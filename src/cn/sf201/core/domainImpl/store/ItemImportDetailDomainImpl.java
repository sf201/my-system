package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemImportDetailDomain")
public class ItemImportDetailDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemImportDetailDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.ItemImportDetail");
    }
}
