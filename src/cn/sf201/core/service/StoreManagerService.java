package cn.sf201.core.service;

import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.entity.store.*;
import cn.sf201.core.entity.system.ItemDict;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.utils.DateUtil;
import cn.sf201.core.utils.DocumentNoUtil;
import cn.sf201.core.utils.MapUtil;
import cn.sf201.core.utils.StringUtil;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("storeManagerService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StoreManagerService {
    @Resource(name = "documentNoUtil")
    DocumentNoUtil documentNoUtil;
    @Resource(name = "itemImportMasterDomain")
    private BaseDomain itemImportMasterDomain;
    @Resource(name = "itemImportDetailDomain")
    private BaseDomain itemImportDetailDomain;
    @Resource(name = "itemStockDomain")
    private BaseDomain itemStockDomain;
    @Resource(name = "deliverBillMasterDomain")
    private BaseDomain deliverBillMasterDomain;
    @Resource(name = "deliverBillDetailDomain")
    private BaseDomain deliverBillDetailDomain;

    @Resource(name = "itemDictDomain")
    private BaseDomain itemDictDomain;

    @Resource(name = "itemStockService")
    private ItemStockService itemStockService;

    @Resource(name = "defectiveProductMasterDomain")
    private BaseDomain defectiveProductMasterDomain;
    @Resource(name = "defectiveProductDetailDomain")
    private BaseDomain defectiveProductDetailDomain;

    @Resource(name = "itemExportMasterDomain")
    private BaseDomain itemExportMasterDomain;
    @Resource(name = "itemExportDetailDomain")
    private BaseDomain itemExportDetailDomain;
    @Resource(name = "defectiveRepairMasterDomain")
    private BaseDomain defectiveRepairMasterDomain;
    @Resource(name = "defectiveRepairDetailDomain")
    private BaseDomain defectiveRepairDetailDomain;
    @Resource(name = "defectiveDamageMasterDomain")
    private BaseDomain defectiveDamageMasterDomain;
    @Resource(name = "defectiveDamageDetailDomain")
    private BaseDomain defectiveDamageDetailDomain;

    /**
     * 获取入库单主记录列表
     *
     * @param para
     * @return
     * @throws RequestProcessException
     */
    public List<ItemImportMaster> getItemImportList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        String storeCode = MapUtil.getStringValue("storeCode", para);

        DetachedCriteria criteria = DetachedCriteria.forClass(ItemImportMaster.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("importDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("importDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(storeCode)) {
            criteria.add(Restrictions.eq("storeCode", storeCode));
        }
        criteria.addOrder(Order.desc("importDateTime"));
        List<ItemImportMaster> masterList = itemImportMasterDomain.queryByCriteria(criteria);
//        if (masterList != null && masterList.size() == 1) {
//            criteriaDetail.add(Restrictions.eq("documentNo", masterList.get(0).getDocumentNo()));
//            List<ItemImportDetail> detailList = itemImportDetailDomain.queryByCriteria(criteriaDetail);
//            masterList.get(0).setListDetail(detailList);
//        }
        return masterList;
    }

    public List<ItemImportDetail> getItemImportDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(ItemImportDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return itemImportDetailDomain.queryByCriteria(criteria);
    }


    public ItemImportMaster saveItemImport(ItemImportMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该入库单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getImportDocumentNo();
                master.setDocumentNo(documentNo);
            }
            itemImportMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (ItemImportDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    itemImportDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                itemImportDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存入库单失败！");
        }
    }

    public int ackImport(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("入库单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(ItemImportMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<ItemImportMaster> masterList = itemImportMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的入库单信息");
        }
        ItemImportMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该入库单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(ItemImportDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<ItemImportDetail> detailList = itemImportDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该入库单没有入库明细");
        }
        for (ItemImportDetail detail : detailList) {
            itemStockService.updateStock(master.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount());
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getItemCode(),detail.getItemCode(),detail.getItemSpec(),detail.getAmount());
        }
        master.setAcked(true);
        itemImportMasterDomain.merge(master);
        return 1;
    }


    public List<DeliverBillMaster> getDeliverMasterList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String houseResourceNo = MapUtil.getStringValue("houseResourceNo", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DeliverBillMaster.class);
        if (!StringUtil.isNullOrEmpty(houseResourceNo)) {
            criteria.add(Restrictions.eq("houseResourceNo", houseResourceNo));
        }
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("deliverDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("deliverDateTime", DateUtil.parseDate(stopDate)));
        }
        List<DeliverBillMaster> masterList = deliverBillMasterDomain.queryByCriteria(criteria);
