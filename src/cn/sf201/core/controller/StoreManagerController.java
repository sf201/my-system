package cn.sf201.core.controller;

import cn.sf201.core.annotations.Auth;
import cn.sf201.core.entity.store.*;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.service.ItemStockService;
import cn.sf201.core.service.StoreManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller("storeManagerController")
@RequestMapping("/storeManager.do")
@Auth(name="store")
public class StoreManagerController extends BaseAjaxController {
    @Resource(name = "storeManagerService")
    private StoreManagerService storeManagerService;

    @Resource(name="itemStockService")
    private ItemStockService itemStockService;

    public void getItemImportMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getItemImportList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getItemImportDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getItemImportDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    /**
     * 保存入库单据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void saveItemImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ItemImportMaster master = this.getPostEntity(request, ItemImportMaster.class);
            this.outJsonObject(response, storeManagerService.saveItemImport(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    /**
     * 入库申请单确认
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void ackImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackImport(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getItemDictBySoreCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getItemDictBySoreCode(para));
        } catch (RequestProcessException ex) {
            this.outJsonObject(response,ex.getMessage());
        }
    }

    public void saveDeliver(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            DeliverBillMaster master = this.getPostEntity(request, DeliverBillMaster.class);
            this.outJsonObject(response, storeManagerService.saveDeliver(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void ackDeliver(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackDeliver(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getDeliverMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDeliverMasterList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getDeliverDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDeliverDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getItemStockList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, itemStockService.getItemStockList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void saveDefectiveProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            DefectiveProductMaster master = this.getPostEntity(request, DefectiveProductMaster.class);
            this.outJsonObject(response, storeManagerService.saveDefectiveProduct(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void ackDefectiveProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackDefectiveProduct(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getDefectiveProductMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveProductMasterList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getDefectiveProductDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveProductDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    /**************************出库单***************************/
    public void saveExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ExportMaster master = this.getPostEntity(request, ExportMaster.class);
            this.outJsonObject(response, storeManagerService.saveExport(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void ackExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackExport(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getExportMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getExportMasterList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getExportDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getExportDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    /**************************不良品修复单***************************/
    public void saveDefectiveRepair(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            DefectiveRepairMaster master = this.getPostEntity(request, DefectiveRepairMaster.class);
            this.outJsonObject(response, storeManagerService.saveDefectiveRepair(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void ackDefectiveRepair(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackDefectiveRepair(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getDefectiveRepairMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveRepairMasterList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getDefectiveRepairDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveRepairDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    /**************************不良品报损单***************************/
    public void saveDefectiveDamage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            DefectiveDamageMaster master = this.getPostEntity(request, DefectiveDamageMaster.class);
            this.outJsonObject(response, storeManagerService.saveDefectiveDamage(master));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void ackDefectiveDamage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.ackDefectiveDamage(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void getDefectiveDamageMasterList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveDamageMasterList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
    public void getDefectiveDamageDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, storeManagerService.getDefectiveDamageDetailList(para));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }


}
