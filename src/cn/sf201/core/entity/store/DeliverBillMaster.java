package cn.sf201.core.entity.store;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.StringUtil;
import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_store_deliver_bill_master")
public class DeliverBillMaster extends BaseObject {
    @InitFieldAnnotation("发货单号")
    private String documentNo;
    @InitFieldAnnotation("地址")
    private String address;
    @InitFieldAnnotation("指定送货日期")
    private Date deliverDateTime;
    @InitFieldAnnotation("钥匙或密码")
    private String keyCode;
    @InitFieldAnnotation("是否加急")
    private String emergency;
    @InitFieldAnnotation("联系电话")
    private String phoneNo;
    @InitFieldAnnotation("房源编号")
    private String houseResourceNo;
    @InitFieldAnnotation("是否确认")
    private boolean acked;


    private List<DeliverBillDetail> listDetail;
    private List<String> listDetailDelete;

    public DeliverBillMaster() {
        this.id = StringUtil.generator();
    }

    @Column(name="document_no")
    public String getDocumentNo() {
        return documentNo;
    }

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name="deliver_date_time")
    public Date getDeliverDateTime() {
        return deliverDateTime;
    }

    @Column(name="key_code")
    public String getKeyCode() {
        return keyCode;
    }

    @Column(name="emergency")
    public String getEmergency() {
        return emergency;
    }

    @Column(name="phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    @Column(name="house_resource_no")
    public String getHouseResourceNo() {
        return houseResourceNo;
    }

    @Column(name="acked")
    public boolean isAcked() {
        return acked;
    }



    @Transient
    public List<DeliverBillDetail> getListDetail() {
        return listDetail;
    }

    @Transient
    public List<String> getListDetailDelete() {
        return listDetailDelete;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setDeliverDateTime(Date appointDeliverDateTime) {
        this.deliverDateTime = appointDeliverDateTime;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setHouseResourceNo(String houseResourceNo) {
        this.houseResourceNo = houseResourceNo;
    }

    public void setListDetail(List<DeliverBillDetail> listDetail) {
        this.listDetail = listDetail;
    }

    public void setListDetailDelete(List<String> listDetailDelete) {
        this.listDetailDelete = listDetailDelete;
    }

    public void setAcked(boolean acked) {
        this.acked = acked;
    }
}
