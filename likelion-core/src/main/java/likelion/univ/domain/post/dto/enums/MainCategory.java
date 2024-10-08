package likelion.univ.domain.post.dto.enums;

import likelion.univ.domain.post.exception.NoSuchCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainCategory {

    HQ_BOARD("멋쟁이사자처럼"),
    FREE_BOARD("자유게시판"),
    OVERFLOW("트랙별 소통 채널");

    private final String title;

    public static MainCategory findByTitle(String title) {
        if (title.equals(HQ_BOARD.title)) {
            return HQ_BOARD;
        } else if (title.equals(FREE_BOARD.title)) {
            return FREE_BOARD;
        } else if (title.equals(OVERFLOW.title)) {
            return OVERFLOW;
        }
        throw new NoSuchCategoryException();
    }

    public static boolean isValid(String title) {
        if (MainCategory.findByTitle(title) == null) {
            return false;
        }
        return true;
    }
}
