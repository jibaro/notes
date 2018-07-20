package com.daojia.open.confluence.worker.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class CacheController {
	
	@RequestMapping(value="getCache/{name}")
	@ResponseBody
	public String getCache(@PathVariable String name) {
		return name;
	}
}