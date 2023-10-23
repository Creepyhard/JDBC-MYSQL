package br.com.jdbcmysql.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="tbluser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp inclusion;

    //private int userInclusion;

    private Timestamp alteration;

    //private Timestamp userAlteration;

    private String name;

    //private String fullName;

    private int userActive;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tblpersonal", referencedColumnName = "id")
    private Personal personal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getInclusion() {
        return inclusion;
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

    public int getUserActive() {
        return userActive;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public User(long id, Timestamp inclusion, Timestamp alteration, String name,
                int userActive, String password, long idPersonal) {
        this.id = id;
        this.inclusion = inclusion;
        this.alteration = alteration;
        this.name = name;
        this.userActive = userActive;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", inclusion=" + inclusion +
                ", alteration=" + alteration +
                ", name='" + name + '\'' +
                ", userActive=" + userActive +
                ", password='" + password + '\'' +
                ", idPersonal=" + personal +
                '}';
    }
}
