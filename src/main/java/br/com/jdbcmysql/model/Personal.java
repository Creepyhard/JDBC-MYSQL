package br.com.jdbcmysql.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tblpersonal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeCompleto;

    @OneToOne
    @JoinColumn(name = "tbluser", referencedColumnName = "id")
    private User user;

    private Timestamp inclusion;

    private Timestamp alteration;

    private String email;

    private String telefone;

    private String celular;

    @OneToOne
    @JoinColumn(name = "tblcargo", referencedColumnName = "id")
    private Cargo cargo;
}
