package likelion.univ.domain.alarm.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.exception.EmailAlreadyRegisteredAsAlarmException;
import likelion.univ.domain.alarm.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class AlarmAdaptor {
    private final AlarmRepository alarmRepository;

    public Alarm save(Alarm alarm){ return alarmRepository.save(alarm);}

    public void existsByOrdinalAndEmailAndAlarmType(Long ordinal, String email) {
        if (alarmRepository.existsByOrdinalAndEmail(ordinal, email))
            throw new EmailAlreadyRegisteredAsAlarmException();
    }
}
