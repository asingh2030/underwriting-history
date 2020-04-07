package com.uw.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/
    @Id
    private String ssn;
    private String name;
    private String gender;
    private LocalDate dob;
    private String address;
    @OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Document> documentList;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected Customer(){
    }

    public Customer(String ssn, String name, String gender, LocalDate dob) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getSsn(), customer.getSsn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSsn());
    }
}
