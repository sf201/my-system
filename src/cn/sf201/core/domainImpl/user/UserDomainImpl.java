package cn.sf201.core.domainImpl.user;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.BaseDomainImpl;
import org.springframework.stereotype.Component;

@Component("userDomain")
public class UserDomainImpl extends BaseDomainImpl implements BaseDomain {
    public UserDomainImpl() {
        this.setClassName("cn.sf201.core.entity.user.User");
    }
}
