package edu.hope.biz.common.config;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* Swagger配置文件
* @param: null
* @author: zhaoMaoJie
* @Date: 15:08 2019/11/27 0027
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "mconfig", name = "swagger-ui-open", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SeClover数据平台开放接口")
                .description("Rest API接口")
                .termsOfServiceUrl("https://blog.csdn.net/youbitch1")
                .version("1.0")
                .build();
    }
}
