package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveRepairDetailDomain")
public class DefectiveRepairDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveRepairDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveRepairDetail");
    }
}
