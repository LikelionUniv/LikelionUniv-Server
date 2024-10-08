package likelion.univ.alarm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendEmailDto {

    @Schema(description = "받는 사람 (Email List)")
    private List<String> receivers;

    @Schema(description = "메일 제목")
    private String subject;

    @Schema(description = "본문 형식 [html / plain-text]")
    private String contentsType;

    @Schema(description = "메일 본문")
    private String contents;
}
