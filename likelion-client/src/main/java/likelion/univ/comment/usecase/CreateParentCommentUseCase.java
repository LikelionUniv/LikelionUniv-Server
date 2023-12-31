package likelion.univ.comment.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.comment.dto.request.CommentCreateParentRequestDto;
import likelion.univ.domain.comment.dto.response.SimpleCommentData;
import likelion.univ.domain.comment.dto.request.CreateParentCommentCommand;
import likelion.univ.domain.comment.service.CommentDomainService;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.post.processor.UpdatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateParentCommentUseCase {
    private final AuthenticatedUserUtils userUtils;
    private final CommentDomainService commentDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final UpdatePostCountInfoProcessor updatePostCountInfoProcessor;

    public SimpleCommentData execute(Long postId, CommentCreateParentRequestDto createRequestDto) {
        SimpleCommentData parentComment = commentDomainService.createParentComment(serviceDtoBy(postId, createRequestDto));

        // redis update
        PostCountInfo countInfo = getOrCreatePostCountInfoProcessor.execute(postId);
        Long commentCount = countInfo.getCommentCount();
        Long likeCount = countInfo.getLikeCount();
        updatePostCountInfoProcessor.execute(postId, ++commentCount, likeCount);

        return parentComment;
    }

    private CreateParentCommentCommand serviceDtoBy(Long postId, CommentCreateParentRequestDto createParentRequest) {
        return CreateParentCommentCommand.builder()
                .postId(postId)
                .loginUserId(userUtils.getCurrentUserId())
                .body(createParentRequest.getBody())
                .build();
    }
}
