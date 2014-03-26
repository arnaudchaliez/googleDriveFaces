/**
 * ZZDrive - 2014
 *
 * @author Arnaud CHALIEZ
 * @author Jérémy BOUNY
 */
package com.isima.zzdrive.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "access", catalog = "zzdrive"
)
public class Access implements java.io.Serializable {

    private AccessId id;
    private User user;

    public Access() {
    }

    public Access(AccessId id, User user) {
        this.id = id;
        this.user = user;
    }

    @EmbeddedId

    @AttributeOverrides({
        @AttributeOverride(name = "idfileaccess", column = @Column(name = "idfileaccess", nullable = false)),
        @AttributeOverride(name = "iduseraccess", column = @Column(name = "iduseraccess", nullable = false))})
    public AccessId getId() {
        return this.id;
    }

    public void setId(AccessId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduseraccess", nullable = false, insertable = false, updatable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
