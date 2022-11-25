package br.pucrs.classesms.repository;

import br.pucrs.classesms.entity.Call;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CallRepository extends CrudRepository<Call, String> {
    Optional<Call> findByClassE_Id(String classId);
}
