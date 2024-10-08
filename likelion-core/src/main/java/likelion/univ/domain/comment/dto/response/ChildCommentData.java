package likelion.univ.domain.comment.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import likelion.univ.common.processor.DateCustomFormatter;
import likelion.univ.domain.comment.entity.Comment;
import lombok.Builder;

public record ChildCommentData(
        Long commentId,
        Long parentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Integer likeCount,
        Boolean isLikedByLoginUser,
        String body,
        Boolean isDeleted,
        LocalDateTime createdDate
) {

    @Builder
    @QueryProjection
    public ChildCommentData {
    }

    public static ChildCommentData of(Comment comment, Boolean isLikedByLoginUser) {
        return ChildCommentData.builder()
                .commentId(comment.getId())
                .parentId(comment.getParentComment().getId())
                .userId(comment.getAuthor().getId())
                .userName(comment.getAuthor().getProfile().getName())
                .userProfileImageUrl(comment.getAuthor().getProfile().getProfileImage())
                .likeCount(getLikeCount(comment))
                .isLikedByLoginUser(isLikedByLoginUser)
                .body(comment.getBody())
                .isDeleted(comment.getIsDeleted())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().size());
    }

    public String getFormattedDate() {
        return DateCustomFormatter.format(this.createdDate);
    }
}
