package likelion.univ.domain.repository;

import likelion.univ.domain.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example,Long> {
}