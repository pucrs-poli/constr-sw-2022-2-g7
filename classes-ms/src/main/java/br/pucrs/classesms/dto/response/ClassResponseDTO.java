package br.pucrs.classesms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponseDTO {
    private Long roomId;
    private Long groupId;
    private LocalDateTime date;
    private String content;
    private List<String> call;
}
