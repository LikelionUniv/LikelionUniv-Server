package likelion.univ.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.alarm.dto.request.AlarmRegisterRequestDto;
import likelion.univ.alarm.service.ClientAlarmService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/alarm")
@Tag(name = "알람", description = "알람 API")
public class AlarmController {

    private final ClientAlarmService clientAlarmService;

    @Operation(summary = "알람 등록", description = "이메일과 알람 타입을 입력받아 해당 기수의 알람을 등록합니다.")
    @PostMapping("/{ordinal}/register")
    public SuccessResponse registerAlarm(
            @PathVariable("ordinal") Long ordinal,
            @RequestBody @Valid AlarmRegisterRequestDto alarmRegisterRequestDto
    ) {
        clientAlarmService.registerAlarm(ordinal, alarmRegisterRequestDto);
        return SuccessResponse.empty();
    }
}
