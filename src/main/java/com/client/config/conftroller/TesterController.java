package com.client.config.conftroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api")
public class TesterController {
	
	@Value("${com.hip.product}")
	private String displayName;
	
	@GetMapping("/ping")
	public String ping() {
		return "pong:\t"+displayName;
	}
	
	@GetMapping("/getData")
	public String getData() {
		return "getData";
	}
	
	@PostMapping("/postData")
	public String insertData() {
		return "insertData";
	}
	
	@DeleteMapping("/deleteData")
	public String deleteData() {
		return "deleteData";
	}
}
