package likelion.univ.domain.recruit.service;

import java.util.List;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitQueryService {

    private final RecruitRepository recruitRepository;

    public List<Recruit> queryAllByUniversityNameAndGeneration(String universityName, Integer generation) {
        return recruitRepository.findAllByGenerationAndUniversityName(generation, universityName);
    }
}
