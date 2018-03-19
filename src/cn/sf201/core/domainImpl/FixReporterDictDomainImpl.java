package cn.sf201.core.domainImpl;

import cn.sf201.core.domain.BaseDomain;
import org.springframework.stereotype.Component;

/**
 * Created by sf201 on 9/11/2017.
 */
@Component("fixReporterDictDomain")
public class FixReporterDictDomainImpl extends BaseDomainImpl implements BaseDomain {
    public FixReporterDictDomainImpl() {
        this.setClassName("cn.sf201.core.entity.FixReporter");
    }
}
