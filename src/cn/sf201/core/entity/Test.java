package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sf201 on 9/5/2017.
 */
@Entity
@InitNameAnnotation("测试类")
@Table(name="T_CORE_TEST")
public class Test extends BaseObject {
    @InitFieldAnnotation("测试2")
    private String test;

    @Column(name="test")
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
