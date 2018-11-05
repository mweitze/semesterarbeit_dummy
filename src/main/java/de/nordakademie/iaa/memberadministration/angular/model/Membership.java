package de.nordakademie.iaa.memberadministration.angular.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Membership {
    private Long id;
    private String name;
    private Integer fee;

    public Membership() { }

    public Membership(String name, Integer fee) {
        this.name = name;
        this.fee = fee;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NaturalId
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
