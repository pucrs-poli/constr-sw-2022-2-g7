package br.pucrs.classesms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentCallRequestDTO {
    private Long callId;
    private Long studentId;
    private boolean present;
}
