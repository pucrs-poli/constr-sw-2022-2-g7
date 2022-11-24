package br.pucrs.classesms.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleBadRequestException(IllegalArgumentException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException exception) {
        log.error(exception.getMessage());
        String error = this.getErrorMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.valueOf(exception.status()));
    }

    private String getErrorMessage(String message) {
        int index = message.indexOf("[{\"error\":");
        return index <= 0 ? message : message.substring(index)
                .replace("[", "")
                .replace("{", "")
                .replace("\"error\":", "")
                .replace("\"", "")
                .replace("}", "")
                .replace("]", "");
    }
}
