package br.com.jdbcmysql.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Random;

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

    private int levelAcess;

    @Column(name = "idPerson")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tblperson", referencedColumnName = "id")
    private Person person;

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

    public String getNameUser(String fullName) {
        String nameUser = "";
        nameUser = fullName.substring(0,fullName.indexOf(" ")) + String.valueOf(Math.abs(new Random().nextInt()));
        if(nameUser.length() > 20) {
            return nameUser.substring(0,20);
        } else {
        return nameUser;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordEncrypt(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
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

    public int getUserActive() {
        return userActive;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }

    public Person getPerson() {
        return person;
    }

    public void setPersonal(Person person) {
        this.person = person;
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

    public User(String name, String password){
        this.name = name;
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
                ", idPersonal=" + person +
                '}';
    }
}
