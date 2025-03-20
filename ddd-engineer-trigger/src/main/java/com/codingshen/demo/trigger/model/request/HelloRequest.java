package com.codingshen.demo.trigger.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(title = "HelloRequest", description = "Hello 请求")
public class HelloRequest {

    @Schema(description = "姓名", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED, type = "string")
    @Length(min = 1, max = 10, message = "姓名长度必须在1到10之间")
    @NotNull(message = "姓名不能为空")
    private String name;

    @Schema(description = "消息", example = "你好，世界！")
    @NotNull(message = "消息不能为空")
    @Length(min = 1, max = 50, message = "消息长度必须在1到50之间")
    private String msg;

}
