package cn.sf201.core.vo;

import cn.sf201.core.entity.system.EquipDict;

import java.util.List;

/**
 * Created by sf201 on 10/12/2017.
 */
public class EquipDictVo {
    private List<EquipDict> addList;
    private List<String> delList;

    public List<EquipDict> getAddList() {
        return addList;
    }

    public void setAddList(List<EquipDict> addList) {
        this.addList = addList;
    }

    public List<String> getDelList() {
        return delList;
    }

    public void setDelList(List<String> delList) {
        this.delList = delList;
    }
}
