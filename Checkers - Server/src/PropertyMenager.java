import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyMenager {
    private static PropertyMenager INSTANCE = null;
    private Properties prop = new Properties();

    private PropertyMenager() throws IOException{
        InputStream var1 = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (var1 != null) {
            this.prop.load(var1);
        } else {
            throw new FileNotFoundException("Property file is not found");
        }
    }

    public static PropertyMenager getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new PropertyMenager();
        }
        return INSTANCE;
    }

    public int getPort() {
        return Integer.parseInt(this.prop.getProperty("port"));
    }

}
