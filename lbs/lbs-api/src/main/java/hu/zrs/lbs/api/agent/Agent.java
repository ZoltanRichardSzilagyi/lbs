package hu.zrs.lbs.api.agent;

import java.net.URI;
import java.nio.file.Path;

public interface Agent {

	String getId();

	String getName();

	Path getInstallationPath();

	AgentStatus getStatus();

	void setStatus(AgentStatus status);

	URI getUri();

	// LocalDate getUptime();

	<K, V> AgentProperties<K, V> getProperties();

	<K, V> void setProperties(AgentProperties<K, V> properties);

}
