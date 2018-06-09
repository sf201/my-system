package cn.sf201.core.domainImpl.store;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemExportMasterDomain")
public class ItemExportMasterDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemExportMasterDomainImpl() {
        this.setClassName("cn.sf201.core.entity.store.ExportMaster");
    }
}
