package com.raju.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {

		return new Docket(DocumentationType.SWAGGER_2).
				groupName("CurdOPerations").
				apiInfo(metaData()).
				select().
				apis(RequestHandlerSelectors.basePackage("com.raju.controller")).
				paths(PathSelectors.any()).
				build();
	}

	
	@SuppressWarnings("deprecation")
	private ApiInfo metaData() {

        return new ApiInfoBuilder().
        		title("CurdOPerations").
        		description("Basic CRUD operations").
        		contact("draju.raj19@gmail.com").
        		build();		
		        
		
		    }
}
