package com.example.smittetryk01.controller;

import com.example.smittetryk01.exception.ResourceNotFoundException;
import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import com.example.smittetryk01.repository.CountyRepository;
import com.example.smittetryk01.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CountyRESTController {

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/counties")
    public List<County> getAllCounties() {
        return countyRepository.findAll();
    }

    @GetMapping("/counties2")
    public List<County> getAllCounties2() {
        return countyRepository.findAll();
    }

    @GetMapping("countyByNameyy/{name}")
    public County findCountyByNameyy(@PathVariable String name) {
        Optional<County> obj = countyRepository.findByName(name);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            return null;
        }
    }

    @GetMapping("countyByName/{name}")
    public County findCountyByName(@PathVariable String name) {
        return countyRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("County not found with name = " + name));
    }



    @GetMapping("countyxx")
    public List<County> findAllCountiesxx() {
        int i1 = 100;
        int i2 = 0;
        int i3 = i1/i2;
        return countyRepository.findAll();
    }



    @GetMapping("countyByNameRE/{name}")
    public ResponseEntity<County> findCountyByNameRE(@PathVariable String name) {
        Optional<County> optCounty = countyRepository.findByName(name);
        if (optCounty.isPresent()) {
            return new ResponseEntity<>(optCounty.get(),HttpStatus.OK);
        } else {
            County notfoundCounty = new County();
            notfoundCounty.setName("Jeg kunne ikke finde name=" + name);
            return new ResponseEntity<>(notfoundCounty, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("countyByNameLike/{name}")
    public List<County> findCountyByNameLike(@PathVariable String name) {
        name = '%' + name + '%';
        return countyRepository.findByNameIsLike(name);
    }

    @GetMapping("countyByRegionId/{id}")
    public List<County> findCountyByRegionId(@PathVariable String id) {
        return countyRepository.findCountyByRegionRegionCode(id);
    }

    @GetMapping("countyByRegionId2/{id}")
    public List<County> findCountyByRegionId2(@PathVariable String id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            Region reg = region.get();
            return countyRepository.findCountyByRegion(reg);
        } else {
            return null;
        }
    }

    @GetMapping("countCountyByNameLike/{name}")
    public long countCountyByNameLike(@PathVariable String name) {
        name = '%' + name + '%';
        return countyRepository.countCountyByNameLike(name);
    }

    @GetMapping("countCountyByNameLike/{name}/{regcode}")
    public long countCountyByNameLikeAndRegionCode(@PathVariable String name, @PathVariable String regcode) {
        name = '%' + name + '%';
        return countyRepository.countCountyByNameLikeAndRegionCode(name, regcode);
    }


    @GetMapping("/")
    public String hej() {
        return "Hello World";
    }

    @GetMapping("/county/{id}")
    public County findCountyById(@PathVariable String id) {
        Optional<County> obj = countyRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            return null;
        }

    }

    @PostMapping("/county")
    @ResponseStatus(HttpStatus.CREATED)
    public County postCounty(@RequestBody County county) {
        System.out.println(county);
        return countyRepository.save(county);
    }



    @PutMapping("/county/{id}")
    public ResponseEntity<County> updateCount(@PathVariable String id, @RequestBody County county) {
        Optional<County> optCounty = countyRepository.findById(id);
        if (optCounty.isPresent()) {
            countyRepository.save(county);
            return new ResponseEntity<>(county,HttpStatus.OK);
        } else {
            County notfoundCounty = new County();
            notfoundCounty.setName("Jeg kunne ikke finde id=" + id);
            return new ResponseEntity<>(notfoundCounty, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/county/{id}")
    public ResponseEntity<String> deleteCounty(@PathVariable String id) {
        try {
            countyRepository.deleteById(id);
            return new ResponseEntity<>("Slettet id=" + id, HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity<>("Jeg kunnne ikke slet id=" + id, HttpStatus.NOT_FOUND);
        }

    }


}





