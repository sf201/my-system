package cn.sf201.core.service;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.entity.store.ItemStock;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.utils.MapUtil;
import cn.sf201.core.utils.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ItemStockService {

    @Resource(name="itemStockDomain")
    private BaseDomain itemStockDomain;

    public void UpdateStock(String documentNo, String storeCode, String itemCode, String itemSpec, long amount) {
        String updateSql = "update t_store_item_stock a set a.amount=a.amount+?,a.document_no=?" +
                "where a.store_code=? and a.item_code=? and a.item_spec=?";
        String insertSql = "insert into t_store_item_stock(amount,document_no,store_code,item_code,item_spec,id)" +
                "values(?,?,?,?,?,?)";
        List params = new ArrayList();
        params.add(amount);
        params.add(documentNo);
        params.add(storeCode);
        params.add(itemCode);
        params.add(itemSpec);
        int rows = itemStockDomain.executeBySQL(updateSql,params);
        if (rows <= 0) {
            params.add(StringUtil.generator());
            rows = itemStockDomain.executeBySQL(insertSql, params);
        }
    }

    public void updateStock(String storeCode, String itemCode, String itemSpec, long amount) throws RequestProcessException {
        Map<String, Object> map = new HashMap<>();
        map.put("storeCode", storeCode);
        map.put("itemCode", itemCode);
        map.put("itemSpec", itemSpec);
        ItemStock stock;
        List<ItemStock> stockList = this.getItemStockList(map);
        if (stockList == null||stockList.size()<=0) {//库存中无记录
            if (amount > 0) {//入库
                stock = new ItemStock();
                stock.setStoreCode(storeCode);
                stock.setItemCode(itemCode);
                stock.setItemSpec(itemSpec);
                stock.setAmount(amount);
            } else {
                throw new RequestProcessException("库存中不存在代码为[" + itemCode + "]的物品!");
            }
        } else {//库存中有记录
            if (stockList.size() == 1) {//库存记录正常
                stock = stockList.get(0);
                if (amount > 0) {//入库
                    stock.setAmount(stock.getAmount() + amount);
                } else {
                    if (stock.getAmount() + amount < 0) {
                        throw new RequestProcessException("物品[" + itemCode + "]库存不足！");
                    } else {
                        stock.setAmount(stock.getAmount()+amount);
                    }
                }
            } else {//库存记录异常
                throw new RequestProcessException("仓库中存在多条相同物品记录！库房代码:[" + storeCode + "],物品代码：[" + itemCode + "]");
            }
        }
        itemStockDomain.merge(stock);
    }

    public List<ItemStock> getItemStockList(Map<String,Object> para) throws RequestProcessException {
        String storeCode = MapUtil.getStringValue("storeCode",para);
        String itemCode = MapUtil.getStringValue("itemCode", para);
        String itemSpec = MapUtil.getStringValue("itemSpec", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(ItemStock.class);
        if (!StringUtil.isNullOrEmpty(storeCode)) {
            criteria.add(Restrictions.eq("storeCode", storeCode));
        }
        if (!StringUtil.isNullOrEmpty(itemCode)) {
            criteria.add(Restrictions.eq("itemCode", itemCode));
        }
        if (!StringUtil.isNullOrEmpty(itemSpec)) {
            criteria.add(Restrictions.eq("itemSpec", itemSpec));
        }
        return itemStockDomain.queryByCriteria(criteria);
    }
}
