package likelion.univ.hackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import likelion.univ.domain.hackathon.response.HackathonFindResponse;
import likelion.univ.domain.hackathon.response.HackathonFormFindResponse;
import likelion.univ.domain.hackathon.service.HackathonService;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import likelion.univ.hackathon.email.HackathonApplyEmailContent;
import likelion.univ.hackathon.request.HackathonApplyRequest;
import likelion.univ.hackathon.request.HackathonModifyRequest;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hackathons")
@Tag(name = "해커톤", description = "해커톤 API")
public class HackathonController {

    private final AuthenticatedUserUtils userUtils;
    private final HackathonService hackathonService;
    private final EmailSender emailSender;

    @Operation(summary = "해커톤 신청")
    @PostMapping
    public SuccessResponse<Long> apply(
            @Valid @RequestBody HackathonApplyRequest request
    ) {
        Long userId = userUtils.getCurrentUserId();
        Long projectId = hackathonService.apply(request.toCommand(userId));
        EmailContent emailContent = EmailContent.builder()
                .subject("[멋쟁이사자처럼] 12기 중앙 해커톤 신청이 완료되었습니다.")
                .contents(HackathonApplyEmailContent.VALUE)
                .receivers(List.of(request.email()))
//                .attachments(List.of(FileUploadUtil.convertPngToMultipartFile(IMAGE_PATH)))
                .build();

        emailSender.send(emailContent);
        return SuccessResponse.of(projectId);
    }

    @Operation(summary = "해커톤 신청 내역 조회")
    @GetMapping
    public SuccessResponse<List<HackathonFindResponse>> findMyHackathonForms() {
        Long userId = userUtils.getCurrentUserId();
        List<HackathonFindResponse> response = hackathonService.findMyHackathonForms(userId);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "해커톤 신청 상세 조회")
    @GetMapping("/{hackathonFormId}")
    public SuccessResponse<HackathonFormFindResponse> find(
            @PathVariable("hackathonFormId") Long hackathonFormId
    ) {
        Long userId = userUtils.getCurrentUserId();
        HackathonFormFindResponse response = hackathonService.find(userId, hackathonFormId);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "해커톤 신청 수정")
    @PatchMapping("/{hackathonFormId}")
    public SuccessResponse<Long> modify(
            @Valid @RequestBody HackathonModifyRequest request,
            @PathVariable("hackathonFormId") Long hackathonFormId
    ) {
        Long userId = userUtils.getCurrentUserId();
        hackathonService.modify(request.toCommand(userId, hackathonFormId));
        return SuccessResponse.of(null);
    }

}
