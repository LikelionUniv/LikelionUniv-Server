package likelion.univ.post.controller;


import likelion.univ.domain.post.dto.PostDetailResponseDto;
import likelion.univ.domain.post.dto.PostSimpleResponseDto;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.post.usecase.PostCreateUseCase;
import likelion.univ.post.usecase.PostDeleteUseCase;
import likelion.univ.post.usecase.PostUpdateUseCase;
import likelion.univ.post.usecase.PostFindAllUseCase;
import likelion.univ.domain.post.dto.PostServiceDTO;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("v1/community/posts")
public class PostController {

    private final PostCreateUseCase postCreateUseCase;
    private final PostUpdateUseCase postUpdateUsecase;
    private final PostDeleteUseCase postDeleteUseCase;
    private final PostFindAllUseCase postFindAllUseCase;

    @PostMapping("new")
    public SuccessResponse<?> createPost(@RequestBody PostCreateRequestDto request) {
        PostSimpleResponseDto response = postCreateUseCase.execute(request);
        return SuccessResponse.of(response);
    }

    @GetMapping("")
    public SuccessResponse<?> findAll(@RequestParam Integer page, @RequestParam Integer limit) {
        List<PostDetailResponseDto> response = postFindAllUseCase.execute(page,limit);
        return SuccessResponse.of(response);
    }

    @PatchMapping("{postId}")
    public SuccessResponse<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto request) {
        PostSimpleResponseDto response = postUpdateUsecase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @DeleteMapping("{postId}")
    public SuccessResponse<?> deletePost(@PathVariable Long postId) {
        postDeleteUseCase.execute(postId);
        return SuccessResponse.empty();
    }
}
