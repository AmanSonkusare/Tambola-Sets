package com.tambola.controller;

import com.tambola.dao.TambolaDao;
import com.tambola.model.Response;
import com.tambola.service.TambolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TambolaController {
    @Autowired
    TambolaService tambolaService;

    @Autowired
    TambolaDao tambolaDao;

    @PostMapping("/generateTambolaTicket/{ticketNumber}")
    public ResponseEntity<Response> generatTicket(@PathVariable int ticketNumber) {
        Response response = tambolaDao.generateTicket(ticketNumber);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllTicket")
    public ResponseEntity<String> getAllTricket(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "6") int size) {
        String getTicket = tambolaService.getAllTricket(page, size);
        return new ResponseEntity<>(getTicket, HttpStatus.OK);


    }
}
