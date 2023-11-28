package br.com.jdbcmysql.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jdbcmysql.DAO.ConnectionFactory;
import lombok.Data;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Random;

@Data
@Entity
@Table(name="tbluser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private Timestamp inclusion;

    @Column
    private int userInclusion;

    @Column
    private Timestamp alteration; //Time of change

    @Column
    private Timestamp userAlteration;//ID User Log Acess

    @Column(unique = true, name = "nickname", length = 20)
    private String name;

    //private String fullName;

    @Column
    private int userActive;

    @Column(length = 61)
    private String password;

    @Column(name = "idLevelAcess")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tblLevelAcess", referencedColumnName = "id")
    private LevelAccess levelAcess;

    @Column(name = "idPerson")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tblperson", referencedColumnName = "id")
    private Person person;

    public long getId() {
        return id;
    }

    public String ifNullString(String infoUpdate, String fieldUpdate){
        if(infoUpdate != null) {
            return infoUpdate;
        } else {
            return fieldUpdate;
        }
    }

    public String ifNullInt(int infoUpdate, String fieldUpdate){
        if(infoUpdate != 0) {
            return String.valueOf(infoUpdate);
        } else {
            return fieldUpdate;
        }
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

    public String getNameMaster() {
        if (this.name.startsWith("$")) {
            return this.name;
        } else {
            return null;
        }
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

    public String getIpAcess() {
        return "192.1.1.15";
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

    public String verifyPasswordUpdate(long idUser, String password) {
        BCrypt.Result result = null;
        String sql = "SELECT PASSWORD FROM TBLUSER WHERE ID = " + idUser;
        String passwordDB = "";
        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                passwordDB = rs.getString("password");
            }
            result = BCrypt.verifyer().verify(password.toCharArray(), passwordDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.verified) {
            return getPasswordEncrypt(password);
        } else {
            //Return error
            return null;
        }
    }

    public Timestamp getInclusion() {
        Long datetime = System.currentTimeMillis();
        return new Timestamp(datetime);
    }

    public void setInclusion(Timestamp inclusion) {
        this.inclusion = inclusion;
    }

    public Timestamp getAlteration() {
        Long datetime = System.currentTimeMillis();
        return new Timestamp(datetime);
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

    public int getUserInclusion() {
        return userInclusion;
    }

    public void setUserInclusion(int userInclusion) {
        this.userInclusion = userInclusion;
    }

    public Timestamp getUserAlteration() {
        return userAlteration;
    }

    public void setUserAlteration(Timestamp userAlteration) {
        this.userAlteration = userAlteration;
    }

    public void setPerson(Person person) {
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
