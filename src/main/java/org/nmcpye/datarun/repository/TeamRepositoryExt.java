package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Team entity.
 */
@Repository
public interface TeamRepositoryExt extends TeamRepository {
    @Query(
        value = "SELECT t FROM Team t WHERE t.activity.active = true and t.userInfo.login = :login",
        countQuery = "select count(team) from Team team"
    )
    Page<Team> findByCurrentUser(@Param("login") String login,
                                 Pageable pageable);
}
