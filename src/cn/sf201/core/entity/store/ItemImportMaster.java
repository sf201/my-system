package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_store_item_import_master")
@InitNameAnnotation("物品入库单")
public class ItemImportMaster extends BaseObject {
    @InitFieldAnnotation("入库单号")
    private String documentNo;
    @InitFieldAnnotation("入库日期")
    private Date importDateTime;
    @InitFieldAnnotation("仓库代码")
    private String storeCode;
    @InitFieldAnnotation("操作员")
    private String operator;
    @InitFieldAnnotation("来源")
    private String importFrom;
    @InitFieldAnnotation("是否确认")
    private boolean acked;
    private List<ItemImportDetail> listDetail;
    private List<String> listDetailDelete;

    public ItemImportMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name = "document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Column(name = "import_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getImportDateTime() {
        return importDateTime;
    }

    @Column(name = "store_code")
    public String getStoreCode() {
        return storeCode;
    }

    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    @Column(name = "import_from")
    public String getImportFrom() {
        return importFrom;
    }

    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    @Transient
    public List<ItemImportDetail> getListDetail() {
        return listDetail;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setImportDateTime(Date importDateTime) {
        this.importDateTime = importDateTime;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setImportFrom(String importFrom) {
        this.importFrom = importFrom;
    }


    public void setListDetail(List<ItemImportDetail> listDetail) {
        this.listDetail = listDetail;
    }


    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }

    public void setAcked(boolean acked) {
        this.acked = acked;
    }
}
