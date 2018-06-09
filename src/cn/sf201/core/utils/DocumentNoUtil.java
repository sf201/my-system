package cn.sf201.core.utils;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.domainImpl.store.ItemImportDetailDomainImpl;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component("documentNoUtil")
public class DocumentNoUtil {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public String getImportDocumentNo() {
        return getDocumentNo("t_store_item_import_master");
    }
    public String getDeliverDocumentNo() {
        return getDocumentNo("t_store_deliver_bill_master");
    }
    public String getDefectiveProductDocumentNo() {
        return getDocumentNo("t_store_defective_product_master");
    }
    public String getExportDocumentNo() {
        return getDocumentNo("t_store_export_master");
    }
    public String getDefectiveRepairDocumentNo() {
        return getDocumentNo("t_store_defective_repair_master");
    }
    public String getDefectiveDamageDocumentNo() {
        return getDocumentNo("t_store_defective_damage_master");
    }

    private String getDocumentNo(String tableName) {
        String dateStr = DateUtil.getCurDate().replace("-","");
        String sql = "SELECT ifnull(max(document_no),concat(:dateStr,'0000'))+1 from " +tableName+" \n"+
                "where document_no like concat(:dateStr,'%')";
        Map<String, String> params = new HashMap<>();
        params.put("dateStr", dateStr);
        return namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
    }
}
