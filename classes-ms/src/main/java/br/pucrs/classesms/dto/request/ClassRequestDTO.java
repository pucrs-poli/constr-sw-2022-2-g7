package br.pucrs.classesms.dto.request;

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
public class ClassRequestDTO {
    private String roomId;
    private Long groupId;
    private LocalDateTime date;
    private String content;
    private List<ResourceRequestDTO> resources;
}
