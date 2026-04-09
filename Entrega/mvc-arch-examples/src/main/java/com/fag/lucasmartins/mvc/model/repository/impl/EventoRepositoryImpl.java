package com.fag.lucasmartins.mvc.model.repository.impl;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;
import com.fag.lucasmartins.mvc.model.repository.EventoRepository;
import com.fag.lucasmartins.mvc.model.repository.entity.EventoEntity;
import com.fag.lucasmartins.mvc.model.repository.jpa.EventoJpaRepository;
import com.fag.lucasmartins.mvc.model.repository.mapper.EventoEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventoRepositoryImpl implements EventoRepository {

    private final EventoJpaRepository jpaRepository;

    public EventoRepositoryImpl(EventoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public EventoBO salvar(EventoBO bo) {
        EventoEntity entity = EventoEntityMapper.toEntity(bo);

        EventoEntity eventoCriado = jpaRepository.save(entity);

        return EventoEntityMapper.toBo(eventoCriado);
    }

    @Override
    public EventoBO buscarPorId(Long id) {
        Optional<EventoEntity> evento = jpaRepository.findById(id);

        if (evento.isEmpty()) {
            throw new IllegalArgumentException("Evento não encontrado!");
        }

        return EventoEntityMapper.toBo(evento.get());
    }
}
