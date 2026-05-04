package com.fag.lucasmartins.mvc.model.repository.mapper;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;
import com.fag.lucasmartins.mvc.model.repository.entity.EventoEntity;

public class EventoEntityMapper {

    private EventoEntityMapper() {
    }

    public static EventoEntity toEntity(EventoBO bo) {
        EventoEntity entity = new EventoEntity();
        entity.setId(bo.getId());
        entity.setNome(bo.getNome());
        entity.setCapacidadeMaxima(bo.getCapacidadeMaxima());
        entity.setIngressosVendidos(bo.getIngressosVendidos());

        return entity;
    }

    public static EventoBO toBo(EventoEntity entity) {
        EventoBO bo = new EventoBO();
        bo.setId(entity.getId());
        bo.setNome(entity.getNome());
        bo.setCapacidadeMaxima(entity.getCapacidadeMaxima());
        bo.setIngressosVendidos(entity.getIngressosVendidos());

        return bo;
    }
}
