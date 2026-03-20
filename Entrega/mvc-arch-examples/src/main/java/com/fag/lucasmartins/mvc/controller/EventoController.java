package com.fag.lucasmartins.mvc.controller;


import com.fag.lucasmartins.mvc.controller.mapper.EventoDTOMapper;
import com.fag.lucasmartins.mvc.view.dto.EventoDTO;
import com.fag.lucasmartins.mvc.model.bo.EventoBO;
import com.fag.lucasmartins.mvc.model.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoDTO> criarEvento(@Valid @RequestBody EventoDTO dto) {
        EventoBO eventoBO = EventoDTOMapper.toBo(dto);

        EventoBO eventoCriadoBO = eventoService.criarEvento(eventoBO);

        EventoDTO eventoCriadoDTO = EventoDTOMapper.toDto(eventoCriadoBO);

        return ResponseEntity
                .status(201)
                .body(eventoCriadoDTO);
    }

    @PostMapping("/{id}/comprar")
    public ResponseEntity<String> comprarIngresso(@PathVariable Long id) {
        eventoService.comprarIngresso(id);

        return ResponseEntity.ok("Ingresso adquirido com sucesso!");
    }
}
