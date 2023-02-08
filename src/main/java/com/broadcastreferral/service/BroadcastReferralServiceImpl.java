package com.broadcastreferral.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.broadcastreferral.dto.StatusResponse;
import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;
import com.broadcastreferral.dto.response.UpdateResponseDto;
import com.broadcastreferral.entity.BroadcastReferral;
import com.broadcastreferral.entity.BroadcastReferral.ProviderResponse;
import com.broadcastreferral.repository.BroadcastReferralRepository;

@Service
public class BroadcastReferralServiceImpl implements BroadcastReferralService{
	
	//@Autowired
	//private BroadcastReferralDao broadcastReferralDao;
	
	@Autowired
	private BroadcastReferralRepository broadcastReferralRepository;

	@Override
	public Map<Long, UpdateResponseDto> updateReferral(UpdateRequestDto updateRequestDto) throws Exception {
		List<BroadcastReferral> referrals = (List<BroadcastReferral>) broadcastReferralRepository.findAllById(updateRequestDto.getReferralIds());
		if(CollectionUtils.isEmpty(referrals) && CollectionUtils.isEmpty(updateRequestDto.getReferralIds())) {
			return Collections.emptyMap();
		}
		List<BroadcastReferral> listOfreferrals = new ArrayList<>();
		if(!CollectionUtils.isEmpty(referrals)) {
			ProviderResponse providerResponse = new ProviderResponse();
			for(BroadcastReferral referral : referrals) {
				List<ProviderResponse> existingListOfProviderResponses = 
						!CollectionUtils.isEmpty(referral.getProviderResponses())?referral.getProviderResponses():new ArrayList<>();
				ProviderResponse providerResponseCheck = existingListOfProviderResponses.stream()
						.filter(provRespo -> provRespo.getProvider().getTin().equalsIgnoreCase(updateRequestDto.getProvider().getTin())
								&& provRespo.getProvider().getNpi().equalsIgnoreCase(updateRequestDto.getProvider().getNpi()))
						.findAny().orElse(null);
				
				if(null != providerResponseCheck) {
					providerResponseCheck.setProvider(updateRequestDto.getProvider());
					providerResponseCheck.setAction(updateRequestDto.getAction());
					referral.setProviderResponses(existingListOfProviderResponses);
					listOfreferrals.add(referral);
				}else {
					providerResponse.setProvider(updateRequestDto.getProvider());
					providerResponse.setAction(updateRequestDto.getAction());
					existingListOfProviderResponses.add(providerResponse);
					referral.setProviderResponses(existingListOfProviderResponses);
					listOfreferrals.add(referral);
				}
			}
		}
		List<BroadcastReferral> updatedReferrals = null;
		try {
			updatedReferrals = broadcastReferralRepository.saveAll(listOfreferrals);
		}catch(Exception e) {
			
		}
		return updatedReferrals.stream()
				.collect(Collectors.toMap(BroadcastReferral::getRefId,
						referral -> referralToResponseDto(referral),
						(first, second) -> first));
	}
	
	public BroadcastReferral dtoToReferral(UpdateResponseDto updateResponseDto) {
		BroadcastReferral referral = new BroadcastReferral();
		BeanUtils.copyProperties(updateResponseDto, referral);
		return referral;
	}
	
	public UpdateResponseDto referralToResponseDto(BroadcastReferral broadcastReferral) {
		UpdateResponseDto updateResponseDto = new UpdateResponseDto();
		BeanUtils.copyProperties(broadcastReferral, updateResponseDto);
		return updateResponseDto;
	}
	
	@Override
	public ReferralUpdateResponse createUpdateReferralResponse(UpdateRequestDto updateRequestDto,
			Map<Long, UpdateResponseDto> updateReferralsMap) {
		ReferralUpdateResponse referralUpdateResponse = new ReferralUpdateResponse();
		List<StatusResponse> listOfStatusResponses;
		listOfStatusResponses = updateRequestDto.getReferralIds().stream()
				.map(refId -> setStaus(updateReferralsMap, refId)).collect(Collectors.toList());
		referralUpdateResponse.setStatusResponse(listOfStatusResponses);
		return referralUpdateResponse;
	}
	
	public StatusResponse setStaus(Map<Long, UpdateResponseDto> updateReferralsMap, Long refId) {
		StatusResponse statusResponse = new StatusResponse();
		if(updateReferralsMap.containsKey(refId)) {
			statusResponse.setReferralId(refId);
			statusResponse.setStatus("200");
			statusResponse.setDescription("Success");
		}else {
			statusResponse.setReferralId(refId);
			statusResponse.setStatus("404");
			statusResponse.setDescription("Record not found with the given referral Id:\t"+refId);
		}
		return statusResponse;
	}
}
