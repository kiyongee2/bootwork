package com.khit.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	
	@GetMapping("/public-data/main")
	public String dataMain() {
		return "public-data/main"; //public-data/main.html
	}
	
	@GetMapping("/disaster.do")
	public @ResponseBody String getData() {
		try {
			//지진 해일 대피소 데이터 테스트
			String serviceKey = "Ekmt%2F9o9LH7HCquGXEIc3o%2BqAAtZfnTGEYWIfUhvcoCyVWLiJLAWujWkNctV06dlz5c0oaKZ1rft2llpEDl%2BTw%3D%3D"; 
			String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
				url += "?serviceKey=" + serviceKey;
				url	+= "&pageNo=1";
				url	+= "&numOfRows=20";
				url	+= "&type=json";
			
			//데이터를 받기 위해서 URL 클래스의 객체 생성
			URL requestUrl = new URL(url);
			System.out.println(url);
			
			//openConnection()을 이용한 연결
			HttpURLConnection conn = (HttpURLConnection)requestUrl.openConnection();
			conn.setRequestMethod("GET");  //대문자로 명시함
			conn.setRequestProperty("content-type", "application/json");
			
			//응답 상태 코드(200, 403, 404, 500)
			int status = conn.getResponseCode();
			System.out.println(status);
			
			//버퍼: 입출력속도 향상을 위해서 데이터를 일시적으로 메모리 영역에 모아두는 것
			//BufferedReader(보조스트림) : 기반스트림(생성자) - InputStreamReader
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			String responseText = "";
			String line;  //한 행에 있는 데이터
			while((line = br.readLine()) != null) {
				responseText += line;
			}
			System.out.println(responseText);
			
			br.close();   //br 종료
			conn.disconnect();  //연결 종료
			
			return responseText;  //json 형태로 반환함
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
