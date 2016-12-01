package hu.zrs.lbs.api.agent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericAgentProperties implements AgentProperties<String, String> {

	private final Map<String, String> properties = new ConcurrentHashMap<>();

	@Override
	public void addProperty(final String key, final String value) {
		properties.put(key, value);
	}

	@Override
	public String getProperty(final String key) {
		return properties.get(key);
	}

}
