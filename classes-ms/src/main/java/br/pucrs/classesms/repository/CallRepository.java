package br.pucrs.classesms.repository;

import br.pucrs.classesms.entity.Call;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends CrudRepository<Call, Long> {
    List<Call> findAllByClassE_Id(Long classId);
}
