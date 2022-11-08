package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.exception.ClassNotFoundException;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.mapper.ClassRequestAndResponseMapper;
import br.pucrs.classesms.repository.ClassRepository;
import br.pucrs.classesms.service.ClassService;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.pucrs.classesms.mapper.ClassRequestAndResponseMapper.toEntity;
import static br.pucrs.classesms.mapper.ClassRequestAndResponseMapper.toResponse;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository repository;
    private final RoomService roomService;

    private final Translator translator;

    @SneakyThrows
    @Override
    public ClassResponseDTO save(ClassRequestDTO classRequestDTO) {
        RoomResponseDTO roomResponseDTO = this.roomService.findById(classRequestDTO.getRoomId());
        if (isNull(roomResponseDTO)) {
            throw new IllegalArgumentException(translator.toLocale("msg_Room_not_found"));
        }

        return toResponse(this.repository.save(toEntity(classRequestDTO)));
    }

    @Override
    public ClassResponseDTO findById(Long id) {
        return toResponse(this.repository.findById(id).orElse(null));
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        return this.repository.findAll().stream()
                .map(ClassRequestAndResponseMapper::toResponse)
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
            throw new ClassNotFoundException(translator.toLocale("msg_Class_not_found"));
        }
        this.repository.deleteById(id);

        return classResponseDTO;
    }
}
