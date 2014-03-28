/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", catalog = "zzdrive"
)
public class Role implements java.io.Serializable {

    private int idrole;
    private String name;
    private Set<User> users = new HashSet<>(0);

    public Role() {
    }

    public Role(int idrole) {
        this.idrole = idrole;
    }

    public Role(int idrole, String name, Set<User> users) {
        this.idrole = idrole;
        this.name = name;
        this.users = users;
    }

    @Id

    @Column(name = "idrole", unique = true, nullable = false)
    public int getIdrole() {
        return this.idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
