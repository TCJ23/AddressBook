package com.gft.addressbook.core.model.criteria;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Set;

@Getter
public class AddressBookRequest {

    private Set<SearchCriteria> searchCriteriaSet;
    /// wielkość strony i nr strony
    private Sort sort;

    // statyczna metoda konstruująca obiekt
    public static AddressBookRequest instance(Set<SearchCriteria> searchCriteria, Sort sort) {
        return new AddressBookRequest(searchCriteria, sort);
    }

    // prywatny konstruktor
    private AddressBookRequest(Set<SearchCriteria> searchCriteriaSet, Sort sort) {
        this.searchCriteriaSet = searchCriteriaSet;
        this.sort = sort;
    }

    public Sort getSort() {
        if (sort == null) {
        return Sort.unsorted();
        } else {
            return sort;
        }
    }
}