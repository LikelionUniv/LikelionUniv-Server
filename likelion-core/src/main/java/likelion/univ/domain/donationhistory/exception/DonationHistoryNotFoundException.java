package likelion.univ.domain.donationhistory.exception;

import likelion.univ.exception.base.BaseException;

public class DonationHistoryNotFoundException extends BaseException {

    public DonationHistoryNotFoundException() {
        super(DonationHistoryErrorCode.DONATION_HISTORY_NOT_FOUND);
    }
}
