import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigMain {


    private static final String CONFIG_FILE = "config_ui.properties";

    public static Properties loadProperties() {
        Properties props = new Properties();


        try (InputStream propstream = ConfigMain.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (propstream == null) {
                throw new RuntimeException("Файл не найден в ClassPath: " + CONFIG_FILE);
            }
            props.load(propstream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}
