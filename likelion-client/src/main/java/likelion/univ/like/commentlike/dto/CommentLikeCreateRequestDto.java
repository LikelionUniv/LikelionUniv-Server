package likelion.univ.like.commentlike.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentLikeCreateRequestDto {
    private Long commentId;
}