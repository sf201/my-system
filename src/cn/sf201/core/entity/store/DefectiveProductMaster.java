package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_store_defective_product_master")
public class DefectiveProductMaster extends BaseObject {
    private String documentNo;//单据编号
    private String deliverDocumentNo;//发货单号
    private Date registDateTime;//登记日期
    private String operator;//操作员
    private String storeCode;//录入库房
    private boolean acked;//是否已经确认登记
    private String status;//不良品状态 0，新登记 ，1，已修复，2，已报废
    private List<DefectiveProductDetail> listDetail;
    private List<String> listDetailDelete;

    public DefectiveProductMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name = "document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Column(name="deliver_document_no")
    public String getDeliverDocumentNo() {
        return deliverDocumentNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "regist_date_time")
    public Date getRegistDateTime() {
        return registDateTime;
    }

    @Column(name="operator")
    public String getOperator() {
        return operator;
    }

    @Column(name="store_code")
    public String getStoreCode() {
        return storeCode;
    }

    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }

    @Column(name="status")
    public String getStatus() {
        return status;
    }

    @Transient
    public List<DefectiveProductDetail> getListDetail() {
        return listDetail;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setDeliverDocumentNo(String deliverDocumentNo) {
        this.deliverDocumentNo = deliverDocumentNo;
    }

    public void setRegistDateTime(Date registDateTime) {
        this.registDateTime = registDateTime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setAcked(boolean acked) {
        this.acked = acked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setListDetail(List<DefectiveProductDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }
}
