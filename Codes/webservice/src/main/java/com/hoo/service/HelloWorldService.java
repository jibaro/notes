package com.hoo.service;

 

import javax.jws.WebParam;

import javax.jws.WebService;

import javax.jws.soap.SOAPBinding;

import javax.jws.soap.SOAPBinding.Style;

 

/**

 * <b>function:</b>CXF WebService 服务器端helloWorld示例

 * @author hoojo

 * @createDate 2011-3-16 上午08:29:07

 * @file HelloWorld.java

 * @package com.hoo.service

 * @project CXFWebService

 * @blog http://blog.csdn.net/IBM_hoojo

 * @email hoojo_@126.com

 * @version 1.0

 */

@WebService

@SOAPBinding(style = Style.RPC)

public class HelloWorldService {

    

    public String sayHello(@WebParam(name = "name") String name) {

        return name + " say: Hello World ";

    }

}