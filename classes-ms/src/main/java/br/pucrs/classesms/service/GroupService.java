package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.response.GroupResponseDTO;

public interface GroupService {
    GroupResponseDTO findById(Long id);
}
