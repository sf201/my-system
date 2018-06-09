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
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by sf201 on 9/5/2017.
 */
@Entity
@InitNameAnnotation("设备名录")
@Table(name = "T_SYSTEM_EQUIP_DICT")
public class EquipDict extends DictObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("类别")
    private String equipType;
    @InitFieldAnnotation("名称")
    private String equipName;
    @InitFieldAnnotation("品类")
    private String productType;
    @InitFieldAnnotation("规格型号")
    private String spec;
    @InitFieldAnnotation("单价")
    private float price;
    @InitFieldAnnotation("默认数量")
    private float defaultCount;

    private List<EquipAccessories> accessoriesList;

    private List<String> deleteAccessories;

    public EquipDict() {
        this.setId(StringUtil.generator());
    }

    @Column(name="serial_no")
    public String getSerialNo() {
        return serialNo;
    }
    @Column(name="equip_type")
    public String getEquipType() {
        return equipType;
    }
    @Column(name="equip_name")
    public String getEquipName() {
        return equipName;
    }
    @Column(name="product_type")
    public String getProductType() {
        return productType;
    }
    @Column(name="spec")
    public String getSpec() {
        return spec;
    }
    @Column(name="price")
    public float getPrice() {
        return price;
    }
    @Column(name="default_count")
    public float getDefaultCount() {
        return defaultCount;
    }

    @Transient
    public List<EquipAccessories> getAccessoriesList() {
        return accessoriesList;
    }

    @Transient
    public List<String> getDeleteAccessories() {
        return deleteAccessories;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDefaultCount(float defaultCount) {
        this.defaultCount = defaultCount;
    }

    public void setAccessoriesList(List<EquipAccessories> accessoriesList) {
        this.accessoriesList = accessoriesList;
    }

    public void setDeleteAccessories(List<String> deleteAccessories) {
        this.deleteAccessories = deleteAccessories;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getEquipName()));
    }
}
