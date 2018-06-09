package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_store_item_stock")
@InitNameAnnotation("物品库存表")
public class ItemStock extends BaseObject {
    @InitFieldAnnotation("仓库代码")
    private String storeCode;
    @InitFieldAnnotation("物品代码")
    private String itemCode;
    @InitFieldAnnotation("物品型号")
    private String itemSpec;
    @InitFieldAnnotation("最后一次入库、出库单号")
    private String documentNo;
    @InitFieldAnnotation("物品数量")
    private long amount;

    public ItemStock() {
        this.id = StringUtil.generator();
    }

    @Column(name="store_code")
    public String getStoreCode() {
        return storeCode;
    }

    @Column(name="item_code")
    public String getItemCode() {
        return itemCode;
    }

    @Column(name="item_spec")
    public String getItemSpec() {
        return itemSpec;
    }

    @Column(name="document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Column(name="amount")
    public long getAmount() {
        return amount;
    }


    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
