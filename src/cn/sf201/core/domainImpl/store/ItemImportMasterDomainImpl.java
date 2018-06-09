package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemImportMasterDomain")
public class ItemImportMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemImportMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.ItemImportMaster");
    }
}
