package br.pucrs.classesms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDTO {
    private String sub;
    private boolean emailVerified;
    private String name;
    private String preferredUsername;
    private String givenName;
    private String familyName;
    private String email;
    private List<String> groups;
}
