package com.broadcastreferral.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.broadcastreferral.entity.BroadcastReferral;
import com.broadcastreferral.entity.BroadcastReferral.ProviderAction;
import com.broadcastreferral.entity.BroadcastReferral.ProviderInfo;
import com.broadcastreferral.entity.BroadcastReferral.ProviderResponse;
import com.broadcastreferral.repository.BroadcastReferralRepository;

@Component
public class BroadcastReferralRunner implements CommandLineRunner {
	
	@Autowired
	private BroadcastReferralRepository broadcastReferralRepository;

	@Override
	public void run(String... args) throws Exception {
		BroadcastReferral broadcastReferral = new BroadcastReferral();
		List<ProviderResponse> providerResponses = new ArrayList<>();
		ProviderResponse providerResponse = new ProviderResponse();
		ProviderInfo provider = new ProviderInfo();
		provider.setTin("t123");
		provider.setNpi("n123");
		provider.setName("provider1");
		ProviderAction action = new ProviderAction();
		action.setCode("c123");
		action.setDescription("action1 without cantact info");
		providerResponse.setProvider(provider);
		providerResponse.setAction(action);
		providerResponses.add(providerResponse);
		broadcastReferral.setProviderResponses(providerResponses);
		BroadcastReferral savedBroadcastReferral = broadcastReferralRepository.save(broadcastReferral);
		
		System.out.println(savedBroadcastReferral.getId());
		List list = Arrays.asList("1","2");
		List<BroadcastReferral> referrals = (List<BroadcastReferral>) broadcastReferralRepository.findAllById(list);
		for(BroadcastReferral ref : referrals) {
			System.out.println("referrals = "+ref);
		}
		
	}

}
