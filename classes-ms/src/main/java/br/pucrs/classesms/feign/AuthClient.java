package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.response.TokenIntrospectResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authClient", url = "${client.auth.url}")
public interface AuthClient {
    @GetMapping("/token-introspect")
    TokenIntrospectResponseDTO tokenIntrospect(@RequestBody String token);
}
