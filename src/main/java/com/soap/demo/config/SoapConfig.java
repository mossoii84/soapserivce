package com.soap.demo.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig  extends WsConfigurerAdapter {

    public static final String NAMESPACE = "http://www.soap.demo.ws.com/movies-ws";


    @Bean
    public ServletRegistrationBean servletRegistrationBean (ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "movieService")
    public Wsdl11Definition wsdl11Definition (XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("MoviesPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace(NAMESPACE);
        wsdl.setSchema(schema);
        return wsdl;
    }


    @Bean(name = "movies")
    public XsdSchema xsdSchema() {
        SimpleXsdSchema schema = new SimpleXsdSchema();
        schema.setXsd(new ClassPathResource("/xsd/movies.xsd"));
        return schema;
    }
}
