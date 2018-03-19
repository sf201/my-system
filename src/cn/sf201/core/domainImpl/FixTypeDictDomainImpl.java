package cn.sf201.core.domainImpl;

import cn.sf201.core.domain.BaseDomain;
import org.springframework.stereotype.Component;

/**
 * Created by sf201 on 9/11/2017.
 */
@Component("fixTypeDictDomain")
public class FixTypeDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public FixTypeDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.FixTypeDict");
    }
}
