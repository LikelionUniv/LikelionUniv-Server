package likelion.univ.domain.like.postlike.repository;

import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<PostLike> findByPostAndUser(Post post, User user);
    Long countByPostId(Long postId);
    Boolean existsByPostIdAndUserId(Long postId, Long userId);
}
