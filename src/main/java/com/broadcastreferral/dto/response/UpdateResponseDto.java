package com.broadcastreferral.dto.response;

import com.broadcastreferral.entity.BroadcastReferral.ProviderAction;
import com.broadcastreferral.entity.BroadcastReferral.ProviderInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateResponseDto {
	private Long id;
	private ProviderInfo provider;
	private ProviderAction action;
}
