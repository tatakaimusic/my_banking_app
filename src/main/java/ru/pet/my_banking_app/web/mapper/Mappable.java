package ru.pet.my_banking_app.web.mapper;

import java.util.List;

public interface Mappable<E, D>{

    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E toEntity(D dto);

    List<E> toEntity(List<D> dto);

}
