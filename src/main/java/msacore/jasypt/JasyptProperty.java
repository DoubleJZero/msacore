package msacore.jasypt;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "encrypt")
@Component
public class JasyptProperty {
    String algolithm;
    int keyObtentionIterations;
    int poolSize;
    String saltGeneratorClassName;
    String stringOutputType;
}
