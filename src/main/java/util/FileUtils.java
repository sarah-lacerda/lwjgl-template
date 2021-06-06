package util;

import java.io.InputStream;

public class FileUtils {

    private FileUtils() {}

    public static InputStream getFileFromResourceAsStream(String filePath) {
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("Error while accessing resource, file not found! " + filePath);
        } else {
            return inputStream;
        }
    }
}
