package likelion.univ.hackathon.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import likelion.univ.domain.hackathon.service.command.HackathonApplyCommand;

public record HackathonApplyRequest(
        @NotBlank
        @Schema(description = "신청자 이름", example = "김멋사")
        String name,

        @NotBlank
        @Schema(description = "신청자 이메일", example = "likelion@likelion.com")
        String email,

        @NotNull
        @Schema(description = "신청자 학교 ID", example = "2")
        Long universityId,

        @NotBlank
        @Schema(description = "신청자 전공", example = "멋쟁이과")
        String major,

        @NotBlank
        @Schema(description = "신청자 전화번호 (-생략, 숫자만)", example = "01012341234")
        String phone,

        @NotEmpty
        @Schema(description = "해커톤 파트들", implementation = HackathonPart.class)
        Set<HackathonPart> hackathonParts,

        @NotBlank
        @Size(max = 10, message = "팀명은 10자 이하여야 합니다.")
        @Schema(description = "팀명", example = "멋쟁이팀")
        String teamName,

        @Schema(description = "오프라인 참가 여부", example = "true")
        boolean offlineParticipation,

        @Size(max = 100, message = "불참사유는 100자 이내여야 합니다.")
        @Schema(description = "불참 사유", example = "개인 사정으로 인해 불참합니다.")
        String reasonForNotOffline
) {
    public HackathonApplyCommand toCommand(Long userId) {
        return new HackathonApplyCommand(
                userId,
                name,
                email,
                universityId,
                major,
                phone,
                hackathonParts,
                teamName,
                offlineParticipation,
                reasonForNotOffline
        );
    }
}
