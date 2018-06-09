package cn.sf201.core.entity.system;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.entity.DictObject;
import cn.sf201.core.utils.InitFieldAnnotation;
import cn.sf201.core.utils.InitNameAnnotation;
import cn.sf201.core.utils.Pinyin4JUtils;
import cn.sf201.core.utils.StringUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_system_item_dict")
@InitNameAnnotation("物品字典表")
public class ItemDict extends DictObject {
    @InitFieldAnnotation("物品代码")
    private String itemCode;
    @InitFieldAnnotation("物品名称")
    private String itemName;
    @InitFieldAnnotation("型号")
    private String itemSpec;

    public ItemDict() {
        this.id = StringUtil.generator();
    }

    @Override
    public void setInputCode() {
        this.setInputCode(Pinyin4JUtils.cn2PYInitial(this.getItemName()));
    }

    @Column(name="item_code")
    public String getItemCode() {
        return itemCode;
    }

    @Column(name="item_name")
    public String getItemName() {
        return itemName;
    }

    @Column(name="item_spec")
    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }
}
