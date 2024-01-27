package ignis.ignis.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Service implements ImageUtil {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;
    private final AmazonS3 amazonS3;

    @Override
    public String uploadImage(MultipartFile image) throws Exception {
        if (image.isEmpty() || image.getOriginalFilename() == null) {
            throw new Exception("sdf");
        }

        String fileName = UUID.randomUUID() + image.getOriginalFilename();

        try {
            PutObjectRequest request = new PutObjectRequest(
                    bucketName, fileName, image.getInputStream(), getObjectMetadata(image)
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return getFileUrl(fileName);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());

        return objectMetadata;
    }

    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
