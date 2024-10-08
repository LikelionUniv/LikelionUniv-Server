package likelion.univ.alarm.service;

import likelion.univ.alarm.dto.request.AlarmRegisterRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.exception.EmailAlreadyRegisteredAsAlarmException;
import likelion.univ.domain.alarm.repository.AlarmRepository;
import likelion.univ.domain.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ClientAlarmService {

    private final AlarmRepository alarmRepository;
    private final AlarmService alarmService;

    public void registerAlarm(Long ordinal, AlarmRegisterRequestDto alarmRegisterRequestDto) {
        if (alarmRepository.existsByOrdinalAndEmail(ordinal, alarmRegisterRequestDto.getEmail())) {
            throw new EmailAlreadyRegisteredAsAlarmException();
        }
        Alarm newAlarm = AlarmRegisterRequestDto.toEntity(ordinal, alarmRegisterRequestDto);
        alarmService.createAlarm(newAlarm);
    }
}
