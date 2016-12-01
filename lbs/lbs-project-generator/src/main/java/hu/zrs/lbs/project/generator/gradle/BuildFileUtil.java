package hu.zrs.lbs.project.generator.gradle;

import java.io.File;
import java.io.IOException;

public class BuildFileUtil {

	private BuildFileUtil() {
	}

	public static File createBuildFile(File buildFileDirectory) {
		return createFile("build", buildFileDirectory);
	}

	public static File createBuildSettingsFile(File settingsFileDirectory) {
		return createFile("settings", settingsFileDirectory);
	}

	private static File createFile(String fileName, File directory) {
		final File buildFilePath = new File(directory, fileName + ".gradle");
		try {
			buildFilePath.createNewFile();
		} catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		return buildFilePath;
	}

}
