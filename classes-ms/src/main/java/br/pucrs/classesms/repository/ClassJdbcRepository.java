package br.pucrs.classesms.repository;

import br.pucrs.classesms.dto.response.ClassResponseDTO;

import java.util.List;
import java.util.Map;

public interface ClassJdbcRepository {
    List<ClassResponseDTO> findAllBySimpleQuery(Map<String, String> params);
    List<ClassResponseDTO> findAllByComplexQuery(Map<String, String> params);
}
