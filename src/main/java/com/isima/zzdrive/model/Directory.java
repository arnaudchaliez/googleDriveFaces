/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.Entity;

@Entity
public class Directory extends File implements java.io.Serializable {

    public final static String TYPE = "Directory";

    public Directory() {
    }

    public Directory(String name, int idparent, int idowner) {
        super(name, idparent, "Directory", idowner);
    }

    public Directory(String name, int idparent, Integer size, int idowner) {
        super(name, idparent, "Directory", idowner);
    }
}
