package hu.zrs.lbs.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import liquibase.resource.ResourceAccessor;

public class InMemoryResourceAccessor implements ResourceAccessor {

	private final Set<InputStream> inputData;

	public InMemoryResourceAccessor(final InputStream inputData) {
		this.inputData = new HashSet<>();
		this.inputData.add(inputData);
	}


	@Override
	public Set<InputStream> getResourcesAsStream(final String path) throws IOException {
		return inputData;
	}

	@Override
	public Set<String> list(final String relativeTo, final String path, final boolean includeFiles, final boolean includeDirectories, final boolean recursive) throws IOException {
		return null;
	}

	@Override
	public ClassLoader toClassLoader() {
		return this.getClass().getClassLoader();
	}

}
