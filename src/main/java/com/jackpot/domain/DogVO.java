package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DogVO {

	private long dogId;

	@NotBlank(message = "반려견 이름은 필수 항목입니다.")
	private String dogName;

	private String dogKind;
	private Date dogBirth;
	private String dogPhotoPath;
	private String dogFavorite;
	private String dogDescription;

}
