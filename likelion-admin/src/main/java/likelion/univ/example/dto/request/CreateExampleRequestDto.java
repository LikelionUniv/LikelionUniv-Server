package likelion.univ.example.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateExampleRequestDto {

    @NotBlank
    @ApiModelProperty(value = "댓글 내용", example = "안녕하세요. 멋쟁이사자처럼입니다.", required = true)
    private String body;
}
