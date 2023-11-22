package br.com.jdbcmysql.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String ipAcess;

    @Column(length = 50)
    private String userMaster;

}
