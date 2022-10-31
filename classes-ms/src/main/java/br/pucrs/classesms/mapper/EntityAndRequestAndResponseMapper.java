package br.pucrs.classesms.mapper;

public interface EntityAndRequestAndResponseMapper<E, A, B> {
    E toEntity(A dto);
    B toResponse(E entity);
}
