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
 * Created by sf201 on 9/25/2017.
 */
@Entity
@Table(name="t_system_fix_item_dict")
@InitNameAnnotation("维修项目字典")
public class FixItemDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("维修类别")
    private String fixType;
    @InitFieldAnnotation("维修项目名称")
    private String name;

    public FixItemDict() {
        this.setId(StringUtil.generator());
    }

    @Column(name="serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name="fix_type")
    public String getFixType() {
        return fixType;
    }

    @Column(name = "`name`")
    public String getName() {
        return name;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setFixType(String fixType) {
        this.fixType = fixType;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getName()));
    }
}
