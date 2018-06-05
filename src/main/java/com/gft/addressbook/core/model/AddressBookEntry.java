package com.gft.addressbook.core.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSBOOK_ENTRIES ")
@Getter
@Setter
public class AddressBookEntry {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "telephone")
    private String telePhone;

    @Override
    public String toString() {
        return "ID: " + ID + " " + firstName + " " + lastName + " " + telePhone;
    }

    /*
    Hibernate
     */
    AddressBookEntry() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressBookEntry that = (AddressBookEntry) o;

        return new EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(telePhone, that.telePhone)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .append(telePhone)
                .toHashCode();
    }
}