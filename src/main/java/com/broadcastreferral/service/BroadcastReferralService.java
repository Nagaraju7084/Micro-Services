package com.broadcastreferral.service;

import java.util.Map;

import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;
import com.broadcastreferral.dto.response.UpdateResponseDto;

public interface BroadcastReferralService {
	//update referral
	Map<Long, UpdateResponseDto> updateReferral(UpdateRequestDto updateRequestDto) throws Exception;
	
	ReferralUpdateResponse createUpdateReferralResponse(UpdateRequestDto updateRequestDto,
			Map<Long, UpdateResponseDto> updateReferralsMap);
}
