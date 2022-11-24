package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.request.StudentCallRequestDTO;
import br.pucrs.classesms.dto.response.StudentCallResponseDTO;

import java.util.List;

public interface StudentCallService {
    StudentCallResponseDTO save(StudentCallRequestDTO dto);
    List<StudentCallResponseDTO> findAllByClassId(String classId);
}
