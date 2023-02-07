package com.broadcastreferral.dao;

import java.util.Map;

import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.UpdateResponseDto;

public interface BroadcastReferralDao {
	Map<Long, UpdateResponseDto> updateReferral(UpdateRequestDto updateRequestDto);
}
