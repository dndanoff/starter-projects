package io.github.dndanoff.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;

@Configuration
public class Wro4jConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(Wro4jConfiguration.class);
	
	private static final String WRO_PROPERTEIS_PREFIX = "wro.";
	private static final String[] OTHER_WRO_PROP = {ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
													ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS};
	
	@Bean
	public FilterRegistrationBean<ConfigurableWroFilter> webResourceOptimizer(Environment env) {
		FilterRegistrationBean<ConfigurableWroFilter> fr = new FilterRegistrationBean<>();
		ConfigurableWroFilter filter = new ConfigurableWroFilter();
		Properties props = buildWroProperties(env);
		filter.setProperties(props);
		filter.setWroManagerFactory(new Wro4jCustomXmlModelManagerFactory(props));
		fr.setFilter(filter);
		fr.addUrlPatterns("/wro/*");
		return fr;
	}

	private Properties buildWroProperties(Environment env) {
		Properties prop = new Properties();
		for (ConfigConstants c : ConfigConstants.values()) {
			addProperty(env, prop, c.name());
		}
		for (String name : OTHER_WRO_PROP) {
			addProperty(env, prop, name);
		}
		LOGGER.debug("WRO4J properties {}", prop);
		return prop;
	}

	private void addProperty(Environment env, Properties to, String name) {
		String value = env.getProperty(WRO_PROPERTEIS_PREFIX + name);
		if (value != null) {
			to.put(name, value);
		}
	}
}
