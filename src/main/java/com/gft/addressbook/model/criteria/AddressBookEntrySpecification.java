package com.gft.addressbook.model.criteria;

import com.gft.addressbook.model.AddressBookEntry;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AddressBookEntrySpecification implements Specification<AddressBookEntry> {

    private SearchCriteria criteria;

    public AddressBookEntrySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<AddressBookEntry> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getProperty()).getJavaType() == String.class) // czy istnieją i są stringiem
            {
                return builder.like(
                        root.<String>get(criteria.getProperty()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getProperty()), criteria.getValue());
            }
        }
        return null;
    }
}
