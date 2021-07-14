package com.hudadaq.api.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class APIDaoImpl implements APIDao {

	@Inject
	private SqlSession session;
	
	public Map<String, Object> getMethod(Map<String, Object> map)  throws Exception{
		// TODO Auto-generated method stub
		return session.selectOne("APIMap.getMethod", map);
	}

	@Override
	public List<Map<String, Object>> getRequest(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		//List<Map<String, Object>> list = session.selectList("APIMap.getRequest", paramMap);
		//return (List<Map<String, String>>) session.selectList("APIMap.getRequest", paramMap);
		return session.selectList("APIMap.getRequest", paramMap);
	}

	@Override
	public List<Map<String, Object>> getResponse(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		return session.selectList("APIMap.getResponse", paramMap);
	}


}
