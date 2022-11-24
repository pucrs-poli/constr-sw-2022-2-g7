package br.pucrs.classesms.service.mock;

import br.pucrs.classesms.dto.request.ReservationRequestDTO;
import br.pucrs.classesms.dto.response.ReservationResponseDTO;
import br.pucrs.classesms.dto.response.ResourceResponseDTO;
import br.pucrs.classesms.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@ConditionalOnProperty(
        value="service.reservations.mocked",
        havingValue = "true",
        matchIfMissing = false
)
@RequiredArgsConstructor
@Service
public class ReservationServiceMock implements ReservationService {
    @Override
    public ReservationResponseDTO findById(String id) {
        return ReservationResponseDTO.builder()
            .id("")
            .resource(ResourceResponseDTO.builder().id("").name("Notebook 1").build())
            .build();
    }

    @Override
    public List<ReservationResponseDTO> findByClassId(String classId) {
        return List.of(
            ReservationResponseDTO.builder()
                .id("")
                .resource(ResourceResponseDTO.builder()
                    .id("")
                    .name("Notebook 1")
                    .build())
                .build()
        );
    }

    @Override
    public ReservationResponseDTO create(ReservationRequestDTO reservation) {
        return ReservationResponseDTO.builder()
            .id("")
            .resource(ResourceResponseDTO.builder()
                .id("")
                .name("Notebook 1")
                .build())
            .build();
    }
}
