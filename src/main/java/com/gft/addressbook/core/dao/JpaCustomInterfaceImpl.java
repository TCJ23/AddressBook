package com.gft.addressbook.core.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class JpaCustomInterfaceImpl<T> extends SimpleJpaRepository<T, Long> implements JpaCustomInterface<T> {
    public JpaCustomInterfaceImpl(Class domainClass, EntityManager em) {
        super(domainClass, em);
    }

    public JpaCustomInterfaceImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {

        super(entityInformation, entityManager);
    }
    //    Implemented because in default config Pageable & Sort are not working togheter.
//    when Paging is unPaged return true
//    when Paging was not existing and USER wanted to sort the default sort method Sort was unsorted
//    and custom method was not working
//    This was default method
//    @Override
//    public Page<T> findAll(Specification spec, Pageable pageable) {
//        TypedQuery<T> query = getQuery(spec, pageable);
//        return isUnpaged(pageable) ? new PageImpl<T>(query.getResultList())
//                : readPage(query, getDomainClass(), pageable, spec);
//    plus this
//       private static boolean isUnpaged(Pageable pageable) {
////        return pageable.isUnpaged();
////    }
// }
    @Override
    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, Pageable pageable) {
        return getQuery(spec, getDomainClass(), pageable.getSort());
//        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
//        return getQuery(spec, getDomainClass(), sort);

    }

}
