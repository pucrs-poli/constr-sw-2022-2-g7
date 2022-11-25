package br.pucrs.classesms.service;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;

import java.util.List;
import java.util.Map;

public interface ClassService {
    ClassResponseDTO save(ClassRequestDTO classRequestDTO);
    ClassResponseDTO findById(String id);
    List<ClassResponseDTO> findAll();
    ClassResponseDTO update(ClassRequestDTO classRequestDTO);
    ClassResponseDTO deleteById(String id);
    List<ClassResponseDTO> findAllBySimpleQuery(Map<String, String> params);
    List<ClassResponseDTO> findAllByComplexQuery(Map<String, String> params);
}
