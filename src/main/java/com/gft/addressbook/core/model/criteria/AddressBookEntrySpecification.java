package com.gft.addressbook.core.model.criteria;

import com.gft.addressbook.core.model.AddressBookEntry;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AddressBookEntrySpecification implements Specification<AddressBookEntry> {
    /// strictly HIBERNATE -> turn SearchCriteria into database query ONLY GET

    private Collection<SearchCriteria> criteria;

    public AddressBookEntrySpecification(Collection<SearchCriteria> criteria) {

        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate
            (Root<AddressBookEntry> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //SimpleJpaRepository
        //        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
      /* private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,
                CriteriaQuery<S> query) {

            Assert.notNull(domainClass, "Domain class must not be null!");
            Assert.notNull(query, "CriteriaQuery must not be null!");

            Root<U> root = query.from(domainClass);

            if (spec == null) {
                return root;
            }
            CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

            KLUCZ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
            */

        if (criteria.isEmpty()) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria element : criteria) {
            Optional<Predicate> predicate = internalBuild(root, builder, element);
//            predicate.ifPresent(p -> predicates.add(p));
            predicate.ifPresent(predicates::add);
        }
//        String[] <=> String... to jest Strin...params
        return builder.and(predicates.toArray(new Predicate[0])); //.....
    /* STREAM
        return builder.and(criteria.stream()
                .map(element -> internalBuild(root, builder, element))
                .filter(element -> element.isPresent())
                .toArray(size -> new Predicate[size]));
    */
    /* STREAM  & METHOD REFERENCE
        return builder.and(criteria.stream()
                .map(element -> internalBuild(root, builder, element))
                .filter(Optional::isPresent)
                .toArray(Predicate[]::new));
    */
    }

    private Optional<Predicate> internalBuild(Root<AddressBookEntry> root, CriteriaBuilder builder, SearchCriteria criteria) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getProperty()).getJavaType() == String.class) // czy istnieją i są stringiem
            {
                return Optional.of(builder.like(
                        root.<String>get(criteria.getProperty()), "%" + criteria.getValue() + "%"));
            } else {
                return Optional.of(builder.equal(root.get(criteria.getProperty()), criteria.getValue()));
            }
        }
        return Optional.empty();
    }
}
