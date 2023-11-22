package br.com.jdbcmysql.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="tblLevelAccess")
public class LevelAccess {

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

    @Column
    private String description;

}
