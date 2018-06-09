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
 * Created by sf201 on 10/12/2017.
 */
@Entity
@Table(name = "t_system_equip_accessories")
@InitNameAnnotation("物品配件明细")
public class EquipAccessories extends DictObject {
    @InitFieldAnnotation("对应设备Id")
    private String equipId;
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("类别")
    private String equipType;
    @InitFieldAnnotation("名称")
    private String name;
    @InitFieldAnnotation("单价")
    private float price;
    @InitFieldAnnotation("默认数量")
    private float defaultCount;

    public EquipAccessories() {
        this.setId(StringUtil.generator());
    }

    @Column(name="equip_id")
    public String getEquipId() {
        return equipId;
    }

    @Column(name="serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name="equip_type")
    public String getEquipType() {
        return equipType;
    }

    @Column(name="`name`")
    public String getName() {
        return name;
    }

    @Column(name="price")
    public float getPrice() {
        return price;
    }

    @Column(name="default_count")
    public float getDefaultCount() {
        return defaultCount;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDefaultCount(float defaultCount) {
        this.defaultCount = defaultCount;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getName()));
    }
}
