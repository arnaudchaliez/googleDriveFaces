/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "file", catalog = "zzdrive")
public class File implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfile")
    private int idfile;

    @Column(name = "name")
    private String name;

    @Column(name = "iddirectory", nullable = false)
    private int iddirectory;

    @Column(name = "type", nullable = false)
    private String type;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @Column(name = "idowner", nullable = false)
    private int idowner;

    public File() {
    }

    public File(int iddirectory, String type, int idowner) {
        this.iddirectory = iddirectory;
        this.type = type;
        this.idowner = idowner;
    }

    public File(String name, int iddirectory, String type, byte[] content, int idowner) {
        this.name = name;
        this.iddirectory = iddirectory;
        this.type = type;
        this.content = content;
        this.idowner = idowner;
    }

    public int getIdfile() {
        return idfile;
    }

    public void setIdfile(int idfile) {
        this.idfile = idfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIddirectory() {
        return iddirectory;
    }

    public void setIddirectory(int iddirectory) {
        this.iddirectory = iddirectory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }
}
