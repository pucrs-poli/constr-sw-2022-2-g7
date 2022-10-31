package br.pucrs.classesms.component.impl;

import br.pucrs.classesms.component.TokenComponent;
import br.pucrs.classesms.dto.CustomUserDetails;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.service.AuthService;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenComponentImpl implements TokenComponent {
    private final AuthService authService;

    private final Translator translator;

    @SneakyThrows
    @Override
    public boolean validateToken(String authToken) {
        try {
            this.authService.userInfo(authToken);
        } catch (Exception e) {
            log.error("Exception ao chamar validateToken - " + e.getMessage() + " - token == " + authToken);
            throw new Exception(e.getMessage());
        }

        return true;
    }

    @SneakyThrows
    @Override
    public Authentication getAuthentication(String token) {
        ArrayList<SimpleGrantedAuthority> perm = new ArrayList<>();
        CustomUserDetails principal = CustomUserDetails.builder()
                .token(token)
                .authorities(perm)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", null);
    }

    @SneakyThrows
    private Map<String, Object> tokenDecode(String authToken) {
        try {
            JWT jwt = JWTParser.parse(authToken);
            if (isNull(jwt.getJWTClaimsSet()))
                throw new Exception(translator.toLocale("msg_Badly_Formatted_Token"));

            Map<String, Object> tokenObj = jwt.getJWTClaimsSet().getClaims();
            if (isNull(tokenObj))
                throw new Exception(translator.toLocale("msg_Badly_Formatted_Token"));

            return tokenObj;
        } catch (Exception e) {
            throw new Exception(translator.toLocale("msg_Badly_Formatted_Token"));
        }
    }

    @SneakyThrows
    private void verifyExpiration(Map<String, Object> tokenObj) {
        try {
            Date expirationTime = (Date) tokenObj.get("exp");
            Date now = new Date();
            if (nonNull(expirationTime) && now.after(expirationTime)) {
                throw new Exception(translator.toLocale("msg_Expired_token"));
            }
        } catch (Exception e) {
            throw new Exception(translator.toLocale("msg_Expired_token"));
        }
    }
}
