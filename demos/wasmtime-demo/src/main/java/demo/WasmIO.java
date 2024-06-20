package demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WasmIO {

    public static byte[] loadWasmFromClasspath(String path) {
        try (var is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException(path);
            }
            return is.readAllBytes();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static Path locateWatFromClasspath(String path) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
            if (resource == null) {
                throw new FileNotFoundException(path);
            }
            return Paths.get(resource.toURI());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
