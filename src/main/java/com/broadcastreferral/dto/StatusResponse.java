package com.broadcastreferral.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusResponse {
	private Long referralId;
	private String status;
	private String description;
}
