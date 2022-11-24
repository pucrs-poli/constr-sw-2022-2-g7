package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.request.ReservationRequestDTO;
import br.pucrs.classesms.dto.request.ResourceRequestDTO;
import br.pucrs.classesms.entity.Class;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public static ReservationRequestDTO toRequest(Class classE, ResourceRequestDTO resource) {
        return ReservationRequestDTO.builder()
                .observation("")
                .classId(classE.getId())
                .resourceId(resource.getId())
                .active(true)
                .build();
    }
}
