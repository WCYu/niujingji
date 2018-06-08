package com.rxjy.niujingji.api;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */
public interface ApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("actionapi/apphome/Login")
    Observable<String> getToken(
            @Field("cardNo") String cardNo,
            @Field("password") String password,
            @Field("postId") int postID
    );

    /**
     * 验证码登录
     */
    @FormUrlEncoded
    @POST("actionapi/apphome/Login")
    Observable<String> getTokenByCode(
            @Field("cardNo") String cardNo,
            @Field("vCode") String vCode,
            @Field("postId") int postID
    );

    /**
     * 获取注册验证码（商务）
     */
    @GET("AppAgent/SendVerifyCode")
    Observable<String> getVerificationCode(
            @Query("phone") String phoneNum
    );

    /**
     * 获取登录验证码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/GetVcodeLanding")
    Observable<String> getVCodeLogin(
            @Field("phone") String phoneNum,
            @Field("postId") int postID
    );

    /**
     * 获取忘记密码验证码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/GetVcodeUpdatePwd")
    Observable<String> getVCodeForgetPassword(
            @Field("phone") String phoneNum
    );

    /**
     * 忘记密码修改密码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/UpdatePassword_Vcode")
    Observable<String> updatePasswordByForget(
            @Field("phone") String phoneNum,
            @Field("newPassword") String newPassword,
            @Field("vCode") String vCode
    );

    /**
     * 修改密码
     */
    @GET("actionapi/apphome/UpdatePassword")
    Observable<String> updatePassword(
            @Query("cardNo") String cardNo,
            @Query("password") String password,
            @Query("newPassword") String newPassword,
            @Query("token") String token
    );


    /**
     * 获取下线
     */
    @GET("AppAgent/GetSubordinateList")
    Observable<String> GetSubordinateList(
            @Query("card") String card
    );

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/AddUser")
    Observable<String> getRegister(
            @Field("v_code") String code,
            @Field("a_account") String phoneNum,
            @Field("a_password") String password,
            @Field("depart_id") int areaID,
            @Field("post_name") String postName,
            @Field("post_id") int postID,
            @Field("sw_invitation_code") String invitationCode
    );

    /**
     * 获取用户信息
     */
    @GET("actionapi/apphome/GetUserMessage")
    Observable<String> getLoginUserInfo(
            @Query("cardNo") String cardNo,
            @Query("token") String token
    );

    /**
     * 上传头像
     */
    @Multipart
    @POST("actionapi/AppHome/UpdateImages")
    Observable<String> upHeaderPicture(
            @Part("token") String token,
            @Part("cardNo") String cardNo,
            @Part("type") String type,
            @Part MultipartBody.Part file
    );

    /**
     * 新闻列表
     */
    @Multipart
    @POST("a/sap/sapArticle/getAppArticleList")
    Observable<String> getNewsList(
            @Part("cardNo") String cardNo,
            @Part("page") int pageIndex,
            @Part("rows") int pageSize
    );

    /**
     * 修改用户信息
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/UpdateMyInfo")
    Observable<String> updateUserInfo(
            @Field("token") String token,
            @Field("cardNo") String cardNo,
            @Field("key") String key,
            @Field("value") String value
    );

    /**
     * 获取钱包信息
     */
    @GET("AppAgent/GetMemBerMoney")
    Observable<String> getWalletInfo(
            @Query("MemberCard") String cardNo
    );

    /**
     * 获取收支明细数据接口
     */
    @GET("AppAgent/ShouZhiMingXiList")
    Observable<String> getWalletRecord(
            @Query("KaHao") String cardNo,
            @Query("PageIndex") int pageIndex,
            @Query("PageSize") int pageSize
    );

    /**
     * 获取是否有支付密码
     */
    @GET("AppAgent/IsSetPassword")
    Observable<String> getIsHavePwd(
            @Query("cardNo") String cardNo
    );

    /**
     * 设置支付密码
     */
    @Multipart
    @POST("AppAgent/SetPassword")
    Observable<String> setBankCardPwd(
            @Part("cardNo") String cardNo,
            @Part("payPassword") String pwd
    );

    /**
     * 提现密码
     */
    @Multipart
    @POST("AppAgent/MemberWithdrawals")
    Observable<String> getWithdrawals(
            @Part("MemberCard") String cardNo,
            @Part("WithDrawalsMoney") double price,
            @Part("MemberBankCard") String cardNum,
            @Part("MemberBankName") String bankName,
            @Part("MemberName") String memberCard,
            @Part("PayPassword") String pwd
    );

    /**
     * 新增或修改银行卡信息
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/UpdateMyBankInfo")
    Observable<String> subAddOrUpd(
            @Field("token") String token,
            @Field("cardNo") String cardNo,
            @Field("bankCard") String bankCard,
            @Field("bankName") String bankName,
            @Field("bankUserName") String bankUserName
    );

    /**
     * 获取客户信息列表接口
     */
    @GET("AppAgent/GetOrderList")
    Observable<String> getClientList(
            @Query("card_no") String cardNo,
            @Query("state") String state,
            @Query("serviceType") String serviceType,
            @Query("key") String key
    );

    /**
     * 获取套餐和客户类型相关信息
     */
    @GET("AppAgent/CustomerProperty")
    Observable<String> getTc(
            @Query("type") String type
    );

    /**
     * 提交客户信息
     */
    @Multipart
    @POST("AppAgent/AddCustomer")
    Observable<String> subClientInfo(
            @Part("XingMing") String clientName,
            @Part("ShouJiHaoYi") String phoneNum,
            @Part("WeiXin") String wx,
            @Part("MianJi") String acreage,
            @Part("LeiXingYi") String typeOne,
            @Part("LeiXingEr") String typeTwo,
            @Part("GongSiMingCheng") String companyName,
            @Part("LiangFangDiZhi") String measureLocation,
            @Part("KaHao") String cardNo,
            @Part("ZhuangTai") String type,
            @Part("Package_type") String tcType
    );

    /**
     * 获取顾问信息
     */
    @GET("AppAgent/GetSalesmanInfo")
    Observable<String> getCounselorInfo(
            @Query("card_no") String cardNo
    );

    /**
     * 获取版本信息接口
     */
    @GET("AppAgent/GetAndroidVersion")
    Observable<String> getVersionInfo(

    );

    /**
     * 获取客户信息接口
     */
    @GET("AppAgent/GetCustomerInfo")
    Observable<String> getClientInfo(
            @Query("KeHuBaseID") int clientID
    );

    /**
     * 修改客户信息接口
     */
    @Multipart
    @POST("AppAgent/UpdateCustomer")
    Observable<String> updClientInfo(
            @Part("XingMing") String clientName,
            @Part("WeiXin") String wx,
            @Part("MianJi") String acreage,
            @Part("LeiXingYi") String typeOne,
            @Part("LeiXingEr") String typeTwo,
            @Part("GongSiMingCheng") String companyName,
            @Part("LiangFangDiZhi") String measureLocation,
            @Part("KaHao") String cardNo,
            @Part("ZhuangTai") String type,
            @Part("Package_type") String tcType,
            @Part("KeHuBaseID") int clientID
    );

    /**
     * 获取设备IP
     */
    @GET
    Observable<String> getIP(
            @Url String url
    );

    /**
     * 上传应用信息
     */
    @FormUrlEncoded
    @POST("actionapi/apphome/AddlandingMessage")
    Observable<String> subAppInfo(
            @Field("card_no") String cardNo,
            @Field("locate_province_now") String province,
            @Field("locate_city_now") String city,
            @Field("a_equipment") String equipment,
            @Field("a_ip") String ip,
            @Field("network_status") String netWorkStatus
    );

    /**
     * 获取Tab未读状态
     */
    @GET("AppAgent/GetSubordinateCount")
    Observable<String> getTabState(
            @Query("card") String card
    );

    /**
     * 获取消息列表
     */
    @GET("AppAgent/GetNewsList")
    Observable<String> getMsgList(
            @Query("card") String card
    );

    /**
     * 获取银行卡列表
     */
    @GET("actionapi/AppHome/GetBankList")
    Observable<String> getBankListInfo(

    );

    /**
     * 获取地区列表
     */
    @GET("AppAgent/GetCityList")
    Observable<String> getCityList(

    );

    /**
     * 获取区域列表
     */
    @GET("AppAgent/GetAreaList")
    Observable<String> getAreaList(
            @Query("id") int cityID
    );

    /**
     * 获取商圈列表
     */
    @GET("AppAgent/GetTradingArea")
    Observable<String> getTradingAreaList(
            @Query("areaid") int areaID
    );

    /**
     * 获取楼盘列表
     */
    @GET("AppAgent/GetBuildingList")
    Observable<String> getBuildingList(
            @Query("city") Integer cityID,
            @Query("county") Integer areaID,
            @Query("bussiness") String tradingAreaName,
            @Query("areaStart") Integer areaStart,
            @Query("areaEnd") Integer areaEnd,
            @Query("priceStart") Integer priceStart,
            @Query("priceEnd") Integer priceEnd,
            @Query("key") String keyWord,
            @Query("pageSize") Integer pageSize,
            @Query("pageIndex") Integer pageIndex,
            @Query("sortBy") Integer sortBy
    );

    /**
     * 获取楼盘列表
     */
    @GET("AppAgent/GetBuildingList")
    Observable<String> searchBuildingList(
            @Query("city") Integer cityID,
            @Query("key") String keyWord,
            @Query("pageSize") Integer pageSize,
            @Query("pageIndex") Integer pageIndex,
            @Query("sortBy") Integer sortBy
    );

    /**
     * 获取楼盘信息
     */
    @GET("AppAgent/GetBuildingInfo")
    Observable<String> getBuildingInfo(
            @Query("id") int buildingID
    );

    /**
     * 获取房源列表
     */
    @GET("AppAgent/GetHouseList")
    Observable<String> getHouseList(
            @Query("id") int cityID,
            @Query("pageSize") int pageSize,
            @Query("pageIndex") int pageIndex,
            @Query("type") int type
    );

    /**
     * 获取房源信息
     */
    @GET("AppAgent/GetHouseInfo")
    Observable<String> getHouseInfo(
            @Query("id") int houseID
    );

    /**
     * 获取房源图片
     */
    @GET("AppAgent/GetHouseImages")
    Observable<String> getHouseImages(
            @Query("id") int houseID
    );

    /**
     * 获取热搜关键词
     */
    @GET("AppAgent/GetHotKeyWords")
    Observable<String> getHotKeyWordList(
            @Query("id") int cityID
    );

    /**
     * 预约看房
     */
    @GET("/AppAgent/BookingRoom")
    Observable<String> bookingRoom(
            @Query("id") int id,
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("type") int type
    );

    /**
     * 获取周边接口
     */
    @GET("place/v2/search")
    Observable<String> getPoiInfo(
            @Query("query") String query,
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("output") String output,
            @Query("page_size") int pageSize,
            @Query("ak") String ak
    );


    /**
     * 获取礼物接口
     */
    @GET("AppAgent/GetGiftStatus")
    Observable<String> getGift(
            @Query("card") String card
    );

    /**
     * 礼物详情
     */
    @GET("AppAgent/GetGiftList")
    Observable<String> getGiftInfo(
            @Query("card") String card
    );

    /**
     * 礼物详情
     */
    @GET("AppAgent/ReceiveGift")
    Observable<String> getGiftPickUp(
            @Query("id") String id,
            @Query("giftname") String giftname,
            @Query("beizhu") String beizhu,
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("address") String address
    );


    /**
     * 验证客户手机号
     */
    @Multipart
    @POST("AppEmployee/VerificationPhone")
    Observable<String> postCustomerPhone(
            @Part("phone") String phone
    );

    /**
     * 获取是否登陆过
     */

    @GET("actionapi/AppLogin/GetCheckUserInfo")
    Observable<String> getCheckUserInfo(
            @Query("phone") String phone,
            @Query("AppId") String AppId
    );
    /**
     *
     获取瑞祥APP注册身份验证码（或者权限验证

     */
    @GET("actionapi/AppLogin/GetInsideVcodeLanding")
    Observable<String> getInsideVcodeLanding(
            @Query("phone") String phone,
            @Query("AppId") String AppId
    );

    /**
     * 登录
     * @param cardNo
     * @param password
     * @param
     * @return
     */

    @FormUrlEncoded
    @POST("actionapi/AppLogin/Login")
    Observable<String> getAppLogin(
            @Field("cardNo") String cardNo,
            @Field("password") String password,
            @Field("vCode") String vCode,
            @Field("appId") String appId
    );
}
