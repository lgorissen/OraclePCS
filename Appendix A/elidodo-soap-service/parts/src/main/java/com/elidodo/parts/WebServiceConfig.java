package com.elidodo.parts;

import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.concurrent.Executor;

@EnableWs
@Configuration
@EnableAsync
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/parts/*");
	}


	// The parts Bean below was used with the DefaultWsdl11Definition:
        // Advantige is that this generated the wsdl itself....

	@Bean(name = "parts")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema partsSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PartsPort");
		wsdl11Definition.setLocationUri("/ws/parts");
		wsdl11Definition.setTargetNamespace("http://elidodo.com/parts/parts/ws");
		wsdl11Definition.setSchema(partsSchema);

		return wsdl11Definition;
	}


	@Bean
	public XsdSchema partsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("parts.xsd"));
	}

	@Bean
	public Executor asyncExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("callbackOrder-");
		executor.initialize();

		return executor;

	} 

	@Bean
	public Jaxb2Marshaller marshaller() {

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.elidodo.parts.ws");

		return marshaller;
	}

	@Bean
	public OrderCallbackClient orderCallbackClient(Jaxb2Marshaller marshaller) {

		OrderCallbackClient client = new OrderCallbackClient();
		client.setDefaultUri("http://<host>:<port>/endpoint");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);

		return client;
	}

}
