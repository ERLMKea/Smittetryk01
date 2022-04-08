package com.example.smittetryk01.controller;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CountyRESTControllerPaging {

    @Autowired
    CountyRepository countyRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @GetMapping("countysort")
    public ResponseEntity<List<County>> getPageOfCounties(@RequestParam(defaultValue = "countyCode,desc") String[] sort) {
        for (String s: sort) {
            System.out.println(s);
        }
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder: sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        List counties = countyRepository.findAll(Sort.by(orders));

        if (counties.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(counties, HttpStatus.OK);

    }

    


    @GetMapping("countypagesort")
    public ResponseEntity<Map<String, Object>> getCountyPageAndSort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder: sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<County> pageCounty = countyRepository.findAll(pagingSort);
        List<County> county = pageCounty.getContent();

        if (county.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("county", county);
        response.put("currentPage", pageCounty.getNumber());
        response.put("totalItems", pageCounty.getTotalElements());
        response.put("totalPages", pageCounty.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("countypagexx")
    public ResponseEntity<List<County>> getPageOfCountiesxx() {
        int page = 4;
        int size = 5;
        Pageable countyPage = PageRequest.of(page, size);
        Page<County> pageCounty = countyRepository.findAll(countyPage);
        List<County> lstCounty = pageCounty.getContent();
        if (lstCounty.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lstCounty, HttpStatus.OK);
        }
    }

    @GetMapping("countypagedefault")
    public ResponseEntity<Map<String, Object>> getPageOfCountiesDefault(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable countyPage = PageRequest.of(page, size);
        Page<County> pageCounty = countyRepository.findAll(countyPage);
        List<County> lstCounty = pageCounty.getContent();
        if (lstCounty.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("county", lstCounty);
        response.put("currentPage", pageCounty.getNumber());
        response.put("totalItems", pageCounty.getTotalElements());
        response.put("totalPages", pageCounty.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("countypage")
    public ResponseEntity<Map<String, Object>> getCountyPage(@RequestParam int page,
                                                                     @RequestParam int size) {
        Pageable countyPage = PageRequest.of(page, size);
        Page<County> pageCounty = countyRepository.findAll(countyPage);
        List<County> lstCounty = pageCounty.getContent();
        if (lstCounty.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("county", lstCounty);
        response.put("currentPage", pageCounty.getNumber());
        response.put("totalItems", pageCounty.getTotalElements());
        response.put("totalPages", pageCounty.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
