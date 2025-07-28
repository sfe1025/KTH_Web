package app.myweb.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"app.myweb"}) 
public class KthWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		log.info("##### KthWebApplication Start #####");
		
		SpringApplication.run(KthWebApplication.class, args);
	}

}
