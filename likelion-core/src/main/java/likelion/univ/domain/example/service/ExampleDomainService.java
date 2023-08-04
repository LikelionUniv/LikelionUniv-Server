package likelion.univ.domain.example.service;

import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleDomainService {
    private final ExampleAdater exampleAdapter;

    @Transactional
    public Example createExample(String body){
        Example example = Example.builder()
                .body(body)
                .build();
        exampleRepository.save(example);
        return example;
    }
    @Transactional
    public void updateExample(Long id, String body){
        Example example = exampleRepository.findById(id).get();
        example.updateBody(body);
    }
}
