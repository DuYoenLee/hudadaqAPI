package com.hudadaq.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hudadaq.api.dao.APIDao;

@Service
public class APIServiceImpl implements APIService {
	
	@Inject
    private APIDao dao;
	
	public Map<String, Object> getMethod(Map<String, Object> map) throws Exception {
		return dao.getMethod(map);
	}

	@Override
	public List<Map<String, Object>> getRequest(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return dao.getRequest(paramMap);
	}

	@Override
	public List<Map<String, Object>> getResponse(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return dao.getResponse(paramMap);
	}
	
}
