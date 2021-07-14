package com.hudadaq.api.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface APIDao {

	Map<String, Object> getMethod(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> getRequest(Map<String, Object> paramMap) throws Exception;

	List<Map<String, Object>> getResponse(Map<String, Object> paramMap) throws Exception;

}
