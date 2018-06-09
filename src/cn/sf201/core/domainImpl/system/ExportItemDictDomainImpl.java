package cn.sf201.core.domainImpl.system;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("exportItemDictDomain")
public class ExportItemDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ExportItemDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.system.ExportTypeDict");
    }
}
