package br.pucrs.classesms.service;

import br.pucrs.classesms.entity.Call;

public interface CallService {
    Call save(Call call);
    Call findById(String id);
    Call findByClassId(String id);
}
