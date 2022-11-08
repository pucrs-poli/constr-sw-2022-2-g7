package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.entity.Class;
import org.springframework.stereotype.Component;

@Component
public final class ClassRequestAndResponseMapper {

    public static Class toEntity(ClassRequestDTO dto) {
        return Class.builder()
                .build();
    }

    public static ClassResponseDTO toResponse(Class entity) {
        return ClassResponseDTO.builder()
                .build();
    }
}
