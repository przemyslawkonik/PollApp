package com.pollapp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validator;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = { "com.pollapp"})
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.pollapp.repository"})
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	Environment env;
		
	//@Bean(name = "dataSource")
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "heroku.datasource")
	public DataSource dataSource() {
	    /*DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    //driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setDriverClassName(env.getProperty("mysql.datasource"));
		driverManagerDataSource.setUrl(env.getProperty("mysql.datasource"));
		driverManagerDataSource.setUsername(env.getProperty("mysql.datasource"));
		driverManagerDataSource.setPassword(env.getProperty("mysql.datasource"));*/
	    //driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/pollapp");
	    //driverManagerDataSource.setUsername("root");
	    //driverManagerDataSource.setPassword("coderslab");
		
	    /*String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
			driverManagerDataSource.setUrl(dbUrl);
			driverManagerDataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
			driverManagerDataSource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
		}*/
	    //return driverManagerDataSource;
		return DataSourceBuilder.create().build();
		//return new DriverManagerDataSource();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(dataSource)
				.packages(env.getProperty("entitymanager.packagesToScan"))
				.properties(jpaProperties())
				.build();

		//LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		//emfb.setPersistenceUnitName("pollapp");

		/*LocalContainerEntityManagerFactoryBean entityManagerFactory =
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(
				env.getProperty("entitymanager.packagesToScan"));*/

		/*String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			Properties jpaProperties = new Properties();
			jpaProperties.put("javax.persistence.jdbc.url", System.getenv("JDBC_DATABASE_URL"));
			jpaProperties.put("javax.persistence.jdbc.user", System.getenv("JDBC_DATABASE_USERNAME"));
			jpaProperties.put("javax.persistence.jdbc.password", System.getenv("JDBC_DATABASE_PASSWORD"));
			jpaProperties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
			jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			emfb.setJpaProperties(jpaProperties);
			emfb.afterPropertiesSet();
		}*/

		/*HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		Properties additionalProperties = new Properties();
		additionalProperties.put(
				"hibernate.dialect",
				env.getProperty("hibernate.dialect"));
		additionalProperties.put(
				"hibernate.show_sql",
				env.getProperty("hibernate.show_sql"));
		additionalProperties.put(
				"hibernate.hbm2ddl.auto",
				env.getProperty("hibernate.hbm2ddl.auto"));
		entityManagerFactory.setJpaProperties(additionalProperties);
		return entityManagerFactory;*/
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager =
				new JpaTransactionManager();
		//transactionManager.setEntityManagerFactory(
				//entityManagerFactory.getObject());
		return transactionManager;
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Autowired
	private DataSource dataSource;

	//@Autowired
	//private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	private Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		return props;
	}

}
