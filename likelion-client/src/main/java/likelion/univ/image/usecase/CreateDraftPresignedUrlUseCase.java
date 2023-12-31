package likelion.univ.image.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.image.dto.response.ImageUrlResponseDto;
import likelion.univ.s3.GeneratePresignedUrlProcessor;
import likelion.univ.s3.S3Properties;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CreateDraftPresignedUrlUseCase {
    private final GeneratePresignedUrlProcessor generatePresignedUrlProcessor;
    private final S3Properties s3Properties;

    public ImageUrlResponseDto execute(String prefix, String fileNameExtension) {
        String fileName = createFileName(prefix, fileNameExtension);
        String presignedUrl = generatePresignedUrlProcessor.execute(fileName);
        String imageUrl = s3Properties.getAccessDomain() + fileName;
        return ImageUrlResponseDto.of(presignedUrl, imageUrl, fileName);
    }
    private String createFileName(String prefix, String fileNameExtension){
        String fileName = UUID.randomUUID().toString();
        return "image/" + prefix + "/" + fileName + "." + fileNameExtension;
    }
}