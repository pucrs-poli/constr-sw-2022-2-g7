package br.pucrs.classesms.mapper;

import br.pucrs.classesms.dto.request.StudentCallRequestDTO;
import br.pucrs.classesms.dto.response.StudentCallResponseDTO;
import br.pucrs.classesms.entity.Call;
import br.pucrs.classesms.entity.StudentCall;
import org.springframework.stereotype.Component;

@Component
public class StudentCallMapper {
    public static StudentCall toEntity(StudentCallRequestDTO dto) {
        return StudentCall.builder()
                .call(Call.builder().id(dto.getCallId()).build())
                .studentId(dto.getStudentId())
                .present(dto.isPresent())
                .build();
    }

    public static StudentCallResponseDTO toResponse(StudentCall entity) {
        return StudentCallResponseDTO.builder()
                .call(CallMapper.toResponse(entity.getCall()))
                .studentId(entity.getStudentId())
                .present(entity.isPresent())
                .build();
    }
}
