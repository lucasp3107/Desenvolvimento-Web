package com.fag.lucasmartins.mvc.model.service.impl;

import com.fag.lucasmartins.mvc.model.bo.EventoBO;
import com.fag.lucasmartins.mvc.model.repository.EventoRepository;
import com.fag.lucasmartins.mvc.model.service.EventoService;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public EventoBO criarEvento(EventoBO eventoBO) {

        return eventoRepository.salvar(eventoBO);
    }

    @Override
    public void comprarIngresso(Long id) {
        EventoBO eventoBO = eventoRepository.buscarPorId(id);

        eventoBO.registrarVenda();

        eventoRepository.salvar(eventoBO);
    }
}
