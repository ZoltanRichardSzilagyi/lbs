package hu.zrs.lbs.project.generator.gradle;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import hu.zrs.lbs.api.project.Project;

public class ProjectGeneratorContext {

	private final Project project;

	private final Path projectParentFolder;

	private final List<String> includedProjects = new ArrayList<>();
	
	private File projectRootDirectory;

	private File projectBuildFile;

	private File workingDirectory;


	public ProjectGeneratorContext(final Project project, final Path projectParentFolder) {
		this.project = project;
		this.projectParentFolder = projectParentFolder;
	}

	public Project getProject() {
		return project;
	}

	public Path getProjectParentFolder() {
		return projectParentFolder;
	}

	public File getProjectRootDirectory() {
		return projectRootDirectory;
	}

	public void setProjectRootDirectory(File projectRootDirectory) {
		this.projectRootDirectory = projectRootDirectory;
	}

	public File getProjectBuildFile() {
		return projectBuildFile;
	}

	public void setProjectBuildFile(File projectBuildFile) {
		this.projectBuildFile = projectBuildFile;
	}

	public List<String> getIncludedProjects() {
		return includedProjects;
	}

	public void addProjectToInclude(String projectName) {
		includedProjects.add(projectName);
	}

	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

}
