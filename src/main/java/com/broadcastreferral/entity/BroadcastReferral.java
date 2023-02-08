package com.broadcastreferral.entity;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document("broadcastReferral")
@EqualsAndHashCode(callSuper = false)
@Schema(name = "BroadcastReferral", title="BroadcastReferral Model", description = "Core model for BroadcastReferral Entities")

public class BroadcastReferral {
	
	@Id
	private String id;
	
	@Field("refId")
	private Long refId;
	
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
