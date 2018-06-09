package cn.sf201.core.controller;

import cn.sf201.core.common.Common;
import cn.sf201.core.entity.*;
import cn.sf201.core.entity.system.*;
import cn.sf201.core.exception.RequestProcessException;
import cn.sf201.core.service.DictService;
import cn.sf201.core.vo.DictSingleVo;
import cn.sf201.core.vo.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sf201 on 9/5/2017.
 */
@Controller("systemController")
@RequestMapping("/system.do")
public class SystemController extends BaseAjaxController {

    @Resource(name = "dictService")
    private DictService dictService;

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("area-config", null);
    }

    public ModelAndView listArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("area-config", null);
    }

    public ModelAndView listErector(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("erector-config", null);
    }

    public ModelAndView listPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("price-config", null);
    }

    public ModelAndView listStore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("store-config", null);
    }

    public ModelAndView listFixReporter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("fix-reporter-config", null);
    }

    public ModelAndView listFixType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("fix-type-config", null);
    }

    public ModelAndView listFixItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("fix-item-config", null);
    }

    public ModelAndView listConfigType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("config-type-config", null);
    }

    public ModelAndView listExchangeReason(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("exchange-reason-config", null);
    }

    public ModelAndView listExchangeRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("exchange-record-config", null);
    }

    public ModelAndView listFault(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("fault-config", null);
    }

    public ModelAndView listPunishment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("punishment-config", null);
    }

    public void getAreaDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, AreaDict.class);
    }

    public void saveAreaDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, AreaDict.class);
    }

    public void getErectorDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ErectorDict.class);
    }

    public void saveErectorDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ErectorDict.class);
    }

    public void getPriceDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, PriceDict.class);
    }

    public void savePriceDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, PriceDict.class);
    }

    public void getStoreDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> para = this.getPostParameters(request);
        try {
            this.outJsonObject(response, dictService.getStoreDictList(para));
        } catch (Exception ex) {
            this.outJsonObject(response, ex.getMessage());
        }
    }

    public void saveStoreDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, StoreDict.class);
    }

    public void getFixReporterDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, FixReporterDict.class);
    }

    public void saveFixReporterDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, FixReporterDict.class);
    }

    public void getFixTypeDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, FixTypeDict.class);
    }

    public void saveFixTypeDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, FixTypeDict.class);
    }

    public void getFixItemDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, FixItemDict.class);
    }

    public void saveFixItemDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, FixItemDict.class);
    }

    public void getConfigTypeDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ConfigTypeDict.class);
    }

    public void saveConfigTypeDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ConfigTypeDict.class);
    }

    public void getExchangeReasonDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ExchangeReasonDict.class);
    }

    public void saveExchangeReasonDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ExchangeReasonDict.class);
    }

    public void getExchangeRecordDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ExchangeRecordDict.class);
    }

    public void saveExchangeRecordDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ExchangeRecordDict.class);
    }

    public void getFaultDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, FaultDict.class);
    }

    public void saveFaultDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, FaultDict.class);
    }

    public void getPunishmentDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, PunishmentDict.class);
    }

    public void savePunishmentDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, PunishmentDict.class);
    }
    public void getHouseResource(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Map<String, Object> para = this.getPostParameters(request);
            this.outJsonObject(response, dictService.getHouseResourceList(para));
        } catch (Exception ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    public void saveHouseResource(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, HouseResource.class);
    }
    public void getItemDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ItemDict.class);
    }

    public void saveItemDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ItemDict.class);
    }
     public void getExportTypeDict(HttpServletRequest request, HttpServletResponse response) throws Exception {
        doGet(request, response, ExportTypeDict.class);
    }

    public void saveExportTypeDict(HttpServletRequest request, HttpServletResponse response) {
        doSave(request, response, ExportTypeDict.class);
    }




    private <T extends DictObject> void doSave(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        try {
//            DictVo<T> dictVo = this.getPostDictVo(request, clazz);
            DictSingleVo<T> dictVo = this.getPostDictSingleVo(request, clazz);
            T dict = dictService.saveDict(dictVo, clazz);
            ResponseVo responseVo = new ResponseVo();
            responseVo.setStatus(Common.SUCCESS_CODE);
            responseVo.setMsg("保存字典信息成功！");
            responseVo.setData(dict);
            this.outJsonObject(response, responseVo);
        } catch (Exception ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }

    private <T extends BaseObject> void doGet(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        try {
            this.outJsonObject(response, dictService.getDict(clazz));
        } catch (RequestProcessException ex) {
            this.outJsonFail(response, ex.getMessage());
        }
    }
}
