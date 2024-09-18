package org.mainfest.devSquare.DevSqaure.controller;


import org.bson.types.ObjectId;
import org.mainfest.devSquare.DevSqaure.entities.Querry;
import org.mainfest.devSquare.DevSqaure.services.QuerryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/querries")
public class QuerriesController {
    @Autowired
    private QuerryService querryService;

    @GetMapping
    public ResponseEntity<List<Querry>> getQuerries(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(querryService.fetchAll(name));
    }

    @DeleteMapping
    public ResponseEntity<Querry> delete(@RequestParam ObjectId id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Querry querry = querryService.delete(id,name);
        if ( querry != null) return  ResponseEntity.ok(querry);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Querry> post(@RequestBody Querry querry){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Querry save = querryService.save(querry,name);
        if (save == null) return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return  new ResponseEntity<>(save,HttpStatus.OK);
    }

    @PutMapping
    public

}
