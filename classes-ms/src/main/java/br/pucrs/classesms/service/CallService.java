package br.pucrs.classesms.service;

import br.pucrs.classesms.entity.Call;

import java.util.List;

public interface CallService {
    List<Call> findAllByClassId(Long id);
}
