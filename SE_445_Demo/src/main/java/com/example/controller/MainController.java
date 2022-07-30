package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class MainController {

    @RequestMapping(value = "tsv-to-maria/{file}", method = RequestMethod.POST)
    public ResponseEntity<?> mariaToTsv(@PathVariable("file") List<?> file) {
        Queue<List<?>> queue = new LinkedList<>();
        if (queue.isEmpty()) {
            Queue<List<?>> queue = new LinkedList();
        }
        queue.add(file);

        for (int i = 0; i < queue.peek().size(); i++) {
            System.out.println(file.size());
        }

        System.out.println(queue.size());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
