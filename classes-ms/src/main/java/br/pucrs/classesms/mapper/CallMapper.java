package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.response.CallResponseDTO;
import br.pucrs.classesms.entity.Call;
import org.springframework.stereotype.Component;

@Component
public class CallMapper {
    public static CallResponseDTO toResponse(Call entity) {
        return CallResponseDTO.builder()
                .build();
    }
}
