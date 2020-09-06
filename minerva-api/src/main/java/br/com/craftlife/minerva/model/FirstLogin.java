package br.com.craftlife.minerva.model;

import br.com.craftlife.eureka.database.EurekaED;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "firstlogin")
public class FirstLogin implements EurekaED<String> {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "ts_login")
    private Integer timestamp;

    @Override
    public String getId() {
        return username;
    }

    @Override
    public void setId(String s) {
        username = s;
    }
}
