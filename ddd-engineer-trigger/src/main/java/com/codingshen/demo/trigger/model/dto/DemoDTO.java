package com.codingshen.demo.trigger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoDTO {

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

}
