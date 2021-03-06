package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemStockDomain")
public class ItemStockDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemStockDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.ItemStock");
    }
}
