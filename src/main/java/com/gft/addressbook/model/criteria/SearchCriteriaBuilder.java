package com.gft.addressbook.model.criteria;

import java.util.HashSet;
import java.util.Set;

public class SearchCriteriaBuilder {

    private Set<SearchCriteria> searchCriteriaSet = new HashSet<SearchCriteria>();

    SearchCriteriaBuilder() {
    }

    public SearchCriteriaBuilder searchBy(String s) {
        searchCriteriaSet.add(new SearchCriteria(s));
        return this;
    }

    public Set<SearchCriteria> buildResult() {
        return new HashSet<SearchCriteria>(searchCriteriaSet);
    }
}
