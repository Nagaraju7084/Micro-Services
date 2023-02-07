package com.broadcastreferral.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="BroadcastReferral", description = "Rest Apis for Broadcast Referral Resource")
@RestController
public class BroadcastReferralController {
	
	@Operation(summary = "Add Category", description = "Adding Category Data")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PutMapping("/referrals")
	public @ResponseBody ResponseEntity<ReferralUpdateResponse> updateReferral(@RequestBody UpdateRequestDto updateRequestDto) throws Exception{
		return null;
	}
}
