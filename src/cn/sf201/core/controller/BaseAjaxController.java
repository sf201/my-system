package cn.sf201.core.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.sf201.core.domain.BaseDomain;
import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.HttpResponseResult;
import cn.sf201.core.utils.HttpUtil;
import cn.sf201.core.utils.Page;
import cn.sf201.core.utils.WEBCONSTANTS;
import cn.sf201.core.vo.DictSingleVo;
import cn.sf201.core.vo.DictVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 基础控制器
 *
 * @author 赵士星
 * QQ:393568883
 */
public abstract class BaseAjaxController extends MultiActionController {
    public BaseDomain baseDomain;
    public String actionKey;
    public String initkey;

    @Resource(name = "methodResolver")
    public void setMethodResolver(MethodNameResolver resolver) {
        this.setMethodNameResolver(resolver);
    }

    /***
     * 返回关闭公共页
     */
    public static final String CloseMessagePage = "common/closeMessage";
    public static final String SubCloseMessagePage = "common/subCloseMessage";
    public static final String SuccessPageForShowModalDialog = "common/showModalDialogPage";
    public static final String ImportDataPage = "common/importPage";
    public static final String ShowMessagePage = "common/showMessagePage";
    public static final String CommonSelectPage = "common/commonSelectPage";
    /***
     * 所有需要根元素的根值
     */
    public static final String rootValue = "11111111111111111111111111111111";
    /**
     * 是否显示搜索影藏页面
     */
    public final Logger logger = LogManager.getLogger(getClass());

    /***
     * 查询规则抽取
     * @param request
     * @return
     */
    protected DetachedCriteria convertCriteria(HttpServletRequest request) {
        DetachedCriteria criteria = null;
        String valid = HttpUtil.getString(request, "valid", "null");
        if ("true".equals(valid)) {
            criteria = baseDomain.getCriteria(true);
        } else if ("false".equals(valid)) {
            criteria = baseDomain.getCriteria(false);
        } else {
            criteria = baseDomain.getCriteria(null);
        }
        String tname = HttpUtil.getString(request, "tname", "");
        if (!tname.equals("")) {
            criteria.add(Restrictions.ilike("tname", "%" + tname + "%"));
        }
        String sortfield = HttpUtil.getString(request, "sortfield", "id");
        boolean sortValue = HttpUtil.getBoolean(request, "sortvalue", true);
        if (sortValue) {
            criteria.addOrder(Order.desc(sortfield));
        } else {
            criteria.addOrder(Order.asc(sortfield));
        }
        return criteria;
    }

    /**
     * 标签删除对象功能
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void jsdelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> ids = HttpUtil.getList(request, "ids", ";");
        String method = request.getMethod().toLowerCase();
        if ("post".equals(method)) {
            baseDomain.markdeletes(ids);
            this.outSuccessString(request, response, "");
        } else {
            this.outFailString(request, response, "对不起您没有操作权限 !", "");
        }
    }

    /**
     * 彻底删除对象功能
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> ids = HttpUtil.getList(request, "ids", ";");
        String method = request.getMethod().toLowerCase();
        if ("post".equals(method)) {
            try {
                baseDomain.deletes(ids);
                this.outSuccessString(request, response, "");
            } catch (Exception e) {
                String msg = "数据已经被使用，不能删除!";
                this.outFailString(request, response, msg, "");
            }
        } else {
            this.outFailString(request, response, "对不起您没有删除权限 !", "");
        }
    }

    /**
     * 新建页面
     *
     * @return
     */
    protected String getAddPage() {
        return this.initkey + "/" + this.actionKey + "/" + this.actionKey + "Add";
    }

    /**
     * 编辑页面
     *
     * @return
     */
    protected String getEditPage() {
        return this.initkey + "/" + this.actionKey + "/" + this.actionKey + "Edit";
    }

    /**
     * 只读页面
     *
     * @return
     */
    protected String getReadPage() {
        return this.initkey + "/" + this.actionKey + "/" + this.actionKey + "Read";
    }

    /**
     * 列表页面
     *
     * @return
     */
    protected String getListPage() {
        return this.initkey + "/" + this.actionKey + "/" + this.actionKey + "List";
    }

    /**
     * 列表页面
     *
     * @return
     */
    protected String getCustomPage(String str) {
        return this.initkey + "/" + this.actionKey + "/" + this.actionKey + str;
    }

    /***
     * ajax
     * 返回成功的消息
     * @param response
     */
    protected void outSuccessString(HttpServletRequest request, HttpServletResponse response, String gotourl) {
        gotourl = gotourl.replaceAll("@@", "&");
        HttpResponseResult resultBean = new HttpResponseResult(HttpResponseResult.SUCCESS, WEBCONSTANTS.SUCCESSINFOR, gotourl);
        request.setAttribute("lastAjaxActionResult", HttpResponseResult.SUCCESS);
        this.outJsonObject(response, resultBean);
    }

    /***
     * ajax
     * 返回失败的消息
     * @param response
     */
    protected void outFailString(HttpServletRequest request, HttpServletResponse response, String msg, String gotourl) {
        gotourl = gotourl.replaceAll("@@", "&");
        HttpResponseResult resultBean = new HttpResponseResult(HttpResponseResult.FAIL, msg, gotourl);
        request.setAttribute("lastAjaxActionResult", HttpResponseResult.FAIL);
        this.outJsonObject(response, resultBean);
    }

