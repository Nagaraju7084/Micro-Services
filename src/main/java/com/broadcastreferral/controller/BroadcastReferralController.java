package com.broadcastreferral.controller;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;
import com.broadcastreferral.dto.response.UpdateResponseDto;
import com.broadcastreferral.service.BroadcastReferralService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="BroadcastReferral", description = "Rest Apis for Broadcast Referral Resource")
@RestController
@Slf4j
public class BroadcastReferralController {
	
	@Autowired
	private BroadcastReferralService broadcastReferralService;
	
	@Operation(summary = "Add Category", description = "Adding Category Data")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not Found")
	})
	@PutMapping("/referrals")
	public @ResponseBody ResponseEntity<ReferralUpdateResponse> updateReferral(@RequestBody UpdateRequestDto updateRequestDto) throws Exception{
		ReferralUpdateResponse referralUpdateResponse = null;
		Map<Long, UpdateResponseDto> updatedReferralsMap = null;
		try {
			if(updateRequestDto == null || CollectionUtils.isEmpty(updateRequestDto.getReferralIds())
					|| updateRequestDto.getProvider() == null || updateRequestDto.getProvider().getTin() == null
					|| updateRequestDto.getProvider().getNpi() == null
					|| updateRequestDto.getProvider().getTin().equalsIgnoreCase("null")
					|| updateRequestDto.getProvider().getNpi().equalsIgnoreCase("null")
					) {
				throw new Exception("Invalid Json");
			}
			log.info("referral ids: "+updateRequestDto.getReferralIds());
			updatedReferralsMap = broadcastReferralService.updateReferral(updateRequestDto);
			referralUpdateResponse = broadcastReferralService.createUpdateReferralResponse(updateRequestDto, updatedReferralsMap);
			if(MapUtils.isEmpty(updatedReferralsMap)) {
				return new ResponseEntity<>(referralUpdateResponse, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(referralUpdateResponse, HttpStatus.OK);
		}catch(Exception e) {
			log.error("internal server error ::{}", e.getMessage());
			throw e;
		}
	}
}
