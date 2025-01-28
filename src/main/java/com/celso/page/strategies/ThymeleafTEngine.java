package com.celso.page.strategies;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Component
public class ThymeleafTEngine implements TemplateEngineStrategy {
	
	private SpringTemplateEngine engine;
	
	public ThymeleafTEngine() {
		super();
		SpringTemplateEngine engine = new SpringTemplateEngine();
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		LayoutDialect dialect = new LayoutDialect();
	    
		resolver.setPrefix("/templates/thymeleaf/");
		resolver.setSuffix(".html");
	    resolver.setTemplateMode(TemplateMode.HTML);
	    engine.setTemplateResolver(resolver);
	    engine.addDialect(dialect);
		this.engine = engine;
	}

	@Override
	public String generatePage(String templateName, Map<String, Object> data) {
		Context context = new Context();
		
        context.setVariables(data);
		return engine.process(templateName, context);
	}

}
