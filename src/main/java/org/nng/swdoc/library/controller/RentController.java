package org.nng.swdoc.library.controller;

import org.nng.swdoc.library.dto.InputRentDto;
import org.nng.swdoc.library.dto.OutputRentDto;
import org.nng.swdoc.library.mangement.BookRentManager;
import org.nng.swdoc.library.mangement.BookReturnManager;
import org.nng.swdoc.library.mangement.RentObservable;
import org.nng.swdoc.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rent")
public class RentController extends RentObservable {
    @Autowired
    private RentService rentService;

    public RentController() {
        this.addRentEventListener(Event.BOOK_RENTED, new BookRentManager());
        this.addRentEventListener(Event.BOOK_RETURNED, new BookReturnManager());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputRentDto> getRentById(@PathVariable Long id) {
        OutputRentDto rent = rentService.getRentById(id);
        return ResponseEntity.ok(rent);
    }

    @GetMapping
    public ResponseEntity<List<OutputRentDto>> getAllRents() {
        List<OutputRentDto> rents = rentService.getAllRents();
        return ResponseEntity.ok(rents);
    }

    @PostMapping
    public ResponseEntity<OutputRentDto> createRent(@RequestBody InputRentDto inputRent) {
        OutputRentDto rent = rentService.createRent(inputRent);
        if (this.notifyRentEventChange(Event.BOOK_RENTED, rent)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rent);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/return_book/{id}")
    public ResponseEntity<OutputRentDto> returnRent(@PathVariable Long id) {
        if (this.notifyRentEventChange(Event.BOOK_RETURNED, rentService.getRentById(id))) {
            return ResponseEntity.ok(rentService.getRentById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OutputRentDto> updateRent(@PathVariable Long id, @RequestBody InputRentDto inputRentDto) {
        OutputRentDto rent = rentService.updateRent(id, inputRentDto);
        return ResponseEntity.ok(rent);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }
}

