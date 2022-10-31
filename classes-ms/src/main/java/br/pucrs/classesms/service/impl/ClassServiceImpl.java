package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.mapper.impl.ClassRequestAndResponseMapperImpl;
import br.pucrs.classesms.repository.ClassRepository;
import br.pucrs.classesms.service.ClassService;
import br.pucrs.classesms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository repository;
    private final RoomService roomService;
    private final ClassRequestAndResponseMapperImpl classResponseMapper;

    private final Translator translator;

    @Override
    public ClassResponseDTO save(ClassRequestDTO classRequestDTO) {
        RoomResponseDTO roomResponseDTO = this.roomService.findById(classRequestDTO.getRoomId());
        if (isNull(roomResponseDTO)) {
            throw new IllegalArgumentException(translator.toLocale("msg_Room_not_found"));
        }

        return this.classResponseMapper
            .toResponse(
                this.repository.save(this.classResponseMapper.toEntity(classRequestDTO))
            );
    }

    @Override
    public ClassResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        return null;
    }

    @Override
    public ClassResponseDTO update(ClassRequestDTO classRequestDTO) {
        return null;
    }

    @Override
    public ClassResponseDTO deleteById(Long id) {
        return null;
    }
}
