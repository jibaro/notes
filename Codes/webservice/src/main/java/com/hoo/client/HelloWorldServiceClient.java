package com.hoo.client;

 

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.hoo.service.IHelloWorldService;

 

/**

 * <b>function:</b>CXF WebService�ͻ��˵��ô���

 * @author hoojo

 * @createDate 2011-3-16 ����09:03:49

 * @file HelloWorldServiceClient.java

 * @package com.hoo.client

 * @project CXFWebService

 * @blog http://blog.csdn.net/IBM_hoojo

 * @email hoojo_@126.com

 * @version 1.0

 */

public class HelloWorldServiceClient {

    

    public static void main(String[] args) {

        //����WebService

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(IHelloWorldService.class);

        factory.setAddress("http://localhost:9000/helloWorld");

        

        IHelloWorldService service = (IHelloWorldService) factory.create();

        System.out.println("[result]" + service.sayHello("hoojo"));

    }

}