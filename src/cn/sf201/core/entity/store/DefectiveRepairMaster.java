package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_store_defective_repair_master")
public class DefectiveRepairMaster extends BaseObject {
    private String documentNo;//单据编号
    private Date repairDateTime;//修复日期
    private String operator;//操作员
    private String defectiveStoreCode;//不良品库房
    private String storeCode;//修复后所入库房
    private boolean acked;//是否已经确认登记
    private List<DefectiveRepairDetail> listDetail;
    private List<String> listDetailDelete;

    public DefectiveRepairMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name = "document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "repair_date_time")
    public Date getRepairDateTime() {
        return repairDateTime;
    }

    @Column(name="operator")
    public String getOperator() {
        return operator;
    }

    @Column(name="defective_store_code")
    public String getDefectiveStoreCode() {
        return defectiveStoreCode;
    }
    @Column(name="store_code")
    public String getStoreCode() {
        return storeCode;
    }
    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }

    @Transient
    public List<DefectiveRepairDetail> getListDetail() {
        return listDetail;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setRepairDateTime(Date repairDateTime) {
        this.repairDateTime = repairDateTime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setDefectiveStoreCode(String defectiveStoreCode) {
        this.defectiveStoreCode = defectiveStoreCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setAcked(boolean acked) {
        this.acked = acked;
    }


    public void setListDetail(List<DefectiveRepairDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }
}
