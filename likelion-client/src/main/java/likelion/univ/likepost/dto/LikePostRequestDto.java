package likelion.univ.likepost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LikePostRequestDto {

    private Long postId;
}
