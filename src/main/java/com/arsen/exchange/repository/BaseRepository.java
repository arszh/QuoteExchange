package com.arsen.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Override
    @Query("select e from #{#entityName} e where e.deleted=false")
    List<T> findAll();

    @Override
    @Query("select e from #{#entityName} e where e.id=?1 and e.deleted=false")
    Optional<T> findById(ID id);


    @Query("update #{#entityName} e set e.deleted=true where e.id=?1")
    @Modifying
    void softDelete(long id);


}
