package likelion.univ.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostDeleteServiceDto {
    private Long postId;
    private Long loginUserId;
}