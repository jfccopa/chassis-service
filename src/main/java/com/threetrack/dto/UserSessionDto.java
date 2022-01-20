package com.threetrack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserSessionDto {
	private Integer userId;
	private String userName;
	private Integer sessionId;
	private Date sessionStart;
}
