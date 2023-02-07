package com.broadcastreferral.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.broadcastreferral.dto.StatusResponse;
import com.broadcastreferral.dto.request.UpdateRequestDto;
import com.broadcastreferral.dto.response.ReferralUpdateResponse;
import com.broadcastreferral.dto.response.UpdateResponseDto;
import com.broadcastreferral.entity.BroadcastReferral;
import com.broadcastreferral.entity.BroadcastReferral.ContactInfo;
import com.broadcastreferral.entity.BroadcastReferral.ProviderAction;
import com.broadcastreferral.entity.BroadcastReferral.ProviderInfo;
import com.broadcastreferral.entity.BroadcastReferral.ProviderResponse;

@Repository
public class BroadcastReferralDaoImpl implements BroadcastReferralDao{
	
	List<BroadcastReferral> listOfReferrals = null;

	@Override
	public Map<Long, UpdateResponseDto> updateReferral(UpdateRequestDto updateRequestDto) {
		Map<Long, UpdateResponseDto> updatedMap = useInMemoryDb(updateRequestDto);
		return updatedMap;
	}

	private Map<Long, UpdateResponseDto> useInMemoryDb(UpdateRequestDto updateRequestDto) {
		listOfReferrals = new ArrayList<BroadcastReferral>();
		List<ProviderResponse> listOfProviderResponses = new ArrayList<>();
		
		ProviderInfo provider = new ProviderInfo();
		provider.setTin("t123");
		provider.setNpi("n123");
		provider.setName("DGN");
		
		ProviderAction action = new ProviderAction();
		action.setCode("c123");
		action.setDescription("hard code value");
		
		ContactInfo contact = new ContactInfo();
		contact.setFirstName("firstName");
		contact.setLastName("lastName");
		
		action.setContact(contact);
		
		ProviderResponse provResp = new ProviderResponse();
		provResp.setProvider(provider);
		provResp.setAction(action);
		
		listOfProviderResponses.add(provResp);
		
		if(!CollectionUtils.isEmpty(listOfReferrals)) {
			ProviderResponse providerResponse = new ProviderResponse();
			for(BroadcastReferral referral : listOfReferrals) {
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
					listOfReferrals.add(referral);
				}else {
					providerResponse.setProvider(updateRequestDto.getProvider());
					providerResponse.setAction(updateRequestDto.getAction());
					existingListOfProviderResponses.add(providerResponse);
					referral.setProviderResponses(existingListOfProviderResponses);
					listOfReferrals.add(referral);
				}
				
			}
			
		}
		
		return listOfReferrals.stream()
				.collect(Collectors.toMap(BroadcastReferral::getId,
						referral -> referralToResponseDto(referral), (first, second) -> first));
		
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
