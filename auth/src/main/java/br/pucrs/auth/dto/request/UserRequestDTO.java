package br.pucrs.auth.dto.request;

import br.pucrs.auth.enums.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserGroup> groups;
}
