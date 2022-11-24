package br.pucrs.classesms.dto.request;

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
    private String classId;
    private String resourceId;
    private Boolean active;
}
