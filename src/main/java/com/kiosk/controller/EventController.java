package com.kiosk.controller;

import com.kiosk.dto.BaseDTO;
import com.kiosk.dto.EventDTO;
import com.kiosk.model.Event;
import com.kiosk.service.base.EventService;
import com.kiosk.transformer.EventTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EventController extends BaseController<EventDTO, Event> {

    @Autowired
    public EventController(EventTransformer transformer, EventService service) {
        super(transformer, service);
    }

    @GetMapping(value = EVENT_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<EventDTO> getById(@PathVariable("id") Integer id) {
        return super.getById(id);
    }

    @GetMapping(value = EVENT_BASE_URL)
    @Override
    public ResponseEntity<Page<EventDTO>> findAll(@PageableDefault(size = 1000) Pageable page) {
        return super.findAll(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = EVENT_BASE_URL)
    @Validated(BaseDTO.CreateValidationGroup.class)
    @Override
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO dto) {
        return super.create(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = EVENT_BASE_URL + "/{id}")
    @Validated(BaseDTO.UpdateValidationGroup.class)
    @Override
    public ResponseEntity<EventDTO> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody EventDTO dto) {
        return super.update(id, dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = EVENT_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return super.delete(id);
    }
}
