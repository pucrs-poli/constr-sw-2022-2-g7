package br.pucrs.classesms.component;

import org.springframework.security.core.Authentication;

public interface TokenComponent {
    boolean validateToken(String authToken) throws Exception;
}
