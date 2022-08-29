package br.pucrs.auth.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import br.pucrs.auth.dto.request.AuthenticationRequestDTO;
import br.pucrs.auth.dto.response.AuthenticationResponseDTO;
import br.pucrs.auth.service.KeycloakService;
import br.pucrs.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final KeycloakService keycloakService;

  @Override
  public AuthenticationResponseDTO login(AuthenticationRequestDTO dto) {
    if (Objects.isNull(dto.getUsername()) || Objects.isNull(dto.getPassword())) {
      throw new IllegalArgumentException("Deve ser informado usuário e senha!");
    }
    return keycloakService.generateToken(dto);
  }

}
