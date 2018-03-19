package cn.sf201.core.vo;

import cn.sf201.core.entity.AreaDict;

import java.util.List;

/**
 * Created by sf201 on 9/12/2017.
 */
public class AreaDictVo {
    private List<AreaDict> addList;
    private List<String> delList;

    public List<AreaDict> getAddList() {
        return addList;
    }

    public void setAddList(List<AreaDict> addList) {
        this.addList = addList;
    }

    public List<String> getDelList() {
        return delList;
    }

    public void setDelList(List<String> delList) {
        this.delList = delList;
    }
}
