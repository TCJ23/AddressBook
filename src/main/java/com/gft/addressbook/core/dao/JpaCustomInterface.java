package com.gft.addressbook.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
/*Annotation to exclude repository interfaces from being picked up and thus in consequence getting an instance being
        * created.*/
public interface JpaCustomInterface<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    Page<T> findAll(Specification<T> spec, Pageable pageable);
}
