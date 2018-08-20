package dubbo.zookeeper.hello.world.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import dubbo.zookeeper.hello.world.service.IProduct;

@Service
public class ProductService implements IProduct {
	public String getProductName() {

		return "jim";
	}
}