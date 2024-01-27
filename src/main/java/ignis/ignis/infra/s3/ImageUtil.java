package ignis.ignis.infra.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageUtil {

    List<String> uploadImages(List<MultipartFile> images) throws IOException;
}
