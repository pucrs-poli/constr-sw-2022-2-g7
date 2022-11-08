package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.entity.Class;
import org.springframework.stereotype.Component;

@Component
public final class ClassMapper {
    public static Class toEntity(ClassRequestDTO dto) {
        return Class.builder()
                .roomId(dto.getRoomId())
                .groupId(dto.getGroupId())
                .date(dto.getDate())
                .content(dto.getContent())
                .build();
    }

    public static ClassResponseDTO toResponse(Class entity) {
        return ClassResponseDTO.builder()
                .roomId(entity.getRoomId())
                .roomId(entity.getGroupId())
                .date(entity.getDate())
                .content(entity.getContent())
                .build();
    }
}
