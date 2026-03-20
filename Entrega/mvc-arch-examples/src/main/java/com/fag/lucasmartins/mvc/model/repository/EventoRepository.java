package com.fag.lucasmartins.mvc.model.repository;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;

public interface EventoRepository {

    EventoBO salvar(EventoBO bo);

    EventoBO buscarPorId(Long id);

}
