package likelion.univ.example.service;

import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.repository.ExampleRepository;
import likelion.univ.domain.example.service.ExampleService;
import likelion.univ.example.dto.request.CreateExampleRequestDto;
import likelion.univ.example.dto.response.ExampleInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExampleAdminService {

    private final ExampleService exampleService;
    private final ExampleRepository exampleRepository;

    public void create(CreateExampleRequestDto createExampleRequestDto) {
        exampleService.createExample(createExampleRequestDto.body());
    }

    public void delete(Long id) {
        Example example = exampleRepository.getById(id);
        exampleRepository.delete(example);
    }

    public void edit(Long id, CreateExampleRequestDto createExampleRequestDto) {
        exampleService.editExample(id, createExampleRequestDto.body());
    }

    public ExampleInfoResponseDto get(Long id) {
        Example example = exampleRepository.getById(id);
        return ExampleInfoResponseDto.of(example);
    }
}
