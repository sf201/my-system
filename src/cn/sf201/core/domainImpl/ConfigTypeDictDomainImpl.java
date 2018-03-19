package cn.sf201.core.domainImpl;

import cn.sf201.core.domain.BaseDomain;
import org.springframework.stereotype.Component;

/**
 * Created by sf201 on 9/11/2017.
 */
@Component("configTypeDictDomain")
public class ConfigTypeDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public ConfigTypeDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.ConfigTypeDict");
    }
}
