package cn.sf201.core.service;

import cn.sf201.core.domain.AreaDictDomain;
import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.entity.*;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.vo.DictSingleVo;
import cn.sf201.core.vo.DictVo;
import cn.sf201.core.vo.EquipDictVo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sf201 on 9/11/2017.
 */
@Service("dictService")
@Transactional
public class DictService {
    @Resource(name="areaDictDomain")
    private AreaDictDomain areaDictDomain;

    @Resource(name="erectorDictDomain")
    private BaseDomain erectorDictDomain;
    @Resource(name="priceDictDomain")
    private BaseDomain priceDictDomain;
    @Resource(name="storeDictDomain")
    private BaseDomain storeDictDomain;
    @Resource(name="fixReporterDictDomain")
    private BaseDomain fixReporterDictDomain;
    @Resource(name="fixTypeDictDomain")
    private BaseDomain fixTypeDictDomain;
    @Resource(name="fixItemDictDomain")
    private BaseDomain fixItemDictDomain;
    @Resource(name="configTypeDictDomain")
    private BaseDomain configTypeDictDomain;
    @Resource(name="exchangeReasonDictDomain")
    private BaseDomain exchangeReasonDictDomain;
    @Resource(name="exchangeRecordDictDomain")
    private BaseDomain exchangeRecordDictDomain;
    @Resource(name="faultDictDomain")
    private BaseDomain faultDictDomain;
    @Resource(name="punishmentDictDomain")
    private BaseDomain punishmentDictDomain;
    @Resource(name="equipDictDomain")
    private BaseDomain equipDictDomain;
    @Resource(name="equipAccessoriesDomain")
    private BaseDomain equipAccessoriesDomain;
    /**
     * 获取区域字典
     * @return
     * @throws RequestProcessException
     */
    public List<AreaDict> getAreaDict() throws RequestProcessException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AreaDict.class);
        criteria.addOrder(Order.asc("serialNo"));
        List<AreaDict> list = areaDictDomain.queryByCriteria(criteria);
        return list;
    }

    /**
     * 保存区域字典
     * @param dictVo
     * @return
     * @throws RequestProcessException
     */
    public boolean saveAreaDict(DictVo<AreaDict> dictVo) throws RequestProcessException {
        try {
            if (dictVo.getAddList() != null && dictVo.getAddList().size() > 0) {
                for (AreaDict dict : dictVo.getAddList()) {
                    areaDictDomain.merge(dict);
                }
            }
            if (dictVo.getDelList() != null && dictVo.getDelList().size() > 0) {
                areaDictDomain.deletes(dictVo.getDelList());
            }
            return true;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    /**
     * 获取字典数据
     * @param clazz
     * @param <T>
     * @return
     * @throws RequestProcessException
     */
    public <T> List<T> getDict(Class<T> clazz) throws RequestProcessException {
        BaseDomain domain = getDomainByClassName(clazz.getName());
        if (domain == null) {
            throw new RequestProcessException("获取数据操作对象失败！");
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        List<T> list = erectorDictDomain.queryByCriteria(criteria);
        return list;
    }
    /**
     * 保存字典数据
     * @param dictVo
     * @return
     * @throws RequestProcessException
     */
    public <T extends BaseObject>  boolean saveDict(DictVo<T> dictVo, Class<T> clazz) throws RequestProcessException {
        BaseDomain domain = getDomainByClassName(clazz.getName());
        if (domain == null) {
            throw new RequestProcessException("获取数据操作对象失败！");
        }
        try {
            if (dictVo.getAddList() != null && dictVo.getAddList().size() > 0) {
                for (T dict : dictVo.getAddList()) {
                    domain.merge(dict);
                }
            }
            if (dictVo.getDelList() != null && dictVo.getDelList().size() > 0) {
                domain.deletes(dictVo.getDelList());
            }
            return true;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    /**
     * 保存或删除单个字典
     * @param dictVo
     * @param clazz
     * @param <T>
     * @return
     * @throws RequestProcessException
     */
    public <T extends BaseObject> T saveDict(DictSingleVo<T> dictVo, Class<T> clazz) throws RequestProcessException {
        BaseDomain domain = getDomainByClassName(clazz.getName());
        if (domain == null) {
            throw new RequestProcessException("获取数据操作对象失败！");
        }
        try {
            if ("delete".equals(dictVo.getOper())) {
                domain.delete(dictVo.getDict());
                return null;
            } else {
                domain.merge(dictVo.getDict());
                return dictVo.getDict();
            }
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    /**
     * 删除单个字典
     * @param dict
     * @param clazz
     * @param <T>
     * @throws RequestProcessException
     */
    public <T extends BaseObject> void deleteDict(T dict,Class<T> clazz) throws RequestProcessException {
        BaseDomain domain = this.getDomainByClassName(clazz.getName());
        if (domain == null) {
            throw new RequestProcessException("获取数据操作对象失败！");
        }
        try {
            domain.delete(dict);
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    /**
     * 获取设备名录信息
     * @param para
     * @return
     * @throws RequestProcessException
     */
    public List<EquipDict> getEquipDictList(Map<String,Object> para) throws RequestProcessException {

        DetachedCriteria criteria = DetachedCriteria.forClass(EquipDict.class);
        List<EquipDict> list = equipDictDomain.queryByCriteria(criteria);
        for (EquipDict item : list) {
            DetachedCriteria detailCriteria = DetachedCriteria.forClass(EquipAccessories.class);
            detailCriteria.add(Restrictions.eq("equipId", item.getId()));
            List<EquipAccessories> detailList = equipAccessoriesDomain.queryByCriteria(detailCriteria);
            item.setAccessoriesList(detailList);
        }
        return list;
    }

    public boolean saveEquipDict(EquipDict equipDict) throws RequestProcessException {
        try {
            equipDictDomain.merge(equipDict);
            if (equipDict.getAccessoriesList() != null && equipDict.getAccessoriesList().size() > 0) {
                for (EquipAccessories item : equipDict.getAccessoriesList()) {
                    equipAccessoriesDomain.merge(item);
                }
            }
            if (equipDict.getDeleteAccessories() != null && equipDict.getDeleteAccessories().size() > 0) {
                equipAccessoriesDomain.deletes(equipDict.getDeleteAccessories());
            }
            return true;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    public boolean deleteEquipDictList(List<String> ids) throws RequestProcessException {
        try {
            if (ids == null || ids.size() <= 0) {
                throw new RequestProcessException("没有需要删除的项目！");
            }
            String idString = "";
            for (String id : ids) {
                idString += "'" + id + "',";
            }
            idString = idString.substring(0, idString.lastIndexOf(",") - 1);
            equipDictDomain.deletes(ids);
            List<String> para = new ArrayList<String>();
            para.add(idString);
            equipAccessoriesDomain.executeBySQL("delete EquipAccessories a where a.equipId in (?)", para);
            return true;
        } catch (Exception ex) {
            throw new RequestProcessException(ex.getMessage());
        }
    }

    private BaseDomain getDomainByClassName(String className) {
        if (className.equals("cn.sf201.core.entity.AreaDict")) {
            return areaDictDomain;
        } else if (className.equals("cn.sf201.core.entity.ErectorDict")) {
            return erectorDictDomain;
        } else if (className.equals("cn.sf201.core.entity.PriceDict")) {
            return priceDictDomain;
        }else if (className.equals("cn.sf201.core.entity.StoreDict")) {
            return storeDictDomain;
        }else if (className.equals("cn.sf201.core.entity.FixReporterDict")) {
            return fixReporterDictDomain;
        }else if (className.equals("cn.sf201.core.entity.FixTypeDict")) {
            return fixTypeDictDomain;
        }else if (className.equals("cn.sf201.core.entity.FixItemDict")) {
            return fixItemDictDomain;
        }else if (className.equals("cn.sf201.core.entity.ConfigTypeDict")) {
            return configTypeDictDomain;
        }else if (className.equals("cn.sf201.core.entity.ExchangeReasonDict")) {
            return exchangeReasonDictDomain;
        }else if (className.equals("cn.sf201.core.entity.ExchangeRecordDict")) {
            return exchangeRecordDictDomain;
        }else if (className.equals("cn.sf201.core.entity.FaultDict")) {
            return faultDictDomain;
        }else if (className.equals("cn.sf201.core.entity.PunishmentDict")) {
            return punishmentDictDomain;
        }
        return null;
    }
}
