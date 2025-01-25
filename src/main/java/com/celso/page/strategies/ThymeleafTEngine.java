package com.celso.page.strategies;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Component
public class ThymeleafTEngine implements TemplateEngineStrategy {
	
	private TemplateEngine engine;
	
	public ThymeleafTEngine() {
		super();
		TemplateEngine engine = new TemplateEngine();
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
	    
		resolver.setPrefix("/thymeleaf/");
	    resolver.setTemplateMode(TemplateMode.HTML);
	    engine.setTemplateResolver(resolver);
		this.engine = engine;
	}

	@Override
	public String generatePage(String templateName, Map<String, Object> data) {
		Context context = new Context();
		
        context.setVariables(data);
		return engine.process(templateName, context);
	}

}
