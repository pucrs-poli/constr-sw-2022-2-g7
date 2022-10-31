package br.pucrs.classesms.mapper.impl;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.entity.Class;
import br.pucrs.classesms.mapper.EntityAndRequestAndResponseMapper;
import org.springframework.stereotype.Component;

@Component
public final class ClassRequestAndResponseMapperImpl implements EntityAndRequestAndResponseMapper<Class, ClassRequestDTO, ClassResponseDTO> {
    @Override
    public Class toEntity(ClassRequestDTO dto) {
        return Class.builder()
                .build();
    }

    @Override
    public ClassResponseDTO toResponse(Class entity) {
        return ClassResponseDTO.builder()
                .build();
    }
}
