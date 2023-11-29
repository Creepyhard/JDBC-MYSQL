package br.com.jdbcmysql.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@SuppressWarnings("ALL")
@Data
@Entity
@Table(name="tblLogAccess")
public class LogAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private Timestamp inclusion;

    @Column
    private int userInclusion;

    @Column
    private Timestamp alteration;

    @Column
    private Timestamp userAlteration;

    @Column(length = 15)
    private String ipAccess;

    @Column(length = 50)
    private String userMaster;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getInclusion() {
        return inclusion;
    }

    public void setInclusion(Timestamp inclusion) {
        this.inclusion = inclusion;
    }

    public int getUserInclusion() {
        return userInclusion;
    }

    public void setUserInclusion(int userInclusion) {
        this.userInclusion = userInclusion;
    }

    public Timestamp getAlteration() {
        return alteration;
    }

    public void setAlteration(Timestamp alteration) {
        this.alteration = alteration;
    }

    public Timestamp getUserAlteration() {
        return userAlteration;
    }

    public void setUserAlteration(Timestamp userAlteration) {
        this.userAlteration = userAlteration;
    }

    public String getIpAccess() {
        return ipAccess;
    }

    public void setIpAccess(String ipAccess) {
        this.ipAccess = ipAccess;
    }

    public String getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(String userMaster) {
        this.userMaster = userMaster;
    }
}
