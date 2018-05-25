package com.gft.addressbook.core.model.criteria;

import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.Set;

public class SearchCriteriaBuilder {

    private Sort sort;
    private Set<SearchCriteria> searchCriteriaSet = new HashSet<SearchCriteria>();
    private Integer size;
    private Integer page;

    SearchCriteriaBuilder() {
    }

    public SearchCriteriaBuilder paging(int size, int page) {
        this.size = size;
        this.page = page;
        return this;
    }

    public SearchCriteriaBuilder searchBy(String s) {
        searchCriteriaSet.add(new SearchCriteria(s));
        return this;
    }

    public AddressBookRequest buildResult() {
        return AddressBookRequest.instance(new HashSet<>(searchCriteriaSet), sort, size, page);
    }

    public SearchCriteriaBuilder sortBy(String property, SortOrder sortOrder) {
        sort = Sort.by(sortOrder.getJpaOrder(), property);
        return this;
    }

    // WARSTWA ABSTRAKCJI
    public enum SortOrder {
        // new SortOrder (Sort.Direction.ASC) -> wiele Razy vs. singleton
        ASC(Sort.Direction.ASC), DESC(Sort.Direction.DESC);//SINGLETON
        private Sort.Direction order;

        SortOrder(Sort.Direction order) {
            this.order = order;
        }

        private Sort.Direction getJpaOrder() {
            return order;
        }

        public static SortOrder fromString(String sort) {
            String s = sort.trim().toUpperCase();
            return SortOrder.valueOf(s);
        }
    }

}
