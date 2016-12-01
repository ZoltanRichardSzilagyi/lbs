package hu.zrs.lbs.api.agent;

import java.nio.file.Path;

public class GenericAgent implements Agent {

	private String id;

	private String name;

	private AgentStatus status;

	private Path installationPath;

	// TODO fix serialization issue
	// private LocalDate uptime;

	private AgentProperties<String, String> properties;

	public GenericAgent() {
	}

	public GenericAgent(final String id) {
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
