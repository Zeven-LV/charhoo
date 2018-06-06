package com.charhoo.os.consts;

public class ErrorCode {

    //参数错误
    public static final int PARAM_ERROR = 170000;
	//大卡验证TOKEN失败
	public static final int DAKA_TOKEN_VERIFY_FAIL = 170001;
	//大卡用户不存在于智运本地
	public static final int DAKA_USER_INFO_FAIL = 170002;

	//车辆经纬度参数为空
	public static final int VEHICLE_REVERSE_GPS_DESC = 170005;
	//车辆经纬度获取逆地理位置描述时，接口异常。
	public static final int VEHICLE_REVERSE_GPS_DESC_EXCEPTION = 170006;
	//获取车辆最后位置时异常
	public static final int VEHICLE_LASTTRACK_EXCEPTION = 170008;
	//四种验证功能中发生未知错误被捕获
	public static final int VERIFY_ERROR = 170007;
	//etims-api接口未返回数据
	public static final int ETIMS_API_RETURN_NULL = 170009;
	//获得用户下的车辆时异常。
	public static final int VEHICLE_LIST_NULL = 170010;
	//系统错误
	public static final int SYSTEM_ERROR = 170011;
	//用户信息错误
	public static final int USERINFO_INVALID = 170012;
	//获取车辆轨迹信息异常
	public static final int VEHICLE_TRACKINFO_EXCEPTION = 170013;
	//获取车辆里程信息异常
	public static final int VEHICLE_MILEAGEINFO_EXCEPTION = 170013;
	//判断用户查询次数时异常
	public static final int VERIFY_FREQUENCY_ERROR = 170015;
	//调用etims-api接口时异常
	public static final int ETIMS_API_ERROR = 170016;
	//设置用户查询次数加1时异常
	public static final int VERIFY_FREQUENCY_ADD_ERROR = 170017;
	//执行etims-bill时异常
	public static final int ETIMS_BILL_ERROR = 170018;

}
