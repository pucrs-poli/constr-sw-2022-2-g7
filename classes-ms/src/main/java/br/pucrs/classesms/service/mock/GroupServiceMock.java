package br.pucrs.classesms.service.mock;

import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value="service.groups.mocked",
        havingValue = "true",
        matchIfMissing = false
)
@RequiredArgsConstructor
@Service
public class GroupServiceMock implements GroupService {
    @Override
    public GroupResponseDTO findById(Long id) {
        return GroupResponseDTO.builder()
            .id("")
            .numGroup(2)
            .build();
    }
}
