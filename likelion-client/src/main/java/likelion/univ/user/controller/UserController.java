package likelion.univ.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.response.PageResponse;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.response.SuccessResponse;
import likelion.univ.user.dto.request.ProfileEditRequestDto;
import likelion.univ.user.dto.response.FollowUserInfoDto;
import likelion.univ.user.dto.response.ProfileDetailsDto;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.user.dto.response.UserPageProjectsDto;
import likelion.univ.user.dto.response.UserSearchResultDto;
import likelion.univ.user.usecase.EditProfileUsecase;
import likelion.univ.user.usecase.GetFollowInfoUsecase;
import likelion.univ.user.usecase.GetPostsCommentedByMeUsecase;
import likelion.univ.user.usecase.GetProfileUsecase;
import likelion.univ.user.usecase.GetUserLikedPostsUsecase;
import likelion.univ.user.usecase.GetUserPostsUsecase;
import likelion.univ.user.usecase.GetUserProjectsUsecase;
import likelion.univ.user.usecase.SearchUserByNameUsecase;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user")
@Tag(name = "유저페이지", description = "유저페이지관련 API입니다.")
public class UserController {
    
    private final GetProfileUsecase getProfileUsecase;
    private final EditProfileUsecase editProfileUsecase;
    private final GetUserPostsUsecase getUserPostsUsecase;
    private final GetPostsCommentedByMeUsecase getPostsCommentedByMeUsecase;
    private final GetFollowInfoUsecase getFollowInfoUsecase;
    private final SearchUserByNameUsecase searchUserByNameUsecase;
    private final GetUserLikedPostsUsecase getUserLikedPostsUsecase;
    private final GetUserProjectsUsecase getUserProjectsUsecase;

    @Operation(summary = "유저페이지 프로필 조회", description = "해당 유저의 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}/profile")
    public SuccessResponse<Object> getProfile(
            @PathVariable Long userId
    ) {
        ProfileDetailsDto profileDetailsDto = getProfileUsecase.execute(userId);
        return SuccessResponse.of(profileDetailsDto);
    }

    @Operation(summary = "유저페이지 프로필 수정", description = "해당 유저의 프로필 정보를 수정합니다.")
    @PatchMapping("/{userId}/profile")
    public SuccessResponse<Object> editProfile(
            @PathVariable Long userId,
            @RequestBody ProfileEditRequestDto profileEditRequestDto
    ) {
        editProfileUsecase.execute(userId, profileEditRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "팔로잉 목록 조회", description = "해당 유저의 팔로잉 목록을 조회합니다.")
    @GetMapping("/{userId}/following")
    public SuccessResponse<Object> getFollowingList(
            @PathVariable Long userId,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<FollowUserInfoDto> followingUsers = getFollowInfoUsecase.executeForFollowing(userId, pageable);
        return SuccessResponse.of(followingUsers);
    }

    @Operation(summary = "팔로워 목록 조회", description = "해당 유저의 팔로워 목록을 조회합니다.")
    @GetMapping("/{userId}/follower")
    public SuccessResponse<Object> getFollowerList(
            @PathVariable Long userId,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<FollowUserInfoDto> followerUsers = getFollowInfoUsecase.executeForFollower(userId, pageable);
        return SuccessResponse.of(followerUsers);
    }

    @Operation(summary = "해당 유저가 쓴 게시글 조회", description = "해당 유저가 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/posts")
    public SuccessResponse<Object> getPostsWrittenByUser(
            @PathVariable Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPage = getUserPostsUsecase.execute(userId, pageable);
        return SuccessResponse.of(myPagePostsPage);
    }

    @Operation(summary = "해당 유저가 좋아요 누른 게시글 조회", description = "해당 유저가 좋아요를 누른 게시글을 조회합니다. 파라미터로 search를 포함하지 않을 시 전체 조회입니다.")
    @GetMapping("/{userId}/posts/like")
    public SuccessResponse<Object> getPostsLikedByUser(
            @PathVariable Long userId,
            @RequestParam(value = "sort", required = false, defaultValue = "created_date") String sort,
            @RequestParam(value = "search", required = false) String search,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPageLikedByUser = getUserLikedPostsUsecase.execute(userId, pageable,
                sort, search);
        return SuccessResponse.of(myPagePostsPageLikedByUser);
    }


    @Operation(summary = "해당 유저가 댓글 쓴 게시글 조회", description = "해당 유저가 댓글을 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/posts/comment")
    public SuccessResponse<Object> getPostsCommentedByUser(
            @PathVariable Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPageCommentedByUser = getPostsCommentedByMeUsecase.execute(userId,
                pageable);
        return SuccessResponse.of(myPagePostsPageCommentedByUser);
    }

    @Operation(summary = "유저 검색 (Simple Data) (project page)", description = "이름으로 유저를 검색합니다. (프로젝트 페이지 모달)")
    @GetMapping("/search")
    public SuccessResponse<Object> searchUser(
            @RequestParam(required = false) String name,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<UserSearchResultDto> searchedUsers = searchUserByNameUsecase.execute(name, pageable);
        return SuccessResponse.of(searchedUsers);
    }


    @Operation(summary = "내가 참여한 프로젝트 조회", description = "참여한 프로젝트를 조회합니다.")
    @GetMapping("/{userId}/projects")
    public SuccessResponse<Object> getMyProjects(
            @PathVariable Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPageProjectsDto> myPageProjects = getUserProjectsUsecase.execute(userId, pageable);
        return SuccessResponse.of(myPageProjects);
    }
//
//    @Operation(summary = "휴대폰 인증 요청", description = "휴대폰 번호 인증을 위해 서버 내부에 인증번호를 생성합니다.")
//    @PostMapping("/phone/certify")
//    public SuccessResponse<Object> login(@RequestParam("phonenum") String phoneNum){
//        return SuccessResponse.of();
//    }
//
//    @Operation(summary = "휴대폰 인증번호 확인", description = "휴대폰 인증번호 확인 후 인증 토큰을 발급합니다.")
//    @PostMapping("/phone/certify/check")
//    public SuccessResponse<Object> getIdToken(@RequestParam("certifynum") Long certifyNum){
//
//        return SuccessResponse.of();
//    }
}

