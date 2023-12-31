package com.shangchenhsieh.petmanagementtool.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.shangchenhsieh.petmanagementtool.domain.Pets;
import com.shangchenhsieh.petmanagementtool.service.MapValidationErrorService;
import com.shangchenhsieh.petmanagementtool.service.PetsService;

@Controller
@RequestMapping("/api/pets")
public class PetsController {

    @Autowired
    private PetsService petsService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /**
     * creates a new pet object and save it in the database
     * 
     * @param pet
     * @param result
     * @return the created pet object and HTTP status 200
     */
    @PostMapping("")
    public ResponseEntity<?> createPets(@Valid @RequestBody Pets pet, BindingResult result) {
        // map errors into an errorMap if there are errors
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);

        // return errorMap if there are errors
        if (errorMap != null) {
            return errorMap;
        }
        // save the new pet into database
        Pets savedPet = petsService.savePets(pet);

        return new ResponseEntity<Pets>(savedPet, HttpStatus.CREATED);
    }

    /**
     * find and return a pet with it's name
     * 
     * @param petsName
     * @return a JSON pet object with the HTTP status 200
     */
    // !!! NotUniqueElementException might be thrown since Pets and Users are not
    // connected yet !!!
    @GetMapping("/{petsName}")
    public ResponseEntity<?> getPetsByPetsname(@PathVariable String petsName) {
        Pets pet = petsService.findPetsByPetsname(petsName.toUpperCase());

        return new ResponseEntity<Pets>(pet, HttpStatus.OK);
    }

    /**
     * get all pets from a user
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllPets() {
        return null;
    }

    // update pet
    // delete pet

}
