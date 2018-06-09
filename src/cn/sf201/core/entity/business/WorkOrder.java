package cn.sf201.core.entity.business;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@InitNameAnnotation("派工单")
@Table(name="t_business_work_order")
public class WorkOrder extends BaseObject {
    private String workOrderNo;
    private String houseAddress;
    private String houseContractNo;
    private String specialistName;
    private String specialistPhone;
    private String configType;
    private String managerName;
    private String managerPhone;
    private Date orderDate;
    private String doorToDoor;
    private String finish;
    private String worker;
    private String keysOrKeyWord;
    private String settleNo;

    public WorkOrder() {
        this.id = StringUtil.generator();
    }

    @Column(name="work_order_no")
    public String getWorkOrderNo() {
        return workOrderNo;
    }

    @Column(name="house_address")
    public String getHouseAddress() {
        return houseAddress;
    }

    @Column(name="house_contract_no")
    public String getHouseContractNo() {
        return houseContractNo;
    }

    @Column(name="specialist_name")
    public String getSpecialistName() {
        return specialistName;
    }

    @Column(name="specialist_phone")
    public String getSpecialistPhone() {
        return specialistPhone;
    }

    @Column(name="config_type")
    public String getConfigType() {
        return configType;
    }

    @Column(name="manager_name")
    public String getManagerName() {
        return managerName;
    }

    @Column(name="manager_phone")
    public String getManagerPhone() {
        return managerPhone;
    }

    @Column(name="order_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOrderDate() {
        return orderDate;
    }

    @Column(name="door_to_door")
    public String getDoorToDoor() {
        return doorToDoor;
    }

    @Column(name="finish")
    public String getFinish() {
        return finish;
    }

    @Column(name="worker")
    public String getWorker() {
        return worker;
    }

    @Column(name="keys_or_key_word")
    public String getKeysOrKeyWord() {
        return keysOrKeyWord;
    }

    @Column(name="settle_no")
    public String getSettleNo() {
        return settleNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public void setHouseContractNo(String houseContractNo) {
        this.houseContractNo = houseContractNo;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public void setSpecialistPhone(String specialistPhone) {
        this.specialistPhone = specialistPhone;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDoorToDoor(String doorToDoor) {
        this.doorToDoor = doorToDoor;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public void setKeysOrKeyWord(String keysOrKeyWord) {
        this.keysOrKeyWord = keysOrKeyWord;
    }

    public void setSettleNo(String settleNo) {
        this.settleNo = settleNo;
    }
}
