package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.response.GroupResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "roomsClient", url = "${client.groups.url}")
public interface GroupClient {
    @GetMapping("/{id}")
    GroupResponseDTO findById(@PathVariable("id") Long id);
}
