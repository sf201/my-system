package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sf201 on 9/26/2017.
 */
@Entity
@Table(name="t_system_config_type_dict")
@InitNameAnnotation("配置类型字典表")
public class ConfigTypeDict extends BaseObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("配置类型名称")
    private String name;

    public ConfigTypeDict() {
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
