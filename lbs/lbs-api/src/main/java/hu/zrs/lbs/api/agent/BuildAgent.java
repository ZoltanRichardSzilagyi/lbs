package hu.zrs.lbs.api.agent;

import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;

public class BuildAgent implements Agent {

	private String id;

	private String name;

	private AgentStatus status;

	private Path installationPath;

	private URI uri;

	private LocalDate timeOfLastActivity;

	// TODO fix serialization issue
	// private LocalDate uptime;

	private AgentProperties<String, String> properties;

	public BuildAgent() {
	}

	public BuildAgent(final String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public AgentStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(final AgentStatus status) {
		this.status = status;
	}

	@Override
	public Path getInstallationPath() {
		return installationPath;
	}

	public void setInstallationPath(final Path installationPath) {
		this.installationPath = installationPath;
	}

	@Override
	public URI getUri() {
		return uri;
	}

	public void setUri(final URI uri) {
		this.uri = uri;
	}

	public LocalDate getTimeOfLastActivity() {
		return timeOfLastActivity;
	}

	public void setTimeOfLastActivity(final LocalDate timeOfLastActivity) {
		this.timeOfLastActivity = timeOfLastActivity;
	}

	@Override
	public AgentProperties<String, String> getProperties() {
		return properties;
	}

	@Override
	public void setProperties(final AgentProperties properties) {
		this.properties = properties;
	}


	@Override
	public int hashCode() {
		return id.hashCode();
	}


	@Override
	public boolean equals(final Object obj) {
		return id.equals(obj);
	}

}
