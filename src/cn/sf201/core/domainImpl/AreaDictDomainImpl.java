package cn.sf201.core.domainImpl;

import cn.sf201.core.domain.AreaDictDomain;
import org.springframework.stereotype.Component;

/**
 * Created by sf201 on 9/11/2017.
 */
@Component("areaDictDomain")
public class AreaDictDomainImpl extends BaseDomainImpl implements AreaDictDomain {
    public AreaDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.AreaDict");
    }
}
