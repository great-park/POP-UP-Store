package park.waiting.domain.posting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park.waiting.domain.posting.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
