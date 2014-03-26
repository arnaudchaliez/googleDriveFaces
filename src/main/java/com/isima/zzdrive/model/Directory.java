/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "directory", catalog = "zzdrive"
)
public class Directory implements java.io.Serializable {

    private int iddirectory;
    private String name;
    private int idparent;
    private Integer size;
    private int idowner;

    public Directory() {
    }

    public Directory(int iddirectory, String name, int idparent, int idowner) {
        this.iddirectory = iddirectory;
        this.name = name;
        this.idparent = idparent;
        this.idowner = idowner;
    }

    public Directory(int iddirectory, String name, int idparent, Integer size, int idowner) {
        this.iddirectory = iddirectory;
        this.name = name;
        this.idparent = idparent;
        this.size = size;
        this.idowner = idowner;
    }

    @Id
    @Column(name = "iddirectory", unique = true, nullable = false)
    public int getIddirectory() {
        return this.iddirectory;
    }

    public void setIddirectory(int iddirectory) {
        this.iddirectory = iddirectory;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "idparent", nullable = false)
    public int getIdparent() {
        return this.idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    @Column(name = "size")
    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Column(name = "idowner", nullable = false)
    public int getIdowner() {
        return this.idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

}
