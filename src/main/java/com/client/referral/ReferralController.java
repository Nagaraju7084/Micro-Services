package com.client.referral;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/referral")
public class ReferralController {
	
	
	@GetMapping("/pingreferral")
	public String ping() {
		return "pongreferral";
	}
	
	@GetMapping("referral/getData")
	public String getData() {
		return "getData";
	}
	
	@PostMapping("referral/postData")
	public String insertData() {
		return "insertData";
	}
	
	@DeleteMapping("referral/deleteData")
	public String deleteData() {
		return "deleteData";
	}
}
