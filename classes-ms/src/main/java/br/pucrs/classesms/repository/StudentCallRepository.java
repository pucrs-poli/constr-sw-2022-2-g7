package br.pucrs.classesms.repository;

import br.pucrs.classesms.entity.StudentCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCallRepository extends JpaRepository<StudentCall, String> {
    List<StudentCall> findAllByCall_Id(String callId);
    List<StudentCall> findAllByCall_ClassE_Id(String classId);
}
