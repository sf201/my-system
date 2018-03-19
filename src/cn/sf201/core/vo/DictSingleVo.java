package cn.sf201.core.vo;

public class DictSingleVo<T> {
    private T dict;
    private String oper;//操作描述,del为删除,save为保存

    public T getDict() {
        return dict;
    }

    public void setDict(T dict) {
        this.dict = dict;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}
