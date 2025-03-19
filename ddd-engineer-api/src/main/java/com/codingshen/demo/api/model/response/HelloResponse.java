package com.codingshen.demo.api.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloResponse {

    @Schema(title = "回复", example = "张三, 你好, 世界!")
    @Length(max = 1, message = "回复最长 1 个字符")
    private String msg;
}
