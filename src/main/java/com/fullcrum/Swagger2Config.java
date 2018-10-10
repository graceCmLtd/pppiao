package com.fullcrum;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class Swagger2Config {

	@Bean
	public Docket apiDocket() {
		System.out.println("new docket 6666666666666666666666666666");
		new Docket(DocumentationType.SWAGGER_12).apiInfo(apiInfo());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.fullcrum.controller.sys")).paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("gavin", "http://pengpengpiao.com", "gavin.hou@gmail.com");
		Collection<VendorExtension> collection = new ArrayList<>();
		return new ApiInfo("碰碰票api文档", "碰碰票平台接口api文档", "0.0.1", "http://pengpengpiao.com", contact, "证书", "http://pengpengpiao.com",collection);
	}
}
