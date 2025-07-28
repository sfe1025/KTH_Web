package app.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
@Import({
	AppDatasource.class,
})
@PropertySources({
	@PropertySource("classpath:/application-${spring.profiles.active:local}.properties")
})
public class AppConfig implements WebMvcConfigurer {
	
	//private ApplicationContext applicationContext;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		//templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		//templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false); // 캐시 사용 안함(사용하면 html 수정시 서버 재기동 필요)
		return templateResolver;
	}
	
//	@Override
//	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//		exceptionResolvers.add(new MyHandlerExceptionResolver());
//	}

//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver());
//		templateEngine.setEnableSpringELCompiler(true); // Spring EL 사용
//		// add custom tag
//		//templateEngine.addDialect(new EgovPaginationDialect());
//		return templateEngine;
//	}

//	@Bean
//	public ThymeleafViewResolver thymeleafViewResolver() {
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
//		viewResolver.setTemplateEngine(templateEngine());
//		return viewResolver;
//	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//      registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//      registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
//	}


}