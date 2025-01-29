package com.celso.page.strategies;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Component
public class ThymeleafTEngine implements TemplateEngineStrategy {
	
	private SpringTemplateEngine engine;
	@Value("${spring.thymeleaf.prefix}")
	private String prefix;
	
	public ThymeleafTEngine() {
		super();
		SpringTemplateEngine engine = new SpringTemplateEngine();
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		LayoutDialect dialect = new LayoutDialect();
	    
		prefix = prefix == null ? "/templates/thymeleaf/" : prefix;
		resolver.setPrefix(prefix);
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

	@Override
	public List<String> getTemplates() throws URISyntaxException, IOException {
		List<String> templates = new LinkedList<>();
		URI uri = this.getClass().getResource(prefix).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath(prefix);
        } else {
            myPath = Paths.get(uri);
        }
        try (Stream<Path> walk = Files.walk(myPath)) {
            templates = walk.filter(Files::isRegularFile)
            		.map(path -> path.toFile().getName())
                    .collect(Collectors.toList());
        }
        
        return templates;
        
	}

}
