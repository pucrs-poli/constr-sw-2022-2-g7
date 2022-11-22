package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.response.RoomResponseDTO;

public interface RoomService {
    RoomResponseDTO findById(String id);
}
