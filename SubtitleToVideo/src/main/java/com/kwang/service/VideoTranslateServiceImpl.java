package com.kwang.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.google.cloud.speech.v1.WordInfo;
import com.google.protobuf.ByteString;
import com.kwang.bucket.UploadObject;

import io.grpc.internal.ClientStream;


@Service
public class VideoTranslateServiceImpl implements VideoTranslateService {

	@Override
	public String uploadFile(MultipartFile uploadFile) throws Exception {
		try {
			//절대경로 저장
			String path = "C:\\Users\\user\\Desktop\\repo\\SubtitleToVideo\\src\\main\\resources\\static\\download\\" + uploadFile.getOriginalFilename();
			
			//상대경로 저장
			//String path = uploadFile.getOriginalFilename();
			System.out.println("upload 성공!");
			System.out.println("download 경로 : " + path);
			uploadFile.transferTo(new File(path));
			UploadObject uo = new UploadObject();
			uo.uploadObject("ssafyApi", "saveaudio", "temp.wav", path);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String convertToAudio(String filepath) throws Exception {
		Runtime run = Runtime.getRuntime();
		//뒤에서 .mp4 스트링을 제거하는 코드인데 입력되는 file형식이 많아지면 수정해야함
		String resultFile = filepath.substring(0, filepath.length()-4) + ".wav";
		String command = "ffmpeg -i " + filepath + " " +  resultFile;
		System.out.println(command);
		try{
		    run.exec("cmd.exe chcp 65001");  // cmd에서 한글문제로 썸네일이 만들어지지않을시 cmd창에서 utf-8로 변환하는 명령
		    run.exec(command);
		    
		}catch(Exception e){
		    System.out.println("error : "+e.getMessage());
		    e.printStackTrace();
		}
		return resultFile;
		
	}

	
}
