package pers.zhentao.LambdaBaseDemo.Login.dto;

import java.util.Date;

/**
 * user dto
 *
 * @author zhangzhentao1995@163.com
 * 		   2017-10-30
 */
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private Date createDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
