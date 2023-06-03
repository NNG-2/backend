package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.dto.RentDto;
import org.nng.swdoc.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> getRentById(@PathVariable Long id) {
        RentDto rentDto = rentService.getRentById(id);
        return ResponseEntity.ok(rentDto);
    }

    @GetMapping
    public ResponseEntity<List<RentDto>> getAllRents() {
        List<RentDto> rentDtos = rentService.getAllRents();
        return ResponseEntity.ok(rentDtos);
    }

    @PostMapping
    public ResponseEntity<RentDto> createRent(@RequestBody RentDto rentDto) {
        RentDto createdRentDto = rentService.createRent(rentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDto> updateRent(@PathVariable Long id, @RequestBody RentDto rentDto) {
        RentDto updatedRentDto = rentService.updateRent(id, rentDto);
        return ResponseEntity.ok(updatedRentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}

