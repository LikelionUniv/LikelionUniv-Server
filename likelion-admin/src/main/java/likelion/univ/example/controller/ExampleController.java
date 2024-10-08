package likelion.univ.example.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.example.dto.request.CreateExampleRequestDto;
import likelion.univ.example.service.ExampleAdminService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/example")
@Tag(name = "Example", description = "예시 API")
public class ExampleController {

    private final ExampleAdminService exampleAdminService;

    @Operation(summary = "예시 조회", description = "예시를 조회합니다.")
    @GetMapping("/{exampleId}")
    public SuccessResponse<Object> getExample(@PathVariable Long exampleId) {
        return SuccessResponse.of(exampleAdminService.get(exampleId));
    }

    @Operation(summary = "예시 생성", description = "예시를 생성합니다.")
    @PostMapping
    public SuccessResponse<Object> createExample(
            @Validated @RequestBody CreateExampleRequestDto exampleRequestDto
    ) {
        exampleAdminService.create(exampleRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "예시 수정", description = "예시를 수정합니다.")
    @PatchMapping("/{exampleId}")
    public SuccessResponse<Object> editExample(
            @PathVariable("exampleId") Long exampleId,
            @Validated @RequestBody CreateExampleRequestDto exampleRequestDto
    ) {
        exampleAdminService.edit(exampleId, exampleRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "예시 삭제", description = "예시를 삭제합니다.")
    @DeleteMapping("/{exampleId}")
    public SuccessResponse<Object> deleteExample(@PathVariable Long exampleId) {
        exampleAdminService.delete(exampleId);
        return SuccessResponse.empty();
    }
}
