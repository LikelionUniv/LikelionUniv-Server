package likelion.univ.likepost.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.likepost.dto.LikePostRequestDto;
import likelion.univ.domain.likepost.dto.LikePostDto;
import likelion.univ.domain.likepost.service.LikePostDomainService;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@RequiredArgsConstructor
public class CreateLikePostUseCase {
    @Autowired
    private LikePostDomainService likePostDomainService;

    @Autowired
    private UserAdaptor userAdaptor;

    @Autowired
    private PostAdaptor postAdaptor;

    public LikePostDto.ResponseDTO execute(LikePostRequestDto.Save request) {
        //user

        LikePostDto.ResponseDTO response = likePostDomainService.createLikePost(buildDTO(request));
        return response;
    }

    private LikePostDto.CreateRequest buildDTO(LikePostRequestDto.Save request) {
        return LikePostDto.CreateRequest.builder()
                .post(postAdaptor.findById(request.getPostId()))
                 .author(userAdaptor.findById(request.getUserId()))
                .build();
    }


}