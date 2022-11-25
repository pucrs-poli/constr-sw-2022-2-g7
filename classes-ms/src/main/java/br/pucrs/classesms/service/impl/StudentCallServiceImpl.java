package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.StudentCallRequestDTO;
import br.pucrs.classesms.dto.response.StudentCallResponseDTO;
import br.pucrs.classesms.mapper.StudentCallMapper;
import br.pucrs.classesms.repository.StudentCallRepository;
import br.pucrs.classesms.service.CallService;
import br.pucrs.classesms.service.StudentCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.pucrs.classesms.mapper.StudentCallMapper.toEntity;
import static br.pucrs.classesms.mapper.StudentCallMapper.toResponse;

@RequiredArgsConstructor
@Service
public class StudentCallServiceImpl implements StudentCallService {
    private final StudentCallRepository repository;
    private final CallService callService;

    @Override
    public StudentCallResponseDTO save(StudentCallRequestDTO dto) {
        this.callService.findById(dto.getCallId());

        // todo validate student id
        return toResponse(this.repository.save(toEntity(dto)));
    }

    @Override
    public List<StudentCallResponseDTO> findAllByClassId(String classId) {
        return this.repository.findAllByCall_ClassE_Id(classId).stream()
                .map(StudentCallMapper::toResponse)
                .collect(Collectors.toList());
    }
}
