package likelion.univ.domain.recruit.exception;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitErrorCode implements BaseErrorCode {

    EMAIL_ALREADY_REGISTERED(BAD_REQUEST, "ALARM_400", "이번 기수에 이미 등록된 이메일입니다."),
    RECRUIT_NOT_FOUND(BAD_REQUEST, "ALARM_400_1", "등록된 리크루팅이 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
