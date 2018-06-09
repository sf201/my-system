package cn.sf201.core.entity.system;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.entity.DictObject;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.Pinyin4JUtils;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@InitNameAnnotation("房源名录")
@Table(name="t_system_house_resource")
public class HouseResource extends DictObject {
    private String areaCode;
    private String communityName;
    private String suffix;
    private String building;
    private String unit;
    private String roomNo;
    private String houseResourceNo;
    private String houseContractNo;
    private String specialistName;
    private String specialistPhone;
    private String specialistPhone1;
    private String managerName;
    private String managerPhone;
    private String managerPhone1;

    public HouseResource() {
        this.id = StringUtil.generator();
    }

    @Column(name="area_code")
    public String getAreaCode() {
        return areaCode;
    }

    @Column(name="community_name")
    public String getCommunityName() {
        return communityName;
    }

    @Column(name="suffix")
    public String getSuffix() {
        return suffix;
    }

    @Column(name="building")
    public String getBuilding() {
        return building;
    }

    @Column(name="unit")
    public String getUnit() {
        return unit;
    }

    @Column(name="room_no")
    public String getRoomNo() {
        return roomNo;
    }

    @Column(name="house_resource_no")
    public String getHouseResourceNo() {
        return houseResourceNo;
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

    @Column(name="specialist_phone1")
    public String getSpecialistPhone1() {
        return specialistPhone1;
    }

    @Column(name="manager_name")
    public String getManagerName() {
        return managerName;
    }

    @Column(name="manager_phone")
    public String getManagerPhone() {
        return managerPhone;
    }

    @Column(name="manager_phone1")
    public String getManagerPhone1() {
        return managerPhone1;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setHouseResourceNo(String houseResourceNo) {
        this.houseResourceNo = houseResourceNo;
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

    public void setSpecialistPhone1(String specialistPhone1) {
        this.specialistPhone1 = specialistPhone1;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public void setManagerPhone1(String managerPhone1) {
        this.managerPhone1 = managerPhone1;
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getCommunityName()));
    }
}
