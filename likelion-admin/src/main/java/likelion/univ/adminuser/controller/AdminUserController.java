package likelion.univ.adminuser.controller;

import static org.springframework.data.domain.Sort.Direction.DESC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.adminuser.dto.request.DeletedUserListRequestDto;
import likelion.univ.adminuser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.adminuser.service.AdminUserService;
import likelion.univ.common.response.PageResponse;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminuser.controller.RepresentativeController")
@RequestMapping("/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "UserByUnivAdmin", description = "학교 대표에 의한 사용자 관련 API")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Operation(summary = "우리 학교 동아리 멤버 전체 조회")
    @GetMapping("/univ/users")
    public SuccessResponse<PageResponse<UserInfoResponseDto>> findUsersByUnivOfUser(
            @ParameterObject
            @PageableDefault(size = 10, page = 0, sort = "createdDate", direction = DESC)
            Pageable pageable
    ) {
        PageResponse<UserInfoResponseDto> response = adminUserService.findAllByAdminUserUniv(pageable);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "우리 학교 동아리 특정 멤버 수정")
    @PatchMapping("/users/{userId}")
    public SuccessResponse<UserInfoResponseDto> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserRequestDto updateUserRequestDto
    ) {
        UserInfoResponseDto response = adminUserService.updateUser(userId, updateUserRequestDto);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "우리 학교 동아리 특정 멤버 삭제")
    @DeleteMapping("/users/{userId}")
    public SuccessResponse deleteUser(
            @PathVariable("userId") Long userId
    ) {
        adminUserService.delete(userId);
        return SuccessResponse.empty();
    }

    @Operation(summary = "우리 학교 동아리 멤버(리스트) 삭제")
    @DeleteMapping("/users")
    public SuccessResponse deleteUserList(
            @Valid @RequestBody DeletedUserListRequestDto dto
    ) {
        adminUserService.deleteAllUser(dto.getIds());
        return SuccessResponse.empty();
    }
}
