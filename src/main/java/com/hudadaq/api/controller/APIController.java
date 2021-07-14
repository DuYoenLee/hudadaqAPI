package com.hudadaq.api.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hudadaq.api.service.APIService;
import com.hudadaq.api.util.BanapleAPIModule;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/")
public class APIController {

	@Autowired
	private APIService service;

	private static final Logger logger = LoggerFactory.getLogger(APIController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/api.do", produces = "application/json; charset=utf8")
	public JSONObject api(MultipartHttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> map) {
		logger.info("Welcome home! The client locale is {}.", request);

		String param = "";

		logger.info("map => " + map.toString());
		//HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> methodMap = new HashMap<String, Object>();

		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		try {

			// Method_ID의 SEQ 정보 얻 어오기 methodMap = service.getMethod(map);
			methodMap = service.getMethod(map);
			
			logger.info("methodMap => " + methodMap.toString());

			// Method_SEQ에 Mapping된 RequestParam변수정보 얻어오기 List<Map<String, Object>>
			List<Map<String, Object>> reqList = service.getRequest(methodMap);
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("method", String.valueOf(map.get("method")));
			for (int i = 0; i < reqList.size(); i++) {
				Map<String, Object> reqMap = reqList.get(i);
				logger.info("reqMap => " + reqMap.toString());
				String paramKey = String.valueOf(reqMap.get("request_id"));
				paramMap.put(paramKey, String.valueOf(map.get(paramKey)));
				/*
				 * if (!makeParameter(map, String.valueOf(reqMap.get("request_id")))) {
				 * resultMap.put("error", "정보가 다릅니다."); }
				 */
			}

			//Method_SEQ에 Mapping된 ResponseParam변수정보 얻어오기 List<Map<String, Object>>
			//resList = service.getResponse(methodMap); // Banaple API 호출하여 Response 정보 얻어오기
			
			logger.info("paramMap => " + paramMap.toString());
			
			//DB에 셋팅된 Request의 값으로 API파라미터 값 문자열 셋팅
			param = getMapKeyValue(paramMap);
			
			//Header에 있는값 셋팅
			Map<String, String> headersMap = getHeadersInfo(request);
			System.out.println("Header Info" + headersMap.toString());

			map.putAll(headersMap);
			
			// JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
			jsonObj = (JSONObject) jsonParse.parse(BanapleAPIModule.getData(param, map));
			// resultMap.put("result", BanapleAPIModule.getData(getMapKeyValue(map)));
			logger.info(jsonObj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObj;
	}

	public static String getMapKeyValue(Map<String, String> map) {
		Iterator<String> it = map.keySet().iterator();

		String result = "";

		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);

			if (it.hasNext()) {
				result += key + "=" + value + "&";
			} else {
				result += key + "=" + value;
			}
		}

		return result;

	}

	public static boolean makeParameter(Map<String, Object> map, String paramStr) {
		boolean bool = false;

		Iterator<String> it = map.keySet().iterator();
		String result = "";

		while (it.hasNext()) {
			String key = it.next();
			if (key.equals(paramStr)) {
				bool = true;
				break;
			}

		}
		return bool;
	}

	public static Map<String, String> getHeadersInfo(MultipartHttpServletRequest req) {
		StringBuffer builder = new StringBuffer();
		Map<String, String> resultMap = new HashMap<String, String>();
		Enumeration<String> headerNames = req.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			builder = new StringBuffer("");
			String headerName = headerNames.nextElement();
			Enumeration<String> headers = req.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				builder.append(headerValue);
				if (headers.hasMoreElements()) {
					builder.append(",");
				}
			}
			resultMap.put(headerName.toLowerCase(), builder.toString());
		}
		return resultMap;
	}

	/*
	 * public String paramString(ArrayList<HashMap<String, Object>> list) {
	 * 
	 * }
	 */

}
