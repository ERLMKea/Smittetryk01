package com.example.smittetryk01.repository;

import com.example.smittetryk01.model.County;
import com.example.smittetryk01.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, String> {

    public Optional<County> findByName(String name);

    public Optional<County> findByHref(String href);

    //this line will compile, but not run, cannot invoke findByKurt
    //Error creating bean with name 'countyRepository' defined in com.example.smittetryk01.repository.CountyRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract java.util.Optional com.example.smittetryk01.repository.CountyRepository.findByKurt(java.lang.String)! Reason: Failed t
    //public Optional<County> findByKurt(String kurt);

    public List<County> findByNameIsLike(String name);

    public List<County> findCountyByRegion(Region region);

    public List<County> findCountyByRegionRegionCode(String regionCode);

    @Query("SELECT COUNT(c) FROM County c WHERE c.name like ?1")
    long countCountyByNameLike(String name);

    @Query("SELECT COUNT(c) FROM County c JOIN Region r on c.region.regionCode = r.regionCode WHERE c.name like ?1 and r.regionCode=?2")
    long countCountyByNameLikeAndRegionCode(String name, String regionCode);




}
