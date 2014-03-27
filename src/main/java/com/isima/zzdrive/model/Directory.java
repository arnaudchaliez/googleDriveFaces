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

public class Directory extends File implements java.io.Serializable {

    public final static String TYPE = "Directory";


    public Directory() {
    }

    public Directory(String name, int idparent, int idowner) {
        this.name = name;
        super(name, idparent, "Directory", idowner);
    }

    public Directory(String name, int idparent, Integer size, int idowner) {
       this.idownhis.iddirectory;
        super(name, idparent, "Directory", idowner);
    }
    public void se = idowner;
}
}
