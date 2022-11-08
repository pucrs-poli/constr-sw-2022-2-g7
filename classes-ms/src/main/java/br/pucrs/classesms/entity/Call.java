package br.pucrs.classesms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Call {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classE;

    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "present")
    private boolean present;
}
