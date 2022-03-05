package me.kacperlukasik.klptl2.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)//przechowywane w postaci łańcucha znaków
    private Types type;
    @ManyToMany(mappedBy = "roles")//właściciel relacji to roles
    private Set<User> users;

    public Role(Types type){
        this.type = type;
    }

    public static enum Types{
        ROLE_ADMIN,
        ROLE_USER
    }
}
