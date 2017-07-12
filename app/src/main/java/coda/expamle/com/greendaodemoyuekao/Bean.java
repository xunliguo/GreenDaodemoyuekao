package coda.expamle.com.greendaodemoyuekao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by ASUS-PC on 2017/7/4.
 */
@Entity
public class Bean implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    Long id;
    @Property
    String name;
    @Property
    String banshe;
    @Generated(hash = 138732211)
    public Bean(Long id, String name, String banshe) {
        this.id = id;
        this.name = name;
        this.banshe = banshe;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBanshe() {
        return this.banshe;
    }
    public void setBanshe(String banshe) {
        this.banshe = banshe;
    }

}
