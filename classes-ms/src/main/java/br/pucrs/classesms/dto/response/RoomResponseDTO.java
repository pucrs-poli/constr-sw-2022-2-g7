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
public class RoomResponseDTO {
    @JsonProperty("_id")
    private String id;
    private Integer number;
    private Integer capacity;
    private Integer floor;
    private String resource;
}
