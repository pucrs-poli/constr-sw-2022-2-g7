package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.response.UserInfoResponseDTO;

public interface AuthService {
    UserInfoResponseDTO userInfo(String token);
}
