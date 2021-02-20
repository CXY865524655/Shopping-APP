package com.example.vant_demo_success.err;

/**
 * @Package_Name: com.example.vant_demo_success.err
 * @ClassName: EnBusinessError
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/17 14:42
 */
public enum EnBusinessError implements CommonError{

    // 通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),


    USER_IS_NULL(10003, "用户名为空"),
    PASSWORD_IS_NULL(10004, "密码为空"),
    TELEPHONE_IS_NULL(10005, "手机号为空"),
    VerificationCode_IS_NULL(10005, "验证码为空"),
    TEL_OR_CODE_IS_ERROR(10006, "手机号或验证码错误"),
    USER_OR_PASSWORD_ERROR(10007, "用户名或密码错误"),
    PASSWORD_ERROR(10008, "密码错误"),

    // 20000 开头的为用户信息相关错误定义
    USER_NOT_EXITS(20001, "用户不存在,请重新登录"),
    DEL_USER_ERR(20012, "删除用户失败"),
    USER_EXITS(20001, "用户已经存在"),
    TELEPHONE_NOT_EXITS(20005, "手机号不存在"),
    TELEPHONE_EXITS(20006, "手机号已经存在"),
    USER_LOGIN_FAIL(20002, "请检查用户名或密码是否正确"),
    USER_NOT_LOGIN(20003, "用户未登录"),
    CODE_ERR(20004, "验证码错误"),
    UPDATE_USER_INFO_ERROR(20007, "用户信息更新失败"),
    UPDATE_USER_SEX_ERROR(20008, "用户性别更换失败"),
    UPDATE_USER_BIRTHDAY_ERROR(20009, "用户生日更换失败"),
    UPDATE_USER_PAY_PWD_ERROR(20010, "用户支付密码更换失败"),
    TOKEN_ERR(401, "401"),

    PAY_ERROR(20011, "购买失败"),

    NO_TOKEN(20007, "无token，请重新登录"),


    // 3 开头 good
    DEL_GOOD_ERR(30001, "删除商品失败或删除不完全-- good and assess"),
    SAVE_GOOD_ERR(30002, "商品信息修改失败"),
    SAVE_GOOD_IMAGE_ERR(30003, "商品图片修改失败"),
    SAVE_DETAIL_ERR(30003, "商品详情修改失败"),
    ADD_GOOD_ERR(30004, "商品信息添加失败"),
    ADD_SALES_ERR(30005, "销量+1失败"),

    // 4 开头 user
    DEL_USER_PATH_ERR(40001, "删除用户头像失败"),
    SAVE_USER_ERR(40002, "用户信息保存失败"),
    SAVE_USER_IMAGE_PATH_ERR(40002, "用户头像地址保存失败"),

    // 5 开头 category
    GET_CATEGORY_ERR(50001, "获取category失败"),


    // 30000 开头的为交易信息错误定义
    STOCK_NOT_ENOUGH(30001,"库存不足"),



    ;

    private int errCode;
    private String errMsg;

    private EnBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
