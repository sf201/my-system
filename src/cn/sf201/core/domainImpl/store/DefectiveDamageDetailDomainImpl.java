package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveDamageDetailDomain")
public class DefectiveDamageDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveDamageDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveDamageDetail");
    }
}
