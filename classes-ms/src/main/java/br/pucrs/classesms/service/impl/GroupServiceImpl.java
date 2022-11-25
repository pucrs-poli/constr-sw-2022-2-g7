package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.exception.NotFoundException;
import br.pucrs.classesms.feign.GroupClient;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.pucrs.classesms.utils.AuthUtils.getLoggedUserToken;

@ConditionalOnProperty(
    value="service.groups.mocked",
    havingValue = "false",
    matchIfMissing = true
)
@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupClient client;

    private final Translator translator;

    @Override
    public GroupResponseDTO findById(Long id) {
        String token = getLoggedUserToken();
        return Optional.of(this.client.findById(id, token)).orElseThrow(() -> new NotFoundException(
                translator.toLocale("msg_0_not_found", translator.toLocale("msg_Group"))));
    }
}
