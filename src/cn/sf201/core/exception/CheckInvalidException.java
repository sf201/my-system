package cn.sf201.core.exception;

/**
 * Created by sf201 on 2017/8/16.
 */
//约束检测异常
public class CheckInvalidException extends RequestProcessException {
    public CheckInvalidException() {
    }

    public CheckInvalidException(String message) {
        super(message);
    }

    public static final String NOT_NULL_PATIENT_ID = "患者Id不能为空！";
    public static final String NOT_NULL_VISIT_ID = "住院次数不能为空！";
    public static final String NOT_NULL_TEST_NO = "检验单号不能为空！";
    public static final String NOT_NULL_START_DATE = "开始日期不能为空！";
    public static final String NOT_NULL_STOP_DATE = "结束日期不能为空！";
    public static final String NOT_NULL_DEPT_CODE = "科室代码不能为空！";
    public static final String NOT_NULL_ORDER_NO = "医嘱序号不能为空！";;
    public static final String NOT_NULL_ORDER_SUB_NO = "医嘱子序号不能为空！";
    public static final String NOT_NULL_REPORT_ITEM_CODE = "检验结果代码不能为空！";
    public static final String NOT_NULL_REQ_DEPT = "申请科室不能为空！";
    public static final String NOT_NULL_ID = "ID字段不能为空！";
    public static final String NOT_NULL_REQ_DATE_TIME = "申请日期不能为空！";
    public static final String NOT_NULL_ADVICE_MASTER_NO = "药学建议主记录Id不能为空！";
}
