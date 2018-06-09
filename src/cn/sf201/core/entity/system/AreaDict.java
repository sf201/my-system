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
@InitNameAnnotation("区域名录")
@Table(name="T_SYSTEM_AREA_DICT")
public class AreaDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("区域名称")
    private String areaName;

    public AreaDict() {
        this.setId(StringUtil.generator());
    }

    @Column(name="serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name="area_name")
    public String getAreaName() {
        return areaName;
    }

    public void setSerialNo(String test) {
        this.serialNo = test;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getAreaName()));
    }
}
