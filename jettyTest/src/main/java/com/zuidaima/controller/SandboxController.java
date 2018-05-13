package com.zuidaima.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;

@Controller
public class SandboxController {

	private static final Log LOG = LogFactory.getLog(SandboxController.class);

	@RequestMapping("/")
	public void index(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.html");
	}

	@RequestMapping("/aController")
	public void sayHello(HttpServletResponse response) throws IOException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Passing through my controller");
		}
		response.sendError(500, "Here's a controller in action");
	}

	@RequestMapping("/quit")
	public ModelAndView quit() throws Exception {
		int add1 = new Random().nextInt(100);
		int add2 = new Random().nextInt(100);
		return new ModelAndView("redirect:/arithmetic.html", "add", new int[] { add1, add2 });
	}

	@RequestMapping("/checkAnswer")
	@ResponseBody
	public String checkAnswer(@RequestBody String params) throws Exception {
		JSONObject jsonObj = JSONObject.fromObject(params);

		if (jsonObj.get("add1") == null || jsonObj.get("add2") == null) {
			return "�������";
		}

		if (jsonObj.get("answer") == null) {
			return "����д��";
		}

		int add1 = 0;
		if (StringUtils.isNumeric(String.valueOf(jsonObj.get("add1")))) {
			add1 = Integer.parseInt(String.valueOf(jsonObj.get("add1")));
			;
		} else {
			return "���������ʽ����";
		}

		int add2 = 0;
		if (StringUtils.isNumeric(String.valueOf(jsonObj.get("add2")))) {
			add2 = Integer.parseInt(String.valueOf(jsonObj.get("add2")));
			;
		} else {
			return "���������ʽ����";
		}

		int answer = 0;
		if (StringUtils.isNumeric(String.valueOf(jsonObj.get("answer")))) {
			answer = Integer.parseInt(String.valueOf(jsonObj.get("answer")));
			;
		} else {
			return "����������";
		}

		if ((add1 + add2) == answer) {
			return "����ȷ";
		} else {
			return "�𰸴�����ȷ��Ϊ" + (add1 + add2);
		}
	}
}
