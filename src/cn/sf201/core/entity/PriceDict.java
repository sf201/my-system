package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sf201 on 9/5/2017.
 */
@Entity
@InitNameAnnotation("打包价外费用名录")
@Table(name = "T_SYSTEM_PRICE_DICT")
public class PriceDict extends BaseObject {
    @InitFieldAnnotation("序号")
    private String serialNo;
    @InitFieldAnnotation("费用名称")
    private String priceName;
    @InitFieldAnnotation("单位")
    private String units;
    @InitFieldAnnotation("单价")
    private float price;
    @InitFieldAnnotation("备注")
    private float memo;

    public PriceDict() {
        this.setId(StringUtil.generator());
    }

    @Column(name = "serial_no")
    public String getSerialNo() {
        return serialNo;
    }

    @Column(name="price_name")
    public String getPriceName() {
        return priceName;
    }
    @Column(name="units")
    public String getUnits() {
        return units;
    }
    @Column(name="price")
    public float getPrice() {
        return price;
    }
    @Column(name="memo")
    public float getMemo() {
        return memo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMemo(float memo) {
        this.memo = memo;
    }
}
