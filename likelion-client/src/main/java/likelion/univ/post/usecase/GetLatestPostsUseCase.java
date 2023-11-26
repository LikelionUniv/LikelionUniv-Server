package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetLatestPostsServiceDto;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetLatestPostsUseCase {
    private final PostDomainService postDomainService;

    public List<PostDetailResponseDto> execute(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        GetLatestPostsServiceDto requestDto = new GetLatestPostsServiceDto(mainCategory, subCategory, pageable);
        return postDomainService.getLatestPosts(requestDto);
    }
}
