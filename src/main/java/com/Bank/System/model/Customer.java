package com.Bank.System.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customer")
@Where(clause = "is_deleted = false")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_gen")
    @SequenceGenerator(name = "cust_gen", sequenceName = "cust_gen", initialValue = 1,allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "customer_name")
    private String customerName;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
