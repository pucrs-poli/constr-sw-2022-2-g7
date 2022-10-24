package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.response.TokenIntrospectResponseDTO;
import br.pucrs.classesms.dto.response.UserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authClient", url = "${client.auth.url}")
public interface AuthClient {
    @GetMapping("/user-info")
    UserInfoResponseDTO userInfo(@RequestParam("token") String token);

    @GetMapping("/token-introspect")
    TokenIntrospectResponseDTO tokenIntrospect(@RequestBody String token);
}
