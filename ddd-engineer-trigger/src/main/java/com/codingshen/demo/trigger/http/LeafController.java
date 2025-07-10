package com.codingshen.demo.trigger.http;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenchenyu
 * @date 2025-05-14 16:20
 **/
@RestController
@RequestMapping("/leaf")
@Tag(name = "leaf 控制器", description = "leaf 控制器")
@Slf4j
public class LeafController {
	@Resource
	private SegmentService segmentService;

	@Resource
	private SnowflakeService snowflakeService;

	@GetMapping("/get-segment-id")
	public Long getSegmentId() {
		Result id = segmentService.getId("leaf-segment-test");
		return id.getId();
	}

	@GetMapping("/get-snowflake-id")
	public Long getSnowflakeId() {
		Result id = snowflakeService.getId("leaf-segment-test");
		return id.getId();
	}
}
