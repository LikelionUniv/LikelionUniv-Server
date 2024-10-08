package likelion.univ.domain.recruit.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.university.entity.University;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private Integer generation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Builder
    public Recruit(String name, String email, String phoneNumber, int generation, University university) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.university = university;
        this.generation = generation;
    }
}
