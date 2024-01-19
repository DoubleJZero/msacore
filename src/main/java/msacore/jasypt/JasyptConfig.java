package msacore.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
@RequiredArgsConstructor
public class JasyptConfig {

    private final JasyptProperty jasyptProperty;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncyptor() {
        PooledPBEStringEncryptor cncryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(mkPasswd());
        config.setAlgorithm(jasyptProperty.getAlgolithm());
        config.setKeyObtentionIterations(jasyptProperty.getKeyObtentionIterations());
        config.setPoolSize(jasyptProperty.getPoolSize());
        config.setSaltGeneratorClassName(jasyptProperty.getSaltGeneratorClassName());
        config.setStringOutputType(jasyptProperty.getStringOutputType());
        cncryptor.setConfig(config);

        return cncryptor;
    }

    private String mkPasswd() {
        StringBuilder sb = new StringBuilder();

        int[] a = {9,2,1,3,4,7,5,10,6,8};
        int[] b = {10,5,3,2,6,8,1,9,4,7};

        for (int aa : a) {
            for (int bb : b) {
                int n = aa * bb;
                if (47 < n && 91 > n) {
                    sb.append((char)n);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        JasyptProperty jp = new JasyptProperty();
        jp.algolithm = "PBEWithMD5AndDES";
        jp.keyObtentionIterations = 1000;
        jp.poolSize = 1;
        jp.saltGeneratorClassName = "org.jasypt.salt.RandomSaltGenerator";
        jp.stringOutputType = "base64";
        StringEncryptor enc = new JasyptConfig(jp).stringEncyptor();

        String encUrl = enc.encrypt("jdbc:mariadb://192.168.56.1:3306/jandb");
        String encUsername = enc.encrypt("jandb");
        String encPassword = enc.encrypt("jandb");

        System.out.println("encUrl :: " + encUrl + ", encUsername :: " + encUsername + ", encPassword :: " + encPassword);
        // encUrl :: gMR/NvBVO1x4uZVtycBNPxnRWuk84XgGtvEYTYxm38T28uyA3hF8dJEvwnKlHnc+, encUsername :: AOlqjc7Vu97CirlATUxSDg==, encPassword :: qg5r8Eg7+MunDRuXG0nfcg==
    }
}
