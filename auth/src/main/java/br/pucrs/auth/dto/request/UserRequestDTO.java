package br.pucrs.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String enabled;
}