//        if (masterList != null && masterList.size() == 1) {
//            criteriaDetail.add(Restrictions.eq("documentNo", masterList.get(0).getDocumentNo()));
//            List<ItemImportDetail> detailList = itemImportDetailDomain.queryByCriteria(criteriaDetail);
//            masterList.get(0).setListDetail(detailList);
//        }
        return masterList;
    }


    public List<DeliverBillDetail> getDeliverDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DeliverBillDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return deliverBillDetailDomain.queryByCriteria(criteria);
    }

    public DeliverBillMaster saveDeliver(DeliverBillMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该发货单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getDeliverDocumentNo();
                master.setDocumentNo(documentNo);
            }
            deliverBillMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (DeliverBillDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    deliverBillDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                deliverBillDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存发货单失败！");
        }
    }

    public int ackDeliver(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("发货单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(DeliverBillMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DeliverBillMaster> masterList = deliverBillMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的发货单信息");
        }
        DeliverBillMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该发货单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(DeliverBillDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DeliverBillDetail> detailList = deliverBillDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该发货单没有发货明细");
        }
        for (DeliverBillDetail detail : detailList) {
            itemStockService.updateStock(detail.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount() * -1);
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getDefectiveStoreCode(),detail.getItemCode(),detail.getItemSpec(),-detail.getAmount());
        }
        master.setAcked(true);
        deliverBillMasterDomain.merge(master);
        return 1;
    }

    /**
     * 获取某个仓库的库存信息
     *
     * @param para
     * @return
     * @throws RequestProcessException
     */
    public List<ItemDict> getItemDictBySoreCode(Map<String, Object> para) throws RequestProcessException {
        DetachedCriteria criteria = DetachedCriteria.forClass(ItemDict.class, "item");

        DetachedCriteria subCriteria = DetachedCriteria.forClass(ItemStock.class, "stock");
        String storeCode = MapUtil.getStringValue("storeCode", para);
        if (StringUtil.isNullOrEmpty(storeCode)) {
            throw new RequestProcessException("仓库代码不能为空！");
        }

//        String hql = "select a.* from ItemDict a,ItemStock b" +
//                " where a.itemCode = b.itemCode and b.storeCode='"+storeCode+"'";
        subCriteria.setProjection(Projections.property("stock.itemCode"));
        subCriteria.add(Restrictions.eqProperty("item.itemCode", "stock.itemCode"));
        subCriteria.add(Restrictions.eq("storeCode", storeCode));
        criteria.add(Subqueries.exists(subCriteria));


        return itemDictDomain.queryByCriteria(criteria);
    }

    public List<DefectiveProductMaster> getDefectiveProductMasterList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String deliverDocumentNo = MapUtil.getStringValue("deliverDocumentNo", para);
        String storeCode = MapUtil.getStringValue("storeCode", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveProductMaster.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("registDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("registDateTime", DateUtil.parseDate(stopDate)));
        }
        if (!StringUtil.isNullOrEmpty(deliverDocumentNo)) {
            criteria.add(Restrictions.eq("deliverDocumentNo", deliverDocumentNo));
        }
        if (!StringUtil.isNullOrEmpty(storeCode)) {
            criteria.add(Restrictions.eq("storeCode", storeCode));
        }
        List<DefectiveProductMaster> masterList = defectiveProductMasterDomain.queryByCriteria(criteria);
        return masterList;
    }


    public List<DefectiveProductDetail> getDefectiveProductDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveProductDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return defectiveProductDetailDomain.queryByCriteria(criteria);
    }

    public DefectiveProductMaster saveDefectiveProduct(DefectiveProductMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品登记单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getDefectiveProductDocumentNo();
                master.setDocumentNo(documentNo);
            }
            defectiveProductMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (DefectiveProductDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    defectiveProductDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                defectiveProductDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存不良品登记单失败！");
        }
    }

    public int ackDefectiveProduct(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("不良品登记单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(DefectiveProductMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveProductMaster> masterList = defectiveProductMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的不良品登记单信息");
        }
        DefectiveProductMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品登记单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(DefectiveProductDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveProductDetail> detailList = defectiveProductDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该不良品登记单没有明细记录");
        }
        for (DefectiveProductDetail detail : detailList) {
            itemStockService.updateStock(master.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount());
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getDefectiveStoreCode(),detail.getItemCode(),detail.getItemSpec(),-detail.getAmount());
        }
        master.setAcked(true);
        defectiveProductMasterDomain.merge(master);
        return 1;
    }

    /**********************出库管理***********************/
    public List<ExportMaster> getExportMasterList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String storeCode = MapUtil.getStringValue("storeCode", para);
        String receiver = MapUtil.getStringValue("receiver", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(ExportMaster.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("exportDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("exportDateTime", DateUtil.parseDate(stopDate)));
        }
        if (!StringUtil.isNullOrEmpty(storeCode)) {
            criteria.add(Restrictions.eq("storeCode", storeCode));
        }
        if (!StringUtil.isNullOrEmpty(receiver)) {
            criteria.add(Restrictions.like("receiver", "%"+ receiver+"%"));
        }
        List<ExportMaster> masterList = itemExportMasterDomain.queryByCriteria(criteria);
        return masterList;
    }

    public List<ExportDetail> getExportDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(ExportDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return itemExportDetailDomain.queryByCriteria(criteria);
    }

    public ExportMaster saveExport(ExportMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该出库单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getExportDocumentNo();
                master.setDocumentNo(documentNo);
            }
            itemExportMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (ExportDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    itemExportDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                itemExportDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存出库单失败！");
        }
    }

    public int ackExport(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("出库单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(ExportMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<ExportMaster> masterList = itemExportMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的出库单信息");
        }
        ExportMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该出库单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(ExportDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<ExportDetail> detailList = itemExportDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该出库单没有出库明细");
        }
        for (ExportDetail detail : detailList) {
            itemStockService.updateStock(master.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount() * -1);
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getDefectiveStoreCode(),detail.getItemCode(),detail.getItemSpec(),-detail.getAmount());
        }
        master.setAcked(true);
        itemExportMasterDomain.merge(master);
        return 1;
    }

    /**********************不良品修复管理***********************/
    public List<DefectiveRepairMaster> getDefectiveRepairMasterList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveRepairMaster.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("repairDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("repairDateTime", DateUtil.parseDate(stopDate)));
        }
        List<DefectiveRepairMaster> masterList = defectiveDamageMasterDomain.queryByCriteria(criteria);

        return masterList;
    }

    public List<DefectiveRepairDetail> getDefectiveRepairDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveRepairDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return defectiveRepairDetailDomain.queryByCriteria(criteria);
    }

    public DefectiveRepairMaster saveDefectiveRepair(DefectiveRepairMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品修复单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getDefectiveRepairDocumentNo();
                master.setDocumentNo(documentNo);
            }
            defectiveRepairMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (DefectiveRepairDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    defectiveRepairDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                defectiveRepairDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存不良品修复单失败！");
        }
    }

    public int ackDefectiveRepair(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("不良品修复单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(DefectiveRepairMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveRepairMaster> masterList = defectiveRepairMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的不良品修复单信息");
        }
        DefectiveRepairMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品修复单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(DefectiveRepairDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveRepairDetail> detailList = defectiveRepairDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该不良品修复单没有明细信息");
        }
        for (DefectiveRepairDetail detail : detailList) {
            itemStockService.updateStock(master.getDefectiveStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount() * -1);
            itemStockService.updateStock(master.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount());
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getDefectiveStoreCode(),detail.getItemCode(),detail.getItemSpec(),-detail.getAmount());
        }
        master.setAcked(true);
        defectiveRepairMasterDomain.merge(master);
        return 1;
    }


    /**********************不良品报损管理***********************/
    public List<DefectiveDamageMaster> getDefectiveDamageMasterList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        String storeCode = MapUtil.getStringValue("storeCode", para);
        String startDate = MapUtil.getStringValue("startDate", para);
        String stopDate = MapUtil.getStringValue("stopDate", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveDamageMaster.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        if (!StringUtil.isNullOrEmpty(startDate)) {
            criteria.add(Restrictions.ge("damageDateTime", DateUtil.parseDate(startDate)));
        }
        if (!StringUtil.isNullOrEmpty(stopDate)) {
            criteria.add(Restrictions.lt("damageDateTime", DateUtil.parseDate(stopDate)));
        }
        if (!StringUtil.isNullOrEmpty(storeCode)) {
            criteria.add(Restrictions.eq("storeCode",storeCode));
        }
        List<DefectiveDamageMaster> masterList = defectiveRepairMasterDomain.queryByCriteria(criteria);

        return masterList;
    }


    public List<DefectiveDamageDetail> getDefectiveDamageDetailList(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        DetachedCriteria criteria = DetachedCriteria.forClass(DefectiveDamageDetail.class);
        if (!StringUtil.isNullOrEmpty(documentNo)) {
            criteria.add(Restrictions.eq("documentNo", documentNo));
        }
        return defectiveDamageDetailDomain.queryByCriteria(criteria);
    }

    public DefectiveDamageMaster saveDefectiveDamage(DefectiveDamageMaster master) throws RequestProcessException {
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品报损单已经确认，不能修改！");
        }
        try {
            String documentNo = master.getDocumentNo();
            if (StringUtil.isNullOrEmpty(documentNo)) {
                documentNo = documentNoUtil.getDefectiveDamageDocumentNo();
                master.setDocumentNo(documentNo);
            }
            defectiveDamageMasterDomain.merge(master);
            if (master.getListDetail() != null && master.getListDetail().size() > 0) {
                for (DefectiveDamageDetail detail : master.getListDetail()) {
                    detail.setDocumentNo(documentNo);
                    defectiveDamageDetailDomain.merge(detail);
                }
            }
            if (master.getListDetailDelete() != null && master.getListDetailDelete().size() > 0) {
                defectiveDamageDetailDomain.deletes(master.getListDetailDelete());
            }
            return master;
        } catch (Exception ex) {
            throw new RequestProcessException("保存不良品报损单失败！");
        }
    }

    public int ackDefectiveDamage(Map<String, Object> para) throws RequestProcessException {
        String documentNo = MapUtil.getStringValue("documentNo", para);
        if (StringUtil.isNullOrEmpty(documentNo)) {
            throw new RequestProcessException("不良品报损单号不能为空！");
        }
        DetachedCriteria masterCriteria = DetachedCriteria.forClass(DefectiveDamageMaster.class);
        masterCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveDamageMaster> masterList = defectiveRepairMasterDomain.queryByCriteria(masterCriteria);
        if (masterList == null || masterList.size() <= 0) {
            throw new RequestProcessException("没找到需要确认的不良品报损单信息");
        }
        DefectiveDamageMaster master = masterList.get(0);
        if (master.isAcked()) {
            throw new RequestProcessException("该不良品报损单已经被确认！");
        }
        DetachedCriteria detailCriteria = DetachedCriteria.forClass(DefectiveDamageDetail.class);
        detailCriteria.add(Restrictions.eq("documentNo", documentNo));
        List<DefectiveDamageDetail> detailList = defectiveDamageDetailDomain.queryByCriteria(detailCriteria);
        if (detailList == null || detailList.size() <= 0) {
            throw new RequestProcessException("该不良品报损单没有明细信息");
        }
        for (DefectiveDamageDetail detail : detailList) {
            itemStockService.updateStock(master.getStoreCode(), detail.getItemCode(), detail.getItemSpec(), detail.getAmount() * -1);
//            updateStockService.UpdateStock(detail.getDocumentNo(),detail.getDefectiveStoreCode(),detail.getItemCode(),detail.getItemSpec(),-detail.getAmount());
        }
        master.setAcked(true);
        defectiveDamageMasterDomain.merge(master);
        return 1;
    }


}
