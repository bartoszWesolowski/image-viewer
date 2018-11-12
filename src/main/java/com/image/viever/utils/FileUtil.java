package com.image.viever.utils;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class FileUtil {


    private static final Joiner PATH_JOINER = Joiner.on(File.separator)
            .skipNulls();

    private static final double BYTES_IN_MEGA_BYTE_FACTOR = Math.pow(10, -6);

    private FileUtil() {
        throw new UnsupportedOperationException("No instance of that class.");
    }

    public static double sizeInMb(File file) {
        double totalSpaceInBytes = file.length();
        return totalSpaceInBytes * BYTES_IN_MEGA_BYTE_FACTOR;
    }

    public static void copyFile(String sourceFileAbsolutePath, File destinationDirectory) throws IOException {
        if (!destinationDirectory.exists() || !destinationDirectory.isDirectory()) {
            throw new IllegalArgumentException("Destination must exist and be directory");
        }
        Path source = Paths.get(sourceFileAbsolutePath);
        String sourceFileName = source.toFile().getName();
        Path destinatationFile = getDestinationFile(destinationDirectory, sourceFileName);
        Files.copy(source, destinatationFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private static Path getDestinationFile(File parentDirectory, String fileName) {
        String path = PATH_JOINER.join(parentDirectory.getAbsolutePath(), fileName);
        return Paths.get(path);
    }
}
