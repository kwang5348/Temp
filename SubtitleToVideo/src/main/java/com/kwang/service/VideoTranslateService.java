package com.kwang.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoTranslateService {
	public String uploadFile(MultipartFile uploadFile) throws Exception;
	public String convertToAudio(String filepath) throws Exception;
}
