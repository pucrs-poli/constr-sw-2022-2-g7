package br.pucrs.classesms.feign;

import br.pucrs.classesms.dto.request.ReservationRequestDTO;
import br.pucrs.classesms.dto.response.ReservationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "reservationClient", url = "${client.reservations.url}")
public interface ReservationClient {
    @GetMapping("/{id}")
    ReservationResponseDTO findById(@PathVariable("id") String id, @RequestHeader("Authorization") String token);

    @GetMapping("/classes/{classId}")
    List<ReservationResponseDTO> findByClassId(@PathVariable("classId") String classId, @RequestHeader("Authorization") String token);

    @PostMapping("/")
    ReservationResponseDTO create(@RequestBody ReservationRequestDTO reservation, @RequestHeader("Authorization") String token);
}
