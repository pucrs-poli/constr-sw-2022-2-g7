package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.feign.GroupClient;
import br.pucrs.classesms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupClient client;

    @Override
    public GroupResponseDTO findById(Long id) {
        return this.client.findById(id);
    }
}
