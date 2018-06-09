package cn.sf201.core.entity;

import cn.sf201.core.utils.InitFieldAnnotation;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DictObject extends BaseObject {
    @InitFieldAnnotation("输入码")
    protected String inputCode;

    @Column(name="input_code")
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public abstract void setInputCode();


}
