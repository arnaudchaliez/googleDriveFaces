/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "directory", catalog = "zzdrive")
public class Directory implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddirectory")
    private int iddirectory;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "idparent", nullable = false)
    private int idparent;

    @Column(name = "size")
    private Integer size;

    @Column(name = "idowner", nullable = false)
    private int idowner;

    public Directory() {
    }

    public Directory(String name, int idparent, int idowner) {
        this.name = name;
        this.idparent = idparent;
        this.idowner = idowner;
    }

    public Directory(String name, int idparent, Integer size, int idowner) {
        this.idowner = idowner;
    }

    public int getIddirectory() {
        return this.iddirectory;
    }

    public void setIddirectory(int iddirectory) {
        this.iddirectory = iddirectory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdparent() {
        return this.idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int getIdowner() {
        return this.idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }
}
