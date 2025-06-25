package com.app.BrzFinances.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class BrzUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "brzUser")
    private List<DailyTotalExtract> dailyTotalExtracts;

    public BrzUser(){}

    public BrzUser(String firstName, String secondName, String cpf, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DailyTotalExtract> getDailyTotalExtracts() {
        return dailyTotalExtracts;
    }

    public void addDailyTotalExtract(DailyTotalExtract dailyTotalExtract){
        dailyTotalExtracts.add(dailyTotalExtract);
    }
}
