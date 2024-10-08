package likelion.univ.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.univ.response.SuccessResponse;
import likelion.univ.university.dto.response.UnivResponseDto;
import likelion.univ.university.dto.response.UniversityDetailResponseDto;
import likelion.univ.university.service.ClientUniversityQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/universities")
@Tag(name = "University", description = "대학교 API")
public class UniversityController {

    private final ClientUniversityQueryService universityQueryService;

    @Operation(summary = "대학교 조회", description = "대학교를 조회합니다.")
    @GetMapping
    public SuccessResponse<List<UnivResponseDto>> getAllUniv() {
        List<UnivResponseDto> univList = universityQueryService.getUniv();
        return SuccessResponse.of(univList);
    }

    @Operation(summary = "대학교 전체 조회", description = "대학교 전체를 조회합니다.")
    @GetMapping("/all")
    public SuccessResponse<List<UniversityDetailResponseDto>> getAllUnivList() {
        List<UniversityDetailResponseDto> result = universityQueryService.getTotalUnivDetails();
        return SuccessResponse.of(result);
    }

    @Operation(summary = "지역별 대학교 조회", description = "대학교를 지역별로 조회합니다.")
    @GetMapping("/{location}")
    public SuccessResponse<List<UniversityDetailResponseDto>> getLocalUnivList(
            @PathVariable("location") String location
    ) {
        List<UniversityDetailResponseDto> result = universityQueryService.getLocationUnivDetails(location);
        return SuccessResponse.of(result);
    }
}
