package org.bnss;

import org.apache.catalina.connector.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.context.embedded.tomcat.*;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class BnssApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BnssApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BnssApplication.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("/*/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("/*/*/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("/*/*/*/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("/*/*/*/*/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
			}
		};
	}

}
