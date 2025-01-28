package com.celso.page.services;

import com.celso.page.strategies.TemplateEngineStrategy;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

public class PageGenerationService {
	private TemplateEngineStrategy templateEngine;
	private Path outputFile;

	public PageGenerationService(TemplateEngineStrategy templateEngine, Path outputFile) {
		this.templateEngine = templateEngine;
		this.outputFile = outputFile;
	}
	
	public String generatePage(String templateName, Map<String, Object> context) {
		return templateEngine.generatePage(templateName, context);
	}
	
	public void savePageToFile(String pageString) throws IOException {
		Files.write(outputFile, pageString.getBytes(), StandardOpenOption.CREATE);
	}
	
	public void setTemplateEngineStrategy(TemplateEngineStrategy templateEngine) {
		this.templateEngine = templateEngine;
	}

	public Path getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(Path outputFile) {
		this.outputFile = outputFile;
	}
	
}
