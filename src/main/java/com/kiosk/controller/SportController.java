package com.kiosk.controller;

import com.kiosk.dto.BaseDTO;
import com.kiosk.dto.SportDTO;
import com.kiosk.model.Sport;
import com.kiosk.service.base.SportService;
import com.kiosk.transformer.SportTransformer;
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
public class SportController extends BaseController<SportDTO, Sport> {

    @Autowired
    public SportController(SportTransformer transformer, SportService service) {
        super(transformer, service);
    }

    @GetMapping(value = SPORT_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<SportDTO> getById(@PathVariable("id") Integer id) {
        return super.getById(id);
    }

    @GetMapping(value = SPORT_BASE_URL)
    @Override
    public ResponseEntity<Page<SportDTO>> findAll(@PageableDefault(size = 1000) Pageable page) {
        return super.findAll(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = SPORT_BASE_URL)
    @Validated(BaseDTO.CreateValidationGroup.class)
    @Override
    public ResponseEntity<SportDTO> create(@Valid @RequestBody SportDTO dto) {
        return super.create(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = SPORT_BASE_URL + "/{id}")
    @Validated(BaseDTO.UpdateValidationGroup.class)
    @Override
    public ResponseEntity<SportDTO> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody SportDTO dto) {
        return super.update(id, dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = SPORT_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return super.delete(id);
    }
}
