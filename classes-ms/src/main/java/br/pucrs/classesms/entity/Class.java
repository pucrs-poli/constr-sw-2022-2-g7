package br.pucrs.classesms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "classes")
public class Class {
    @Id
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;
}
