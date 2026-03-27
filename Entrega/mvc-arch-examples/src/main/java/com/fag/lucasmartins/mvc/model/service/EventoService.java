package com.fag.lucasmartins.mvc.model.service;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;

public interface EventoService {

    EventoBO criarEvento(EventoBO eventoBO);

    void comprarIngresso(Long id);
}
