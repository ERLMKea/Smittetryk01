package com.example.smittetryk01;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import com.example.smittetryk01.repository.CountyRepository;
import com.example.smittetryk01.repository.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class Smittetryk01ApplicationTests {

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    RegionRepository regionRepository;

    @BeforeEach
    void beforeEach() {
        Region region = new Region();
        region.setRegionCode("1085");
        region.setName("Region Sjælland");
        region.setHref("https://api.dataforsyningen.dk/regioner/1085");
        regionRepository.save(region);

        County roskilde = new County();
        roskilde.setName("Roskilde");
        roskilde.setCountyCode("0265");
        roskilde.setHref("http://localhost:8080/county/0265");
        roskilde.setRegion(region);
        countyRepository.save(roskilde);

        County kobenhavn = new County();
        kobenhavn.setName("København NV");
        kobenhavn.setCountyCode("2400");
        kobenhavn.setHref("http://localhost:8080/county/2400");
        kobenhavn.setRegion(region);
        countyRepository.save(kobenhavn);
    }

    @Test
    void testFunc() {
        List<County> counties = countyRepository.findAll();
        Assertions.assertEquals(2, counties.size());
    }
}
