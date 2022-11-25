package br.pucrs.classesms.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDTO {
    private String id;
    @JsonProperty("numero")
    private Integer numGroup;
    @JsonProperty("semestre")
    private Integer period;
    @JsonProperty("professor")
    private Integer teacher;
    @JsonProperty("vagas")
    private Integer vacancies;
    @JsonProperty("horario")
    private List<TimeResponseDTO> time;
//    @JsonProperty("alunos")
//    private List<> students;

}
