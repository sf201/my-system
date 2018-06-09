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
@Table(name="T_SYSTEM_STORE_DICT")
@InitNameAnnotation("仓库名录字典")
public class StoreDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("名称")
    private String name;
    @InitFieldAnnotation("仓库属性 0，正常库房；1，不良品库房")
    private String storeProperty;

    public StoreDict() {
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

    @Column(name="store_property")
    public String getStoreProperty() {
        return storeProperty;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoreProperty(String storeProperty) {
        this.storeProperty = storeProperty;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getName()));
    }
}


