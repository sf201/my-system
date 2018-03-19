package cn.sf201.core.vo;

import cn.sf201.core.entity.AreaDict;

import java.util.List;

/**
 * Created by sf201 on 9/12/2017.
 */
public class DictVo<T> {
    private List<T> addList;
    private List<String> delList;

    public List<T> getAddList() {
        return addList;
    }

    public void setAddList(List<T> addList) {
        this.addList = addList;
    }

    public List<String> getDelList() {
        return delList;
    }

    public void setDelList(List<String> delList) {
        this.delList = delList;
    }
}
