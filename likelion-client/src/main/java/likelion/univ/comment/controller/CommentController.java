package likelion.univ.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.comment.dto.CommentCreateChildRequestDto;
import likelion.univ.comment.dto.CommentCreateParentRequestDto;
import likelion.univ.comment.dto.CommentFindRequestDto;
import likelion.univ.comment.dto.CommentUpdateRequestDto;
import likelion.univ.comment.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/comments")
@Tag(name = "댓글", description = "댓글 API")
public class CommentController {
    private final CreateParentCommentUseCase createParentCommentUseCase;
    private final CreateChildCommentUseCase createChildCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final SoftDeleteCommentUseCase softDeleteCommentUseCase;
    private final HardDeleteCommentUseCase hardDeleteCommentUseCase;
    private final GetAllCommentUseCase getAllCommentUseCase;

    @Operation(summary = "댓글 작성", description = "부모 댓글을 생성합니다.")
    @PostMapping("/new/parent")
    public SuccessResponse<?> createParentComment(@RequestBody CommentCreateParentRequestDto request) {
        return createParentCommentUseCase.execute(request);
    }

    @Operation(summary = "대댓글 작성", description = "자식 댓글을 생성합니다.")
    @PostMapping("/new/child")
    public SuccessResponse<?> createChildComment(@RequestBody CommentCreateChildRequestDto request) {
        return createChildCommentUseCase.execute(request);
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글 body필드를 수정합니다.")
    @PatchMapping("/update/{commentId}")
    public SuccessResponse<?> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto request) {
        return updateCommentUseCase.execute(commentId, request);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 soft delete 합니다.")
    @PatchMapping("/delete/soft/{commentId}")
    public SuccessResponse<?> deleteCommentSoft(@PathVariable Long commentId) {
        return softDeleteCommentUseCase.execute(commentId);// soft delete
    }

    @Operation(summary = "댓글 완전 삭제", description = "댓글을 hard delete 합니다.")
    @DeleteMapping("/delete/hard/{commentId}")
    public SuccessResponse<?> deleteCommentHard(@PathVariable Long commentId) {
        return hardDeleteCommentUseCase.execute(commentId);
    }

    @Operation(summary = "댓글 조회", description = "게시글에 대한 댓글을 전부 조회합니다.")
    @GetMapping("")
    public SuccessResponse<?> getComments(@RequestBody CommentFindRequestDto request) {
        return getAllCommentUseCase.execute(request);
    }
}