package top.ltcnb.shoppingsystembackend.config;


import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class MinioConfig {

    @Value("${minio.endpoint}")
    String endpoint;
    @Value("${minio.accessKey}")
    String minioAccount;
    @Value("${minio.accessKey}")
    String minioPassword;

    @Bean
    public MinioClient Client() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(minioAccount,minioPassword)
                .build();
    }
}
