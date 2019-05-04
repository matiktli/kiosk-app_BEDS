package com.kiosk.controller;

import com.kiosk.dto.BaseDTO;
import com.kiosk.dto.RoomDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RoomController implements BaseKioskController<RoomDTO>{

    @GetMapping(value = ROOM_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<RoomDTO> getById(@PathVariable("id") Integer id) {
        return null;
    }

    @GetMapping(value = ROOM_BASE_URL)
    @Override
    public ResponseEntity<Page<RoomDTO>> findAll(@PageableDefault(size = 25) Pageable page) {
        return null;
    }

    @PostMapping(value = ROOM_BASE_URL)
    @Validated(BaseDTO.CreateValidationGroup.class)
    @Override
    public ResponseEntity<RoomDTO> create(@Valid @RequestBody RoomDTO dto) {
        return null;
    }

    @PutMapping(value = ROOM_BASE_URL + "/{id}")
    @Validated(BaseDTO.UpdateValidationGroup.class)
    @Override
    public ResponseEntity<RoomDTO> update(@PathVariable("id") Integer id,
                                                  @Valid @RequestBody RoomDTO dto) {
        return null;
    }

    @DeleteMapping(value = ROOM_BASE_URL + "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        return null;
    }
}
