package br.pucrs.classesms.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {
    private String observation;
    @JsonProperty("class_id")
    private String classId;
    @JsonProperty("resource_id")
    private String resourceId;
    private Boolean active;
}
