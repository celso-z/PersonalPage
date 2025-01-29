package com.celso.page.strategies;

import java.util.Map;
import java.util.List;

public interface TemplateEngineStrategy {
	public String generatePage(String templateName, Map<String, Object> context);
	public List<String> getTemplates();
}
