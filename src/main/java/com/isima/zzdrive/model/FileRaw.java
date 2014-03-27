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
import lombok.Getter;
import lombok.Setter;

@Entity
public class FileRaw extends File implements java.io.Serializable {

    public final static String TYPE = "File";
    
    @Getter
    @Setter
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    public FileRaw() {
    }

    public FileRaw(int iddirectory, String type, int idowner) {
        super(iddirectory, "File" , idowner);
    }

    public FileRaw(String name, int iddirectory, String type, byte[] content, int idowner) {
        super(name, iddirectory, "File", idowner);
        this.content = content;
    }

}
