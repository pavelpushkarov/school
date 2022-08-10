package com.example.school.controllers;

import com.example.school.dto.RegisterDTO;
import com.example.school.dto.Result;
import com.example.school.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("courses")
public class StudentRegisterController {

    private final CoursesService coursesService;

    @PostMapping(path = "/{id}")
    public ResponseEntity register(@PathVariable Integer id, @RequestBody RegisterDTO register) {
        Result<Integer> result = coursesService.register(id, register);
        if (result.hasData()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
