package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;

import java.util.List;

public interface ClassService {
    ClassResponseDTO save(ClassRequestDTO classRequestDTO);
    ClassResponseDTO findById(Long id);
    List<ClassResponseDTO> findAll();
    ClassResponseDTO update(ClassRequestDTO classRequestDTO);
    ClassResponseDTO deleteById(Long id);
}
