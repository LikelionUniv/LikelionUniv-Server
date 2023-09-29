package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.post.dto.PostRequestDTO;
import likelion.univ.domain.post.dto.PostServiceDTO;
import likelion.univ.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@UseCase
@RequiredArgsConstructor
public class PostEditUseCase {

    @Autowired
    private PostService postService;

    public PostServiceDTO.ResponseDTO execute(PostRequestDTO.Edit request) {
        PostServiceDTO.ResponseDTO response = postService.editPost(buildDTO(request));
        return response;
    }

    PostServiceDTO.EditRequest buildDTO(PostRequestDTO.Edit request) {
        return PostServiceDTO.EditRequest.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .body(request.getBody())
                .build();
    }
}
