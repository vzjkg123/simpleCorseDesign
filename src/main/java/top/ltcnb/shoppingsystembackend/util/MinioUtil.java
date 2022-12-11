package top.ltcnb.shoppingsystembackend.util;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Objects;
import java.util.UUID;

/**
 * @description： minio工具类
 */
@Component
public class MinioUtil {

    @Value("${minio.endpoint}")
    String endpoint;


    @Value("${minio.bucketName}")
    private String bucketName;


    private final MinioClient minioClient;

    @Autowired
    public MinioUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    public GetObjectResponse download(String bucketName, String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(name).build());
    }

    /**
     * @param file 文件输入流
     * @return 返回访问地址
     */
    public String upload(MultipartFile file) {
        try {
            String originName = Objects.requireNonNull(file.getOriginalFilename());
            int start = originName.lastIndexOf(".");
            String suffix = originName.substring(start);
            String uuidName = UUID.randomUUID() +suffix;
            InputStream in = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uuidName)
                    .stream(in, in.available(), -1)
                    .build());
            return endpoint + "/" + bucketName + "/" + uuidName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * description: 上传文件
     *
     * @param multipartFile
     * @return: java.lang.String
     */
    public String uploadMultipartFile(MultipartFile multipartFile, String bucketName) throws Exception {
        String originName = multipartFile.getOriginalFilename();
        assert originName != null;
        String fileName = UUID.randomUUID() + "." + originName.split("\\.")[1];
        InputStream in = multipartFile.getInputStream();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(in, in.available(), -1)
                .contentType(multipartFile.getContentType())
                .build());


        return fileName;
    }


}


