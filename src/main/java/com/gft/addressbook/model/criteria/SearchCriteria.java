package com.gft.addressbook.model.criteria;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@EqualsAndHashCode
public class SearchCriteria {
    private String property;
    private String operation;
    private Object value;

    public SearchCriteria(String search) {
        Pattern pattern = Pattern.compile("(\\w+?)(:)(\\w+?)");
        Matcher matcher = pattern.matcher(search);
        if (matcher.find()) {
            property = matcher.group(1);
            operation = matcher.group(2);
            value = matcher.group(3);
        } else {
            throw new IllegalArgumentException("Wrong search argument");
        }
    }
}
