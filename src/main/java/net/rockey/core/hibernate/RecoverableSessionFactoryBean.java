package net.rockey.core.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class RecoverableSessionFactoryBean implements FactoryBean,
		DisposableBean, InitializingBean {
	/** logger. */
	private static Logger logger = LoggerFactory
			.getLogger(RecoverableSessionFactoryBean.class);
	private SessionFactoryWrapper sessionFactoryWrapper;
	private DataSource dataSource;
	private String[] packagesToScan;
	private Properties hibernateProperties;
	private LocalSessionFactoryBean localSessionFactoryBean;

	@Override
	public void afterPropertiesSet() throws Exception {
		// init SessionFactoryWrapper
		sessionFactoryWrapper = new SessionFactoryWrapper();

		try {
			// init LocalSessionFactoryBean
			localSessionFactoryBean = new LocalSessionFactoryBean();

			logger.info("{}", dataSource);
			logger.info("{}", hibernateProperties);
			logger.info("{}", packagesToScan);

			localSessionFactoryBean.setDataSource(dataSource);
			localSessionFactoryBean.setHibernateProperties(hibernateProperties);
			localSessionFactoryBean.setPackagesToScan(packagesToScan);

			localSessionFactoryBean.afterPropertiesSet();

			SessionFactory sessionFactory = localSessionFactoryBean.getObject();
			sessionFactoryWrapper.setSessionFactory(sessionFactory);
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

	@Override
	public void destroy() throws Exception {
		sessionFactoryWrapper = null;

		if (localSessionFactoryBean.getObject() != null) {
			localSessionFactoryBean.destroy();
		}
	}

	@Override
	public Object getObject() throws Exception {
		return sessionFactoryWrapper;
	}

	@Override
	public Class getObjectType() {
		return SessionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	// ~ ======================================================================
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setHibernateProperties(Properties hibernateProperties) {
		this.hibernateProperties = hibernateProperties;
	}

	public void setPackagesToScan(String... packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

}
