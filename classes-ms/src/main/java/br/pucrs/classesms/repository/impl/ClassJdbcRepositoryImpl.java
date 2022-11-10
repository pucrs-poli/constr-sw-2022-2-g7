package br.pucrs.classesms.repository.impl;

import br.pucrs.classesms.dto.response.ClassResponseDTO;
import br.pucrs.classesms.dto.response.GroupResponseDTO;
import br.pucrs.classesms.dto.response.RoomResponseDTO;
import br.pucrs.classesms.enumeration.QueryOperators;
import br.pucrs.classesms.repository.ClassJdbcRepository;
import br.pucrs.classesms.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.pucrs.classesms.utils.SqlOperators.*;

@RequiredArgsConstructor
@Repository
public class ClassJdbcRepositoryImpl implements ClassJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SEARCH_CLASSES = "select * from classes where 1 = 1 ";

    @Override
    public List<ClassResponseDTO> findAllBySimpleQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder(SQL_SEARCH_CLASSES);
        List<String> p = new ArrayList<>();

        params.forEach((key, value) -> {
            query.append(AND)
                    .append(key)
                    .append(EQUALS)
                    .append(QUESTION);
            p.add(value);
        });

        return jdbcTemplate.query(query.toString(), p.toArray(), this.rowMapperClassResponseDTO());
    }

    @Override
    public List<ClassResponseDTO> findAllByComplexQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder(SQL_SEARCH_CLASSES);
        List<String> p = new ArrayList<>();

        params.forEach((key, value) -> {
            query.append(AND).append(key);
            String[] v = value.split("}");
            query.append(this.getOperator(v));
            query.append(QUESTION);

            p.add(value);
        });

        return jdbcTemplate.query(query.toString(), p.toArray(), this.rowMapperClassResponseDTO());
    }

    private String getOperator(String[] value) {
        var operator = QueryOperators.valueOf(value[0].replace("{", ""));
        return value.length == 1 ? EQUALS : switch (operator) {
            case neq -> NOT_EQUALS;
            case gt -> GREATER_THAN;
            case gteq -> GREATER_THAN_EQUAL;
            case lt -> LESS_THAN;
            case lteq -> LESS_THAN_EQUAL;
            case like -> LIKE;
        };
    }

    private RowMapper<ClassResponseDTO> rowMapperClassResponseDTO() {
        return (rs, rowNum) -> ClassResponseDTO.builder()
                .room(RoomResponseDTO.builder().id(rs.getLong("room-id")).build())
                .group(GroupResponseDTO.builder().id(rs.getLong("group-id")).build())
                .date(DateUtils.dateToLocalDateTime(rs.getDate("date")))
                .content(rs.getString("content"))
                .build();
    }
}
