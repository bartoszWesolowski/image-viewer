package com.image.viever.utils;

import java.io.File;

public final class FileUtil {

    private static final double BYTES_IN_MEGA_BYTE_FACTOR = Math.pow(10, -6);

    private FileUtil() {
        throw new UnsupportedOperationException("No instance of that class.");
    }

    public static double sizeInMb(File file) {
        double totalSpaceInBytes = file.length();
        return totalSpaceInBytes * BYTES_IN_MEGA_BYTE_FACTOR;
    }
}
