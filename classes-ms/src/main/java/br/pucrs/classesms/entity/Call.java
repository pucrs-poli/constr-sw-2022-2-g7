package br.pucrs.classesms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "call")
public class Call {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", unique = true)
    private Class classE;
}
