package cn.sf201.core.domainImpl.system;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("itemDictDomain")
public class ItemDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ItemDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.system.ItemDict");
    }
}
