package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveDamageMasterDomain")
public class DefectiveDamageMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveDamageMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveDamageMaster");
    }
}
