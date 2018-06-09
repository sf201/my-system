package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("deliverBillDetailDomain")
public class DeliverBillDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DeliverBillDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DeliverBillDetail");
    }
}
