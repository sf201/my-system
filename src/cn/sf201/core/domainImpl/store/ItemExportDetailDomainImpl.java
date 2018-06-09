package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemExportDetailDomain")
public class ItemExportDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemExportDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.ExportDetail");
    }
}
