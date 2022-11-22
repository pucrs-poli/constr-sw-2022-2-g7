package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.response.RoomResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "roomClient", url = "${client.rooms.url}")
public interface RoomClient {
    @GetMapping("/{id}")
    RoomResponseDTO findById(@PathVariable("id") String id);
}
