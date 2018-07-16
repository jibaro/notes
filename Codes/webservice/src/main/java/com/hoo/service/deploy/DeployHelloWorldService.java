package com.hoo.service.deploy;

import javax.xml.ws.Endpoint;
import com.hoo.service.HelloWorldService;

/**
 * <b>function:</b> 发布CXF WebService
 * @author hoojo
 * @createDate 2011-3-17 上午09:24:33
 * @file DeployHelloWorldService.java
 * @package com.hoo.deploy
 * @project CXFWebService
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DeployHelloWorldService {
	
	/**
	 * <b>function:</b>发布WebService
	 * @author hoojo
	 */
	public static void deployService() {
		System.out.println("Server start ……");
		HelloWorldService service = new HelloWorldService();
		String address = "http://localhost:9000/helloWorld";
		Endpoint.publish(address, service);
	}
	
	public static void main(String[] args) throws InterruptedException {
		//发布WebService
		deployService();
		System.out.println("server ready ……");
		Thread.sleep(1000 * 60);
		System.out.println("server exiting");
		//休眠60秒后就退出
		System.exit(0);
	}
}