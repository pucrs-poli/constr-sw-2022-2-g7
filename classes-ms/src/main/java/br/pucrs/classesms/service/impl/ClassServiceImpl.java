package br.pucrs.classesms.service.impl;

import br.pucrs.classesms.dto.request.ClassRequestDTO;
import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.dto.response.StudentCallResponseDTO;
import br.pucrs.classesms.entity.Call;
import br.pucrs.classesms.entity.Class;
import br.pucrs.classesms.exception.NotFoundException;
import br.pucrs.classesms.i18n.Translator;
import br.pucrs.classesms.mapper.ReservationMapper;
import br.pucrs.classesms.repository.ClassJdbcRepository;
import br.pucrs.classesms.repository.ClassRepository;
import br.pucrs.classesms.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.pucrs.classesms.mapper.ClassMapper.toEntity;
import static br.pucrs.classesms.mapper.ClassMapper.toResponse;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository repository;
    private final ClassJdbcRepository jdbcRepository;
    private final RoomService roomService;
    private final GroupService groupService;
    private final ReservationService reservationService;
    private final CallService callService;
    private final StudentCallService studentCallService;

    private final Translator translator;

    @SneakyThrows
    @Override
    public ClassResponseDTO save(ClassRequestDTO classRequestDTO) {
        Class classE = this.repository.save(toEntity(classRequestDTO));
        this.callService.save(Call.builder().classE(classE).build());

        classRequestDTO.getResources().forEach(resource ->
                this.reservationService.create(ReservationMapper.toRequest(classE, resource)));

        return this.buildClassResponseDTO(classE);
    }

    @SneakyThrows
    @Override
    public ClassResponseDTO findById(String id) {
        Class classE = this.repository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        translator.toLocale("msg_0_not_found", translator.toLocale("msg_Class"))));

        Call call = this.callService.findByClassId(classE.getId());
        List<StudentCallResponseDTO> studentCall = this.studentCallService.findAllByClassId(call.getId());
        ClassResponseDTO classResponseDTO = this.buildClassResponseDTO(classE);
        classResponseDTO = classResponseDTO.toBuilder().call(studentCall).build();

        return classResponseDTO;
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        return this.repository.findAll().stream()
                .map(this::buildClassResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClassResponseDTO update(ClassRequestDTO classRequestDTO) {
        return this.buildClassResponseDTO(this.repository.save(toEntity(classRequestDTO)));
    }

    @SneakyThrows
    @Override
    public ClassResponseDTO deleteById(String id) {
        ClassResponseDTO classResponseDTO = this.findById(id);
        if (isNull(classResponseDTO)) {
            throw new NotFoundException(
                    translator.toLocale("msg_0_not_found", translator.toLocale("msg_Class")));
        }
        this.repository.deleteById(id);

        return classResponseDTO;
    }

    @Override
    public List<ClassResponseDTO> findAllBySimpleQuery(Map<String, String> params) {
        return this.jdbcRepository.findAllBySimpleQuery(params);
    }

    @Override
    public List<ClassResponseDTO> findAllByComplexQuery(Map<String, String> params) {
        return this.jdbcRepository.findAllByComplexQuery(params);
    }

    private ClassResponseDTO buildClassResponseDTO(Class classE) {
        RoomResponseDTO roomResponseDTO = this.roomService.findById(classE.getRoomId());
        GroupResponseDTO groupResponseDTO = this.groupService.findById(classE.getGroupId());
//        List<ReservationResponseDTO> reservationResponseDTOs = reservationService.findByClassId(classRequestDTO);

        return toResponse(classE, roomResponseDTO, groupResponseDTO, null);
    }

    private ClassResponseDTO buildClassResponseDTO(ClassRequestDTO classRequestDTO) {
        RoomResponseDTO roomResponseDTO = this.roomService.findById(classRequestDTO.getRoomId());
        GroupResponseDTO groupResponseDTO = this.groupService.findById(classRequestDTO.getGroupId());
//        List<ReservationResponseDTO> reservationResponseDTOs = reservationService.findByClassId(classRequestDTO);

//        return toResponse(classE, roomResponseDTO, groupResponseDTO, null);
        return ClassResponseDTO.builder().build();
    }
}
