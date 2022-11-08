package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.entity.Call;
import br.pucrs.classesms.repository.CallRepository;
import br.pucrs.classesms.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CallServiceImpl implements CallService {
    private final CallRepository repository;

    @Override
    public List<Call> findAllByClassId(Long id) {
        return this.repository.findAllByClassE_Id(id);
    }
}
