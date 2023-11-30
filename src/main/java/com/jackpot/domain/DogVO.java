package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogVO {

	private long dogId;
	@NotBlank(message = "반려견 이름은 필수 항목입니다.")
	private String dogName;
	private String dogKind;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dogBirth;
	private String dogPhotoPath;
	private String dogFavorite;
	private String dogDescription;
	private long memberId;	//FK

	public void updatePhotoPath(String dogPhotoPath){
		this.dogPhotoPath = dogPhotoPath;
	}

}
