package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.exception.NotFoundException;
import br.pucrs.classesms.feign.RoomClient;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.pucrs.classesms.utils.AuthUtils.getLoggedUserToken;

@ConditionalOnProperty(
    value="service.rooms.mocked",
    havingValue = "false",
    matchIfMissing = true
)
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomClient client;

    private final Translator translator;

    @Override
    public RoomResponseDTO findById(String id) {
        String token = getLoggedUserToken();
        return Optional.of(this.client.findById(id, token)).orElseThrow(() -> new NotFoundException(
                        translator.toLocale("msg_0_not_found", translator.toLocale("msg_Room"))));
    }
}
