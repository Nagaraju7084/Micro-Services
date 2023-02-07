package com.broadcastreferral.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class BroadcastReferral {
	
	private Long id;
	private String memberId;
	
	private List<ProviderResponse> providerResponses;
	
	@Data
	public static class ProviderResponse{
		private ProviderInfo provider;
		private ProviderAction action;
	}

	
	@Data
	public static class ProviderAction{
		private String code;
		private String description;
		private ContactInfo contact;
	}
	
	@Data
	public static class ProviderInfo{
		private String tin;
		private String npi;
		private String name;
	}
	
	@Data
	public static class ContactInfo{
		private String firstName;
		private String lastName;
	}
}
