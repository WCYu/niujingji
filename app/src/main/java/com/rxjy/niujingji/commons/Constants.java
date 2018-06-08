package com.rxjy.niujingji.commons;

/**
 * Created by AAA on 2017/7/25.
 */

public class Constants {

    public static final int BASE_CODE = 1000;
    public static String REGISTERPHONE="registerphone";
    public static String CODELOGINPHONE="codeloginphone";
    public static String DAOJISHI="daojishi";
    public static String CODE_LOGIN_DAOJISHI="code_login_daojishi";
    public static String CARD_NO = "card_no"; // 卡号
    public static String TOKEN = "token";
    public static String IS_LOGIN = "is_login";

    public static final String IS_FIRST_LOGIN = "is_first_login";

    //向新闻详情界面传递id
    public static final String ACTION_TO_NEWS_DETAIL_NEWS_ID = "action_to_news_detail_news_id";
    //向修改信息界面传递KeyValue
    public static final String ACTION_TO_UPD_USER_INFO_KEY_VALUE = "action_to_upd_user_info_key_value";
    //向修改信息界面传递Key
    public static final String ACTION_TO_UPD_USER_INFO_KEY = "action_to_upd_user_info_key";
    //向修改信息界面传递Value
    public static final String ACTION_TO_UPD_USER_INFO_VALUE = "action_to_upd_user_info_value";
    //向余额界面传递现金信息
    public static final String ACTION_TO_BALANCE_BALANCE_INFO = "action_to_balance_balance_info";
    //添加客户回传到列表标记
    public static final String BACK_TO_CLIENT_LIST = "back_to_client_list";
    //向支款界面传递余额信息
    public static final String ACTION_TO_WITHDRAW_DEPOSIT_INFO = "action_to_withdraw_deposit_info";
    //向修改客户信息界面传递KeHuBaseID
    public static final String ACTION_TO_UPD_CLIENT_CLIENT_ID = "action_to_upd_client_client_id";
    //向修改客户信息界面传递页面是否可修改
    public static final String ACTION_TO_UPD_CLIENT_IS_CAN_CHANGED = "action_to_upd_client_is_can_changed";
    //向Web界面传递TITLE
    public static final String ACTION_TO_WEB_TITLE = "action_to_web_title";
    //向Web界面传递URL
    public static final String ACTION_TO_WEB_URL = "action_to_web_url";
    //向楼盘搜索界面传递cityID
    public static final String ACTION_TO_SEARCH_HOUSE_CITY_ID = "action_to_search_house_city_id";
    //向楼盘详情界面传递buildingID
    public static final String ACTION_TO_BUILDING_DETAIL_BUILDING_ID = "action_to_building_detail_building_id";
    //向房屋详情界面传递roomID
    public static final String ACTION_TO_ROOM_DETAIL_ROOM_ID = "action_to_room_detail_room_id";
    //向地图界面传递TITLE
    public static final String ACTION_TO_MAP_TITLE = "action_to_map_title";
    //向地图界面传递latitude
    public static final String ACTION_TO_MAP_LATITUDE = "action_to_map_latitude";
    //向地图界面传递longitude
    public static final String ACTION_TO_MAP_LONGITUDE = "action_to_map_longitude";

    //回传客户信息
    public static final int REQUEST_CODE_CLIENT_INFO = BASE_CODE + 1;
    //修改客户信息回传
    public static final int REQUEST_CODE_UPD_CLIENT_INFO = BASE_CODE + 2;

    //发送广播的标识
    public static final String ACTION_INFORMATION_MINE = "com.rxjy.niujingji.information.mine";

    //接收广播的Key
    public static final String KEY_STATE = "key_state";

    //---------------------------------网络状态标识-------------------------------------------------
    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;

    public static final String PHONE="phone";
    public static final String PASSWORD="PASSWORD";

}
