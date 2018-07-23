package com.daojia.open.confluence.worker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daojia.open.confluence.worker.service.CacheService;

@Controller
@RequestMapping("/")
public class CacheController {
	
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping(value="getCache/{name}")
	@ResponseBody
	public Object getCache(@PathVariable String name) {
		try {
			return cacheService.getReptile(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}