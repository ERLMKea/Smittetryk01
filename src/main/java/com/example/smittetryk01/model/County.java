package com.example.smittetryk01.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
public class County {

    @Id
    @Column(name="countycode")
    private String countyCode;
    private String name;
    private String href;

    @ManyToOne
    @JoinColumn(name = "regioncode")
    @JsonBackReference
    private Region region;

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "County{" +
                "countyCode='" + countyCode + '\'' +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", region=" + region +
                '}';
    }
}
