package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetUserPostsServiceDto;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCommentedPostsUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public List<PostDetailResponseDto> execute(Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        GetUserPostsServiceDto requestDto = new GetUserPostsServiceDto(currentUserId, pageable);
        return postDomainService.getCommentedPosts(requestDto);
    }
}
