package com.broadcastreferral.dto.request;

import java.util.List;

import com.broadcastreferral.entity.BroadcastReferral.ProviderAction;
import com.broadcastreferral.entity.BroadcastReferral.ProviderInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRequestDto {
	
	private List<Long> referralIds;
	private ProviderInfo provider;
	private ProviderAction action;
}
