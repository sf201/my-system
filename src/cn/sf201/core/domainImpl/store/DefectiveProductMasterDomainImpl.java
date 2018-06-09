package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveProductMasterDomain")
public class DefectiveProductMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveProductMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveProductMaster");
    }
}
