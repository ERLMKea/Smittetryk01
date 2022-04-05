package com.example.smittetryk01;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import com.example.smittetryk01.repository.CountyRepository;
import com.example.smittetryk01.repository.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
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

        County county = new County();
        county.setName("Roskilde");
        county.setCountyCode("0265");
        county.setHref("http://localhost:8080/county/0265");
        county.setRegion(region);
        countyRepository.save(county);
    }

    @Test
    void testFunc() {
        List<County> counties = countyRepository.findAll();
        Assertions.assertEquals(true, counties.size()>0);
    }

}