    /***
     * ajax 返回单个对象信息 json格式
     * @param response
     * @param obj
     */
    protected void outJsonObject(HttpServletResponse response, Object obj) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(obj));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void outJsonFail(HttpServletResponse response, String msg) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write("{\"err\":\"" + msg + "\"}");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> T getPostEntity(HttpServletRequest request, Class<T> clazz) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String parameters = sb.toString();
        T object = JSON.parseObject(parameters, clazz);
        return object;
    }

    protected <T> DictVo<T> getPostDictVo(HttpServletRequest request, Class<T> clazz) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String parameters = sb.toString();
        DictVo<T> object = JSON.parseObject(parameters, new TypeReference<DictVo<T>>() {
        });
        List<T> dictList = new ArrayList<T>();
        if (object.getAddList() != null) {
            for (Object item : object.getAddList()) {
                JSONObject jsonObject = (JSONObject) item;
                T dict = clazz.newInstance();
                BeanUtils.populate(dict, jsonObject);
                dictList.add(dict);
            }
        }
        DictVo<T> vo = new DictVo<T>();
        vo.setAddList(dictList);
        vo.setDelList(object.getDelList());
        return vo;
    }

    protected <T> DictSingleVo<T> getPostDictSingleVo(HttpServletRequest request, Class<T> clazz) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String parameters = sb.toString();
        DictSingleVo<T> object = JSON.parseObject(parameters, new TypeReference<DictSingleVo<T>>() {
        });

        if (object.getDict() != null) {
            JSONObject jsonObject = (JSONObject) object.getDict();
            T dict = clazz.newInstance();
            BeanUtils.populate(dict, jsonObject);
            object.setDict(dict);
        }
        return object;
    }

    protected Map<String, Object> getPostParameters(HttpServletRequest request) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String parameters = sb.toString();
        Map<String, Object> returnParam = new HashMap<String, Object>();
        returnParam = JSON.parseObject(parameters);
        return returnParam;
    }

    /***
     * 公共的导入页面导向功能
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView importdatas(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String parameters = HttpUtil.getString(request, "parameters", "");
        model.put("parameters", parameters);
        model.put("masterplateurl", "masterplate\\" + this.actionKey + ".xls");
        return new ModelAndView(ImportDataPage, model);
    }

    /***
     * 公共的选择列表方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView commonselectlist(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String objectId = HttpUtil.getString(request, "objectId", "");
        String objectName = HttpUtil.getString(request, "objectName", "");
        String name = HttpUtil.getString(request, "tname", "");
        String selectType = HttpUtil.getString(request, "selectType", "0");
        String sortfield = HttpUtil.getString(request, "sortfield", "");
        String sortvalue = HttpUtil.getString(request, "sortvalue", "");
        Page page = HttpUtil.convertPage(request);
        DetachedCriteria criteria = this.baseDomain.getCriteria(true);
        if (!name.equals("")) {
            criteria.add(Restrictions.ilike("tname", "%" + name + "%"));
        }
        if (!sortfield.equals("") && !sortvalue.equals("")) {
            boolean sortValue = HttpUtil.getBoolean(request, "sortvalue", true);
            if (sortValue) {
                criteria.addOrder(Order.desc(sortfield));
            } else {
                criteria.addOrder(Order.asc(sortfield));
            }
        } else {
            criteria.addOrder(Order.desc("id"));
        }
        List list = baseDomain.queryByCriteria(criteria, page);
        model.put("list", list);
        model.put("page", page);
        model.put("sortfield", sortfield);
        model.put("sortvalue", sortvalue);
        model.put("objectId", objectId);
        model.put("objectName", objectName);
        model.put("tname", name);
        model.put("selectType", selectType);
        String mpath = this.getCustomPageRealPath("commonSelectPage");
        File file = new File(mpath);
        if (file.exists() && file.isFile()) {
            return new ModelAndView(this.getCustomPage("commonSelectPage"), model);
        } else {
            return new ModelAndView(CommonSelectPage, model);
        }
    }

    /***
     * 获取返回视图的物理路径
     * @param str
     * @return
     */
    protected String getCustomPageRealPath(String str) {
        return getServletContext().getRealPath("/") + "page\\" + this.initkey + "\\" + this.actionKey + "\\" + this.actionKey + str + ".htm";
    }

    /***
     * 增加保存时间、保存人相关信息
     * @param request
     * @param obj
     */
    protected void saveBaseInfoToObject(HttpServletRequest request, BaseObject obj) {
//		boolean valid=HttpUtil.getBoolean(request, "valid", true);
//		obj.setValid(valid);
//		Users loginUser=HttpUtil.getLoginUser(request);
//		if(loginUser!=null){
//			obj.setCreateId(loginUser.getId());
//		}
    }

    /***
     * 绑定常用类型
     */
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        if (binder != null) {
//			binder.registerCustomEditor(java.util.Date.class,
//					new DatePropertyEditor());
//			binder.registerCustomEditor(Dictionary.class,
//					new DictionaryPropertyEditor());
//			binder.registerCustomEditor(Organ.class,
//					new OrganPropertyEditor());
//			binder.registerCustomEditor(Role.class,
//					new RolePropertyEditor());
//			binder.registerCustomEditor(Menu.class,
//					new MenuPropertyEditor());
//			binder.registerCustomEditor(Users.class,
//					new UsersPropertyEditor());
//			binder.registerCustomEditor(Appes.class,
//					new AppesPropertyEditor());
        }
        super.initBinder(request, binder);
    }

    public void setBaseDomain(BaseDomain baseDomain) {
        this.baseDomain = baseDomain;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public void setInitkey(String initkey) {
        this.initkey = initkey;
    }
}
