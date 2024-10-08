package likelion.univ.domain.donationhistory.repository.impl.condition;

import static likelion.univ.domain.donationhistory.entity.QDonationHistory.donationHistory;

import com.querydsl.core.types.OrderSpecifier;
import likelion.univ.exception.SortTypeNotMatchedException;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum DonationSortType {

    CREATED_DATE("created_date"),
    VIEW_COUNT("view_count");

    private String value;

    public static DonationSortType fromValue(String value) {
        for (DonationSortType type : DonationSortType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new SortTypeNotMatchedException();
    }

    public static OrderSpecifier toOrderSpecifier(String value) {
        switch (DonationSortType.fromValue(value)) {
            case VIEW_COUNT:
                return donationHistory.viewCount.desc();
            default:
                return donationHistory.createdDate.desc();
        }
    }
}
