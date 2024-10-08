package likelion.univ.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.follow.service.ClientFollowService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/follow")
@Tag(name = "팔로우", description = "팔로우 관련 API입니다.")
public class FollowController {

    private final ClientFollowService clientFollowService;

    @Operation(summary = "팔로우 ", description = "해당 유저를 팔로우 합니다.")
    @PostMapping("/{userId}")
    public SuccessResponse follow(
            @PathVariable("userId") Long userId
    ) {
        clientFollowService.follow(userId);
        return SuccessResponse.empty();
    }

    @Operation(summary = "팔로우 취소", description = "해당 유저를 팔로우 취소 합니다.")
    @DeleteMapping("/{userId}")
    public SuccessResponse deleteFollow(
            @PathVariable("userId") Long userId
    ) {
        clientFollowService.cancelFollow(userId);
        return SuccessResponse.empty();
    }
}

