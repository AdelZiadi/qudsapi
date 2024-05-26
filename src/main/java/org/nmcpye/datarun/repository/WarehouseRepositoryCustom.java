package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.User;
import org.nmcpye.datarun.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Warehouse entity.
 */
@Repository
public interface WarehouseRepositoryCustom extends WarehouseRepository {
    @Query("SELECT w FROM Warehouse w WHERE " +
        "EXISTS (SELECT t FROM Team t WHERE t.userInfo.login = :login AND t.warehouse = w)")
    Page<Warehouse> findByCurrentUser(@Param("login") String login, Pageable pageable);
}
