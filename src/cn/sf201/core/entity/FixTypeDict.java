package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sf201 on 9/25/2017.
 */
@Entity
@InitNameAnnotation("维修品类字典")
@Table(name="t_system_fix_type_dict")
public class FixTypeDict extends BaseObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("品类名称")
    private String name;

    public FixTypeDict() {
        this.setId(StringUtil.generator());
    }
    @Column(name="serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name="`name`")
    public String getName() {
        return name;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
