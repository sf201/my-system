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
@Table(name="t_system_fix_reporter_dict")
@InitNameAnnotation("报修人员名录")
public class FixReporterDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("姓名")
    private String name;

    public FixReporterDict() {
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

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getName()));
    }
}
