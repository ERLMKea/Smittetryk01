package com.example.smittetryk01.controller;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import com.example.smittetryk01.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RegionRESTController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @GetMapping("/region/{id}")
    public Region getRegion(@PathVariable String id) {
        Optional<Region> obj = regionRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        Region region = new Region();
        region.setName("Not Found");
        return region;
    }

    @GetMapping("/regionsleep/{id}/{thrid}")
    public Region getRegionSleep(@PathVariable String id, @PathVariable String thrid) {
        Optional<Region> obj = regionRepository.findById(id);

        if (thrid.contains("1") | thrid.contains("3") | thrid.contains("5")) {
            System.out.println("VI SKAL SOVE Thread =" + thrid);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException err) {
                System.out.printf("Except=" + err.getMessage());
            }
        }

        if (obj.isPresent()) {
            Region reg = obj.get();
            reg.setHref(thrid);
            return reg;
        }
        Region region = new Region();
        region.setName("Not Found");
        return region;
    }


}
