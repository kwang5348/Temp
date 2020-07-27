package com.kwang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kwang.dto.RecInfo;
import com.kwang.service.VideoTranslateService;
import com.kwang.stt.InfiniteStreamRecognize;
import com.kwang.stt.Recognize;

@Controller
public class MainController {

	@Autowired
	VideoTranslateService service;
	
	InfiniteStreamRecognize infrec = new InfiniteStreamRecognize();

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String moveMain() {
		System.out.println("main 호출 성공");
		return "index";//jsp 호출
	}
	
	@RequestMapping(value = "/print")
	@ResponseBody
	public RecInfo printTran() {
		RecInfo rec = new RecInfo(infrec.beforesubtitle, infrec.aftersubtitle, null, infrec.asyncsubtitle);
		return rec;//jsp 호출
	}
	
	@RequestMapping(value = "/start/{cid}", method = RequestMethod.GET)
	public String startTranslate(@PathVariable int cid, HttpServletRequest request, HttpSession session) {
		System.out.println("test 시작");
		//InfiniteStreamRecognize infrec = new InfiniteStreamRecognize();
		try {
			infrec.setFlag(true);
			String lang = infrec.checkLang(cid);
			infrec.infiniteStreamingRecognize(lang, request, session);
			
		} catch (Exception e){
			System.out.println("파일 번역 실패");
			e.printStackTrace();
		}
		System.out.println("test 완료");
		
		return "redirect:/";//jsp 호출
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public String stopTranslate(HttpServletRequest request) {
		try {
			infrec.setFlag(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";//jsp 호출
	}
}






