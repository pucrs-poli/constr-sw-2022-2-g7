package br.pucrs.auth.exceptions;

import br.pucrs.auth.i18n.Translator;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class FeignExceptionHandler {
    private final Translator translator;

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<Object> handleException(FeignException exception) {
        log.error(exception.getMessage());
        switch (exception.status()) {
            case 401 -> {
                return this.buildResponseEntity(translator.toLocale("msg_Unauthorized"), HttpStatus.UNAUTHORIZED);
            }
            case 404 -> {
                return this.buildResponseEntity(translator.toLocale("msg_Not_Found_The_Requested_Object_Was_Not_Found"), HttpStatus.NOT_FOUND);
            }
        }

        String error = this.getErrorMessage(exception.getMessage());
        return this.buildResponseEntity(error, HttpStatus.valueOf(exception.status()));
    }

    private String getErrorMessage(String message) {
        int index = message.indexOf("[{\"error\":");
        return message.substring(index)
                        .replace("[", "")
                        .replace("{", "")
                        .replace("\"error\":", "")
                        .replace("\"", "")
                        .replace("}", "")
                        .replace("]", "");
    }

    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
