package com.broadcastreferral.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.broadcastreferral.dao.BroadcastReferralDao;
import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;
import com.broadcastreferral.dto.response.UpdateResponseDto;

public class BroadcastReferralServiceImpl implements BroadcastReferralService{
	
	@Autowired
	private BroadcastReferralDao broadcastReferralDao;

	@Override
	public Map<Long, UpdateResponseDto> updateReferral(UpdateRequestDto updateRequestDto) throws Exception {
		
		return broadcastReferralDao.updateReferral(updateRequestDto);
	}

	@Override
	public ReferralUpdateResponse createUpdateReferralResponse(UpdateRequestDto updateRequestDto,
			Map<Long, UpdateResponseDto> updateReferralsMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
