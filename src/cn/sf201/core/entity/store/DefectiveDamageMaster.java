package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_store_defective_damage_master")
public class DefectiveDamageMaster extends BaseObject {
    private String documentNo;//单据编号
    private Date damageDateTime;//修复日期
    private String operator;//操作员
    private String storeCode;//库房
    private boolean acked;//是否已经确认登记
    private String memo;//备注
    private List<DefectiveDamageDetail> listDetail;
    private List<String> listDetailDelete;

    public DefectiveDamageMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name = "document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "damage_date_time")
    public Date getDamageDateTime() {
        return damageDateTime;
    }

    @Column(name="operator")
    public String getOperator() {
        return operator;
    }

    @Column(name="store_code")
    public String getStoreCode() {
        return storeCode;
    }

    @Column(name="memo")
    public String getMemo() {
        return memo;
    }

    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }

    @Transient
    public List<DefectiveDamageDetail> getListDetail() {
        return listDetail;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setDamageDateTime(Date damageDateTime) {
        this.damageDateTime = damageDateTime;
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

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setListDetail(List<DefectiveDamageDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }

}
