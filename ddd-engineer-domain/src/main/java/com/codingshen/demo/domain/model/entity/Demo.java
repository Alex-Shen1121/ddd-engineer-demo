package com.codingshen.demo.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {

    /** 用户ID */
    private Long id;
    /** 用户名称 */
    private String userId;
    /** 用户昵称 */
    private String userNickname;
    /** 用户头像 */
    private String userHead;
    /** 账号密码 */
    private String userPassword;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
}
