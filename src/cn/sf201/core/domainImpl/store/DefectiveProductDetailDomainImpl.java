package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("defectiveProductDetailDomain")
public class DefectiveProductDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public DefectiveProductDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.DefectiveProductDetail");
    }
}
