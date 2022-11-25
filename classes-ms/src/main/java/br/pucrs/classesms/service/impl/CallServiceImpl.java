package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.entity.Call;
import br.pucrs.classesms.exception.NotFoundException;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.repository.CallRepository;
import br.pucrs.classesms.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CallServiceImpl implements CallService {
    private final CallRepository repository;

    private final Translator translator;

    @Override
    public Call save(Call call) {
        return this.repository.save(call);
    }

    @Override
    public Call findById(String id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        translator.toLocale("msg_0_not_found", translator.toLocale("msg_Call"))));
    }

    @Override
    public Call findByClassId(String id) {
        return this.repository.findByClassE_Id(id).orElseThrow(
                () -> new NotFoundException(
                        translator.toLocale("msg_0_not_found", translator.toLocale("msg_Call"))));
    }
}
