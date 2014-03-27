/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
@Table(catalog = "zzdrive")
public abstract class File implements java.io.Serializable {
    
    public final static String TYPE = "Undefined";
    
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idfile")
    private int idfile;
    
    @Getter
    @Setter
    @Column(name = "idowner", nullable = false)
    private int idowner;
    
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    
    @Getter
    @Setter
    @Column(name = "date")
    private Date date;


    @Getter
    @Setter
    @Column(name = "iddirectory", nullable = false)
    private int iddirectory;

    @Getter
    @Setter
    @Column(name = "type", nullable = false)
    private String type;

    @Getter
    @Setter
    @Column(name = "size")
    private Integer size;
    
    public File() {
    }

    public File(int iddirectory, String type, int idowner) {
        this.iddirectory = iddirectory;
        this.type = type;
        this.idowner = idowner;
    }

    public File(String name, int iddirectory, String type, int idowner) {
        this.name = name;
        this.iddirectory = iddirectory;
        this.type = type;
        this.idowner = idowner;
        this.date = null;
    }
    

}
