package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.ReservationRequestDTO;
import br.pucrs.classesms.dto.response.ReservationResponseDTO;
import br.pucrs.classesms.feign.ReservationClient;
import br.pucrs.classesms.service.ReservationService;
import br.pucrs.classesms.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucrs.classesms.utils.AuthUtils.*;

@ConditionalOnProperty(
    value="service.reservations.mocked",
    havingValue = "false",
    matchIfMissing = true
)
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationClient client;

    @Override
    public ReservationResponseDTO findById(String id) {
        String token = getLoggedUserToken();
        return this.client.findById(id, token);
    }

    @Override
    public List<ReservationResponseDTO> findByClassId(String classId) {
        String token = getLoggedUserToken();
        return this.client.findByClassId(classId, token);
    }

    @Override
    public ReservationResponseDTO create(ReservationRequestDTO reservation) {
        String token = getLoggedUserToken();
        return this.client.create(reservation, token);
    }
}
