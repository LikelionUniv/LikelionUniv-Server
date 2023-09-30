package likelion.univ.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String body;
    private String thumbnail;
    private String mainCategory;
    private String subCategory;

}
