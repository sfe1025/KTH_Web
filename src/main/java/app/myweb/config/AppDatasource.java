package app.myweb.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppDatasource {
	
	@Autowired
	Environment env;
	
	private String dbType;

	private String className;

	private String url;

	private String userName;

	private String password;
	
	@PostConstruct
	void init() {
		log.info("====================================  post init START~ ====================================  ");
		
		dbType = env.getProperty("Globals.DbType");
		log.debug("config dbType (Globals.DbType) :: {}", dbType);
		//Exception 처리 필요
		className = env.getProperty("Globals." + dbType + ".DriverClassName");
		url = env.getProperty("Globals." + dbType + ".Url");
		userName = env.getProperty("Globals." + dbType + ".UserName");
		password = env.getProperty("Globals." + dbType + ".Password");
		
		log.info("====================================  post init E N D~ ====================================  ");
	}
	
	/**
	 * @return [dataSource 설정] basicDataSource 설정
	 */
	private DataSource basicDataSource() {
		log.info("====================================  basicDataSource START ====================================  ");
		log.debug("-- basicDataSource --");
		log.debug("className : {}", className);
		log.debug("url       : {}", url);
		log.debug("userName  : {}", userName);
		log.debug("password  : {}", password);
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(className);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(userName);
		basicDataSource.setPassword(password);
		
		
//		log.debug("initialSize   : {}", initialSize);
//		log.debug("maxActive     : {}", maxActive);
//		log.debug("maxIdle       : {}", maxIdle);
//		log.debug("minIdle       : {}", minIdle);
//		log.debug("maxWaitMillis : {}", maxWaitMillis);
//		
//		basicDataSource.setInitialSize( initialSize );
//		basicDataSource.setMaxTotal( maxActive );
//		basicDataSource.setMaxIdle( maxIdle );
//		basicDataSource.setMinIdle( minIdle );
//		basicDataSource.setMaxWaitMillis( maxWaitMillis );


//		log.debug("-- dataSourceWeather DB Connection Test --");
		try ( /* AutoCloseable */ java.sql.Connection conn = basicDataSource.getConnection()) {
			log.debug("-- CONNECTED.         --");
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		log.info("====================================  basicDataSource E N D ====================================  ");
		return basicDataSource;
	}
	
	/**
	 * @return [DataSource 설정]
	 */
	@Primary
	@Bean(name = {"dataSource"})
	public DataSource dataSource() {
		return basicDataSource();
	}

}
