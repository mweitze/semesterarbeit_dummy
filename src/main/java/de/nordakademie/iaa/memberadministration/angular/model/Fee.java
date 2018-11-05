package de.nordakademie.iaa.memberadministration.angular.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Fee {

    private Long id;
    private Date date;
    private Integer fee;

    public Fee() {

    }

    public Fee(Date date, Integer fee) {
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
