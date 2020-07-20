package com.desafio.controller;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.University;
import com.desafio.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @GetMapping("/universidades")
    public List<University> getAllUniversity() {
        return universityRepository.findAll();
    }

    @PostMapping("/universidade")
    public University createUniversity(@RequestBody University university) {
        return universityRepository.save(university);
    }

    @GetMapping("/universidade/{id}")
    public University getUniversityById(@PathVariable(value = "id") Long universityId) {
        return universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("Universidade", "id", universityId));
    }

    @PutMapping("/universidade/{id}")
    public University updateUnviersity(@PathVariable(value = "id") Long universityId,
                           @RequestBody University universityDetails) {

        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("Universidade", "id", universityId));

        university.setName(universityDetails.getName());
        university.setCnpj(universityDetails.getCnpj());
        university.setAddress(universityDetails.getAddress());

        University updatedUniversity = universityRepository.save(university);
        return updatedUniversity;
    }

    @DeleteMapping("/universidade/{id}")
    public ResponseEntity<?> deleteUniversity(@PathVariable(value = "id") Long universityId) {
        University unviersity = universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("University", "id", universityId));

        universityRepository.delete(unviersity);

        return ResponseEntity.ok().build();
    }
}