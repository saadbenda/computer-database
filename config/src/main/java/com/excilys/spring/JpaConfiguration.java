//package spring;
//
//import javax.sql.DataSource;
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//
//
//@Configuration
//@EnableJpaRepositories()
//@EnableTransactionManagement
//@Import(SpringMvcConfiguration.class)
//
//public class JpaConfiguration {
//	/*
//	 * @Bean public LocalEntityManagerFactoryBean entityManagerFactory() {
//	 * LocalEntityManagerFactoryBean factoryBean = new
//	 * LocalEntityManagerFactoryBean(); factoryBean.setPersistenceUnitName("");
//	 * 
//	 * return factoryBean; }
//	 */
//
//	/*
//	 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//	 * LocalContainerEntityManagerFactoryBean factory = new
//	 * LocalContainerEntityManagerFactoryBean(); factory.setDataSource(new
//	 * DataSourceBean()); factory.setPersistenceUnitName(l);
//	 * //em.setPackagesToScan(new String[] { "com.baeldung.persistence.model" });
//	 * 
//	 * 
//	 * /* JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	 * vendorAdapter.setGenerateDdl(true);
//	 * 
//	 * factory.setJpaVendorAdapter(vendorAdapter);
//	 * factory.setJpaProperties(additionalProperties());
//	 */
//	//return factory;
//	//}
//	
//	 @Autowired
//	 DataSource datasource;
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("model","persistence");
//		factory.setDataSource(datasource);
//		return factory;
//	}
//
//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory);
//
//		return transactionManager;
//	}
//}