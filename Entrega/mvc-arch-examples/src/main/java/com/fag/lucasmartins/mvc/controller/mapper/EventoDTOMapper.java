package com.fag.lucasmartins.mvc.controller.mapper;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;
import com.fag.lucasmartins.mvc.view.dto.EventoDTO;

public class EventoDTOMapper {

    private EventoDTOMapper() {
    }

    public static EventoBO toBo(EventoDTO dto) {
        return new EventoBO(dto.getId(), dto.getNome(), dto.getCapacidadeMaxima());
    }

    public static EventoDTO toDto(EventoBO bo) {
        return new EventoDTO(bo.getId(), bo.getNome(), bo.getCapacidadeMaxima());
    }
}
