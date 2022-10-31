package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.feign.RoomClient;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomClient client;

    @Override
    public RoomResponseDTO findById(Long id) {
        return this.client.findById(id);
    }
}
