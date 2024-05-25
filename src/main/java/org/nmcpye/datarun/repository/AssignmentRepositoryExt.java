package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepositoryExt extends AssignmentRepository {
    @Query(
        value = "SELECT a FROM Assignment a WHERE a.team.userInfo.login = :login and a.activity.active = true",
        countQuery = "select count(assignment) from Assignment assignment"
    )
    Page<Assignment> findByCurrentUser(@Param("login") String login,
                                       Pageable pageable);
}
