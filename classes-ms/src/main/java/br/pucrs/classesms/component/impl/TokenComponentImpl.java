package br.pucrs.classesms.component.impl;

import br.pucrs.classesms.component.TokenComponent;
import br.pucrs.classesms.i18n.Translator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenComponentImpl implements TokenComponent {
    private final Translator translator;

    @Override
    public boolean validateToken(String authToken) throws Exception {
        try {
            Map<String, Object> tokenDecoded = tokenDecode(authToken);
            verifyExpiration(tokenDecoded);

        } catch (Exception e) {
            log.error("sdk - Exception ao chamar validateToken - " + e.getMessage() + " - token == " + authToken);
            throw new Exception(translator.toLocale("msg_Invalid_Token"));
        }

        return true;
    }

    private Map<String, Object> tokenDecode(String authToken) throws Exception {
        try {
//            JWT jwtToken = JWTParser.parse(authToken);
            // JWT jwtToken = JwtHelper.decode(authToken);
//            Map<String, Object> tokenObj = tryExtractToken(jwtToken);
//            if (tokenObj == null) {
//                throw new Exception(translator.toLocale("token_mal_formatado"));
//            }
//            return tokenObj;
            return null;
        } catch (Exception e) {
            throw new Exception(translator.toLocale("msg_Badly_Formatted_Token"));
        }
    }

    private void verifyExpiration(Map<String, Object> tokenObj) throws Exception {
        try {

            Date expirationTime = (Date) tokenObj.get("exp");
            Date now = new Date();
            if (expirationTime != null && now.after(expirationTime)) {
                throw new Exception(translator.toLocale("msg_Expired_token"));
            }

			/* 	Long timestamp = (long) ((Integer) tokenObj.get("exp")) * 1000;
			Date now = new Date();
			Date expirationTime = new Date(timestamp);
			if (!now.before(expirationTime)) {
				throw new Exception(translator.toLocale("token_expirado"));
			} */
        } catch (Exception e) {
            throw new Exception(translator.toLocale("msg_Expired_token"));
        }
    }
}
