package cn.com.app.api;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by user on 2019/3/11.
 */
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    public Docket docketConfig(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //ApiInfo String title, String description, String version, String termsOfServiceUrl, String contactName, String license, String licenseUrl
        ApiInfo apiInfo = new ApiInfo(
                "api",
                "swagger api",
                "v-api-q.0.1",
                "www.jianinghome.com",
                "chunsheng,zhang",
                "",
                ""
        ) ;
        return docket;
    }
}
