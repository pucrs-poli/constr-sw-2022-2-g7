package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "authClient", url = "${client.auth.url}")
public interface AuthClient {
    @GetMapping("/user-info")
    UserInfoResponseDTO userInfo(@RequestHeader("Authorization") String token);

    @GetMapping("/token-introspect")
    TokenIntrospectResponseDTO tokenIntrospect(@RequestHeader("Authorization") String token);
}
