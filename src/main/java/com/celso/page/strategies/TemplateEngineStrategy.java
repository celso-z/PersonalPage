package com.celso.page.strategies;

import java.util.Map;

public interface TemplateEngineStrategy {
	public String generatePage(String templateName, Map<String, Object> context);
}
