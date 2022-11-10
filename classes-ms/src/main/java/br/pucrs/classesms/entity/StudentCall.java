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
@Entity(name = "student_call")
public class StudentCall {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "call_id")
    private Call call;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "present")
    private boolean present;
}
