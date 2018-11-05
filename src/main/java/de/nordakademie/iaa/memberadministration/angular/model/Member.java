package de.nordakademie.iaa.memberadministration.angular.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Member {
    private Long id;
    private String name;
    private String address;
    private Date birthday;
    private Date joinDate;
    private Date cancelDate;
    private Date leaveDate;
    private Membership membership;
    private Set<Fee> paidFees;
    private String bankDetails;
    private Member familyMember;

    public Member() {
    }

    public Member(String name, String address, Date birthday, Date joinDate, Date cancelDate, Date leaveDate, Membership membership, Set<Fee> paidFees, String bankDetails, Member familyMember) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.cancelDate = cancelDate;
        this.leaveDate = leaveDate;
        this.membership = membership;
        this.paidFees = paidFees;
        this.bankDetails = bankDetails;
        this.familyMember = familyMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (!name.equals(member.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne()
    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<Fee> getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(Set<Fee> paidFees) {
        this.paidFees = paidFees;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    @OneToOne(optional = true)
    public Member getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Member familyMember) {
        this.familyMember = familyMember;
    }
}
