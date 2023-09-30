package likelion.univ.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostUpdateServiceDto {
    private Long userId;
    private Long postId;
    private String title;

    private String body;

    private String thumbnail;
}
