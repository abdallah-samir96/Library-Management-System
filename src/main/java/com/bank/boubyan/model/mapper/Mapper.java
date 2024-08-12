package com.bank.boubyan.model.mapper;
import java.util.*;
import java.util.stream.Collectors;

public interface Mapper <E,D>{
    E toEntity(D dto);
    D toDTO(E entity);

    default List<E> toEntity(List<D> DTOs) {
        return DTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default List<D> toDTO(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
