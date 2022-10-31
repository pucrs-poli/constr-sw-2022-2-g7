package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import br.pucrs.classesms.feign.AuthClient;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthClient authClient;

    private final Translator translator;

    @SneakyThrows
    @Override
    public UserInfoResponseDTO userInfo(String token) {
        if (isNull(token)) {
            throw new IllegalArgumentException(
                translator.toLocale("msg_Field_0_is_Required", translator.toLocale("msg_Token"))
            );
        }

        return this.authClient.userInfo(token);
    }
}
