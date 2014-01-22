package com.gm.infobus.repository.resultHandler;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class UsersMapHandler implements ResultHandler {
	@SuppressWarnings("rawtypes")
	private final Map mappedResults = new HashMap();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleResult(ResultContext context) {
		Map map = (Map) context.getResultObject();
		mappedResults.put(map.get("userName"), map.get("userDetail"));
	}

	@SuppressWarnings("rawtypes")
	public Map getMappedResults() {
		return mappedResults;
	}
}
