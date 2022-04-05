package com.example.smittetryk01.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @Column(name="regioncode")
    private String regionCode;
    private String name;
    private String href;

    @OneToMany
    @JoinColumn(name="regioncode")
    private Set<County> counties = new HashSet<>();

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public Set<County> getCounties() {
        return counties;
    }

    public void setCounties(Set<County> counties) {
        this.counties = counties;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionCode='" + regionCode + '\'' +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
