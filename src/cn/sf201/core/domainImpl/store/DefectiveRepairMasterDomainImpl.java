package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveRepairMasterDomain")
public class DefectiveRepairMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveRepairMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveRepairMaster");
    }
}
