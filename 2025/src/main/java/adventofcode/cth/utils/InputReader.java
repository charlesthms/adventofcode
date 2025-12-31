package adventofcode.cth.utils;



import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputReader {

    public static String readResourceToString(String resourceName)  {
        try (InputStream is = InputReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource: " + resourceName, e);
        }
    }
}
