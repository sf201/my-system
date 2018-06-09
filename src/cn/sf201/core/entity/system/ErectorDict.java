package cn.sf201.core.entity.system;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.entity.DictObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.Pinyin4JUtils;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sf201 on 9/5/2017.
 */
@Entity
@InitNameAnnotation("安装师傅名录")
@Table(name = "T_SYSTEM_ERECTOR_DICT")
public class ErectorDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("姓名")
    private String name;
    @InitFieldAnnotation("联系方式")
    private String phone;
    @InitFieldAnnotation("所属组别")
    private String group;

    public ErectorDict() {
        this.setId(StringUtil.generator());
    }

    @Column(name = "serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name = "`name`")
    public String getName() {
        return name;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "`group`")
    public String getGroup() {
        return group;
    }

    public void setSerialNo(String test) {
        this.serialNo = test;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getName()));
    }
}
