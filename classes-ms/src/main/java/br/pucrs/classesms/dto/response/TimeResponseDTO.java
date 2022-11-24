package br.pucrs.classesms.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponseDTO {
    private String id;
    @JsonProperty("diaSemana")
    private Integer dayOfWeek;
    @JsonProperty("horario")
    private String time;
    @JsonProperty("vagas")
    private Integer vacancies;
}
