package com.broadcastreferral.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.broadcastreferral.dto.StatusResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReferralUpdateResponse {
	private List<StatusResponse> statusResponse = new ArrayList<>();
}
