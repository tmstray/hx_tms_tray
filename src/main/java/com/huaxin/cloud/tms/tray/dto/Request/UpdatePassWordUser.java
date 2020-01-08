package com.huaxin.cloud.tms.tray.dto.Request;

/**
 * @Description 修改用户密码请求体
 * @Author lixin
 * @Date 2020/1/4 10:06
 * @Version 1.0
 **/
public class UpdatePassWordUser {

    /** 用户ID */
    private Long userId;

    /** 新密码 */
    private String password;

    /** 旧密码 */
    private String oldPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "UpdatePassWordUser{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
