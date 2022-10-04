package br.pucrs.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class KeyCloakChangePasswordRequestDTO {
    private String type;
    private String value;
    private boolean temporary;
}
