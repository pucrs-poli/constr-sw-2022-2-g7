package br.pucrs.classesms.component;

import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import org.springframework.security.core.Authentication;

public interface TokenComponent {
    UserInfoResponseDTO getUserInfo(String token);
    Authentication getAuthentication(String token, UserInfoResponseDTO userInfoResponseDTO);
}
