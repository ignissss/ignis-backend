package ignis.ignis.infra.s3;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {

    String uploadImage(MultipartFile image) throws Exception;
}
