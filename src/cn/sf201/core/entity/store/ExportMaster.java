package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_store_export_master")
public class ExportMaster extends BaseObject {
    @InitFieldAnnotation("发货单号")
    private String documentNo;
    @InitFieldAnnotation("接收方")
    private String receiver;
    @InitFieldAnnotation("发货日期")
    private Date exportDateTime;
    @InitFieldAnnotation("联系电话")
    private String phoneNo;
    @InitFieldAnnotation("备注")
    private String memo;
    @InitFieldAnnotation("仓库代码")
    private String storeCode;
    @InitFieldAnnotation("操作员")
    private String operator;
    @InitFieldAnnotation("是否确认")
    private boolean acked;


    private List<ExportDetail> listDetail;
    private List<String> listDetailDelete;

    public ExportMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name="document_no")
    public String getDocumentNo() {
        return documentNo;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name="export_date_time")
    public Date getExportDateTime() {
        return exportDateTime;
    }

    @Column(name="phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    @Column(name="memo")
    public String getMemo() {
        return memo;
    }

    @Column(name="receiver")
    public String getReceiver() {
        return receiver;
    }

    @Column(name="store_code")
    public String getStoreCode() {
        return storeCode;
    }

    @Column(name="operator")
    public String getOperator() {
        return operator;
    }

    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }



    @Transient
    public List<ExportDetail> getListDetail() {
        return listDetail;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }


    public void setExportDateTime(Date appointDeliverDateTime) {
        this.exportDateTime = appointDeliverDateTime;
    }


    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setListDetail(List<ExportDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }

    public void setAcked(boolean acked) {
        this.acked = acked;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
