package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.request.ReservationRequestDTO;
import br.pucrs.classesms.dto.response.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {
    ReservationResponseDTO findById(String id);
    List<ReservationResponseDTO> findByClassId(String classId);
    ReservationResponseDTO create(ReservationRequestDTO reservation);
}
