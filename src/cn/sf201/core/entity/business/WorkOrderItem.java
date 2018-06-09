package cn.sf201.core.entity.business;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.InitNameAnnotation;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@InitNameAnnotation("派工单明细项目")
@Table(name="t_business_work_order_item")
public class WorkOrderItem extends BaseObject {
    private String roomNo;
    private String type;
}
