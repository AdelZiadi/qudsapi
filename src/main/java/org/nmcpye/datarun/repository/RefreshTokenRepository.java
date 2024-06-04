package org.nmcpye.datarun.repository;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.RefreshToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RefreshToken entity.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("select refreshToken from RefreshToken refreshToken where refreshToken.user.login = ?#{authentication.name}")
    List<RefreshToken> findByUserIsCurrentUser();

    default Optional<RefreshToken> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RefreshToken> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RefreshToken> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select refreshToken from RefreshToken refreshToken left join fetch refreshToken.user",
        countQuery = "select count(refreshToken) from RefreshToken refreshToken"
    )
    Page<RefreshToken> findAllWithToOneRelationships(Pageable pageable);

    @Query("select refreshToken from RefreshToken refreshToken left join fetch refreshToken.user")
    List<RefreshToken> findAllWithToOneRelationships();

    @Query("select refreshToken from RefreshToken refreshToken left join fetch refreshToken.user where refreshToken.id =:id")
    Optional<RefreshToken> findOneWithToOneRelationships(@Param("id") Long id);
}
