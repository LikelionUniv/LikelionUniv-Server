package likelion.univ.adminUser.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteUserUsecase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;

    public void execute(Long userId) {
        User user = userAdaptor.findById(userId);
        userDomainService.deleteUser(user);
    }
}