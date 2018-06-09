package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("deliverBillMasterDomain")
public class DeliverBillMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DeliverBillMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DeliverBillMaster");
    }
}
