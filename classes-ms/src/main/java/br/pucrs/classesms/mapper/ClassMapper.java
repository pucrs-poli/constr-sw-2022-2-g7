package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.dto.response.ResourceResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.entity.Class;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public static ClassResponseDTO toResponse(Class entity, RoomResponseDTO room, GroupResponseDTO group, List<ResourceResponseDTO> resources) {
        return ClassResponseDTO.builder()
                .id(entity.getId())
                .room(room)
                .group(group)
                .resources(resources)
                .date(entity.getDate())
                .content(entity.getContent())
                .build();
    }
}
