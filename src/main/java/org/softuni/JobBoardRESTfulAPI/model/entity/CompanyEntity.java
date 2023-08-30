package org.softuni.JobBoardRESTfulAPI.model.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();
//    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
//    private Set<OfferEntity> offers;

    public CompanyEntity() {
    }

    public CompanyEntity setName(String name) {
        this.name = capitalizeName(name);
        return this;
    }

    public void addUser(UserEntity user) {
        users.add(user);
    }

    //    public Set<OfferEntity> getOffers() {
//        return offers;
//    }
//
//    public CompanyEntity setOffers(Set<OfferEntity> offers) {
//        this.offers = offers;
//        return this;
//    }
    private String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] words = name.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                String restOfWord = word.substring(1).toLowerCase();
                capitalized.append(firstChar).append(restOfWord).append(" ");
            }
        }
        return capitalized.toString().trim();
    }
}
