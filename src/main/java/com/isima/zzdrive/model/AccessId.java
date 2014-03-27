/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccessId implements java.io.Serializable {

    private int idfileaccess;
    private int iduseraccess;

    public AccessId() {
    }

    public AccessId(int idfileaccess, int iduseraccess) {
        this.idfileaccess = idfileaccess;
        this.iduseraccess = iduseraccess;
    }

    @Column(name = "idfileaccess", nullable = false)
    public int getIdfileaccess() {
        return this.idfileaccess;
    }

    public void setIdfileaccess(int idfileaccess) {
        this.idfileaccess = idfileaccess;
    }

    @Column(name = "iduseraccess", nullable = false)
    public int getIduseraccess() {
        return this.iduseraccess;
    }

    public void setIduseraccess(int iduseraccess) {
        this.iduseraccess = iduseraccess;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof AccessId)) {
            return false;
        }
        AccessId castOther = (AccessId) other;

        return (this.getIdfileaccess() == castOther.getIdfileaccess())
                && (this.getIduseraccess() == castOther.getIduseraccess());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getIdfileaccess();
        result = 37 * result + this.getIduseraccess();
        return result;
    }

}
