package com.gft.addressbook.core.model.criteria;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

@Getter
//DATA WRAPPER
public class AddressBookRequest {

    private Set<SearchCriteria> searchCriteriaSet;
    private Sort sort;
    private Integer size;
    private Integer page;

    // statyczna metoda konstruująca obiekt
    public static AddressBookRequest instance(Set<SearchCriteria> searchCriteria, Sort sort, Integer size, Integer page) {
        return new AddressBookRequest(searchCriteria, sort, size, page);
    }

    // prywatny konstruktor
    private AddressBookRequest(Set<SearchCriteria> searchCriteriaSet, Sort sort, Integer size, Integer page) {
        this.searchCriteriaSet = searchCriteriaSet;
        this.sort = sort;
        this.size = size;
        this.page = page;
    }

    public Pageable getPageable() {
        if (size != null) {
            return PageRequest.of(page, size, getSort());
        }
        else {
            return new SortableUnpaged(getSort());
        }
    }

    public Sort getSort() {
        if (sort == null) {
            return Sort.unsorted();
        } else {
            return sort;
        }
    }
}