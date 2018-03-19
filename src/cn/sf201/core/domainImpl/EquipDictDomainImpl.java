package cn.sf201.core.domainImpl;

import cn.sf201.core.domain.BaseDomain;
import org.springframework.stereotype.Component;

/**
 * Created by sf201 on 9/11/2017.
 */
@Component("equipDictDomain")
public class EquipDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public EquipDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.EquipDict");
    }
}
