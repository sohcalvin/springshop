package csoh.reference.springshop.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="csoh.reference.springshop.controller")
@EnableTransactionManagement
public class SpringShopConfiguration {
	@Bean
	public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	 @Bean
	   public DataSource dataSource() throws NamingException{

		InitialContext ic = new InitialContext();
		 String dsName = "java:comp/env/jdbc/java-hdi-container";
		 DataSource dataSource = (javax.sql.DataSource)ic.lookup(dsName);
//	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
//	      dataSource.setUsername( "tutorialuser" );
//	      dataSource.setPassword( "tutorialmy5ql" );
	      return dataSource;
	   }
	  @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      Map<String,String> jpaProperties = new HashMap<String,String>(1);
	      jpaProperties.put("eclipselink.weaving", "false");
	      jpaProperties.put("eclipselink.ddl-generation", "create-tables");
	      em.setJpaPropertyMap(jpaProperties);	    	 
	      return em;
	   }
	  
	  // Need this if not you will get "No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call"
	  // From the ORM source code comment :
	  // We need a transactional target now, according to the JPA spec.
	  // Otherwise, the operation would get accepted but remain unflushed...
	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory emf){ 
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	      return transactionManager;
	  }
	
	  

}
