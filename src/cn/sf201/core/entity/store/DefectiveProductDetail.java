package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_store_defective_product_detail")
public class DefectiveProductDetail extends BaseObject {
    @InitFieldAnnotation("单据号码")
    private String documentNo;
    @InitFieldAnnotation("物品代码")
    private String itemCode;
    @InitFieldAnnotation("型号")
    private String itemSpec;
    @InitFieldAnnotation("数量")
    private long amount;
    @InitFieldAnnotation("备注")
    private String memo;

    public DefectiveProductDetail() {
        this.id = StringUtil.generator();
    }

    @Column(name="document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Column(name="item_code")
    public String getItemCode() {
        return itemCode;
    }

    @Column(name="item_spec")
    public String getItemSpec() {
        return itemSpec;
    }

    @Column(name="amount")
    public long getAmount() {
        return amount;
    }

    @Column(name="memo")
    public String getMemo() {
        return memo;
    }


    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
