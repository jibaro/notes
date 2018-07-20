package com.daojia.open.confluence.worker.utils;

import com.alibaba.fastjson.JSON;

public class JsonMapper {

	public String toJson(Object value) {
		try {
			return JSON.toJSONString(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
