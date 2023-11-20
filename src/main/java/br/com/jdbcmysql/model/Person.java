package br.com.jdbcmysql.model;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tblperson")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "idUser")
    @OneToOne
    @JoinColumn(name = "tbluser", referencedColumnName = "id")
    private User user;

    private Timestamp inclusion;

    private Timestamp alteration;

    private String fullName;

    private String email;

    private String telephone;

    @Column(name = "idCargo")
    @OneToOne
    @JoinColumn(name = "tblcargo", referencedColumnName = "id")
    private Cargo cargo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getInclusion() {
        Long datetime = System.currentTimeMillis();
        return new Timestamp(datetime);
    }

    public void setInclusion(Timestamp inclusion) {
        this.inclusion = inclusion;
    }

    public Timestamp getAlteration() {
        return alteration;
    }

    public void setAlteration(Timestamp alteration) {
        this.alteration = alteration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Person() {

    }

    public Person(String fullName, Timestamp inclusion, String email, String telephone) {
        this.fullName = fullName;
        this.inclusion = inclusion;
        this.email = email;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", user=" + user +
                ", inclusion=" + inclusion +
                ", alteration=" + alteration +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cargo=" + cargo +
                '}';
    }
}
