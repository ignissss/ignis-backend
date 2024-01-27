package ignis.ignis.infra.s3;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUtil {

    String uploadImage(List<MultipartFile> image) throws Exception;
}
