package com.jeju.goormthon.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "Goormthon API"
	),
	servers = {
		@Server(url = "https://goormthon-2.goorm.training", description = "배포 서버"),
		@Server(url = "http://localhost:8080", description = "Local 서버")
	}
)
public class SwaggerConfig {

}