package br.pucrs.auth.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserGroup {
    STUDENTS("alunos"),
    TEACHERS("professores"),
    COORDINATORS("coordenadores");

    private String description;
}
