package br.pucrs.classesms.service.mock;

import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value="service.rooms.mocked",
        havingValue = "true",
        matchIfMissing = false
)
@RequiredArgsConstructor
@Service
public class RoomServiceMock implements RoomService {
    @Override
    public RoomResponseDTO findById(String id) {
        return RoomResponseDTO.builder()
            .id("637ee711bd33ecb128c3c51f")
            .number(203)
            .capacity(60)
            .floor(2)
            .resource("projetor")
            .build();
    }
}
