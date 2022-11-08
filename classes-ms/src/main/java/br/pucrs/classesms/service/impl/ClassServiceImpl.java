package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.entity.Class;
import br.pucrs.classesms.exception.ClassNotFoundException;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.mapper.ClassMapper;
import br.pucrs.classesms.repository.ClassRepository;
import br.pucrs.classesms.service.CallService;
import br.pucrs.classesms.service.ClassService;
import br.pucrs.classesms.service.GroupService;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.pucrs.classesms.mapper.ClassMapper.toEntity;
import static br.pucrs.classesms.mapper.ClassMapper.toResponse;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository repository;
    private final RoomService roomService;
    private final GroupService groupService;
    private final CallService callService;

    private final Translator translator;

    @SneakyThrows
    @Override
    public ClassResponseDTO save(ClassRequestDTO classRequestDTO) {
        RoomResponseDTO roomResponseDTO = this.roomService.findById(classRequestDTO.getRoomId());
        if (isNull(roomResponseDTO)) {
            throw new IllegalArgumentException(translator.toLocale("msg_0_not_found", translator.toLocale("msg_Room")));
        }

        GroupResponseDTO groupResponseDTO = this.groupService.findById(classRequestDTO.getGroupId());
        if (isNull(groupResponseDTO)) {
            throw new IllegalArgumentException(
                    translator.toLocale("msg_0_not_found", translator.toLocale("msg_Group")));
        }

        Class classE = this.repository.save(toEntity(classRequestDTO));
        ClassResponseDTO classResponseDTO = toResponse(classE);
        classResponseDTO = classResponseDTO.toBuilder()
//                .call(this.callService.findAllByClassId(classE.getId()).stream().map(c))
                .build();
        return classResponseDTO;
    }

    @Override
    public ClassResponseDTO findById(Long id) {
        Class classE = this.repository.findById(id).orElse(null);
        return nonNull(classE) ? toResponse(classE) : null;
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        return this.repository.findAll().stream()
                .map(ClassMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClassResponseDTO update(ClassRequestDTO classRequestDTO) {
        return toResponse(this.repository.save(toEntity(classRequestDTO)));
    }

    @SneakyThrows
    @Override
    public ClassResponseDTO deleteById(Long id) {
        var classResponseDTO = this.findById(id);
        if (isNull(classResponseDTO)) {
            throw new ClassNotFoundException(
                translator.toLocale("msg_0_not_found", translator.toLocale("msg_Class")));
        }
        this.repository.deleteById(id);

        return classResponseDTO;
    }
}
