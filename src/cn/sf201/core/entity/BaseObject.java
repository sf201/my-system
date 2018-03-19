package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by sf201 on 9/5/2017.
 */
@MappedSuperclass
public abstract class BaseObject {
    @InitFieldAnnotation("唯一标识")
    protected String id;

    @Id
    @GenericGenerator(name = "generator",strategy = "assigned")
    @GeneratedValue(generator="generator")
    @Column(length=32,unique = true,nullable = false,updatable = false,insertable = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
