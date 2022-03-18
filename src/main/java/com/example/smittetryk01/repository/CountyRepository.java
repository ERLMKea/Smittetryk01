package com.example.smittetryk01.repository;

import com.example.smittetryk01.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, String> {

    public Optional<County> findByName(String name);

    public Optional<County> findByHref(String href);

    //this line will compile, but not run, cannot invoke findByKurt
    //Error creating bean with name 'countyRepository' defined in com.example.smittetryk01.repository.CountyRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract java.util.Optional com.example.smittetryk01.repository.CountyRepository.findByKurt(java.lang.String)! Reason: Failed t
    //public Optional<County> findByKurt(String kurt);


    public List<County> findByNameIsLike(String name);


}
