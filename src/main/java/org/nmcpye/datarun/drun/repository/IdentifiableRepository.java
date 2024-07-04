package org.nmcpye.datarun.drun.repository;

import org.nmcpye.datarun.domain.common.IdentifiableObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IdentifiableRepository<T extends IdentifiableObject, ID> extends JpaRepository<T, ID> {
    Optional<T> findByUid(String uid);

    Page<T> findAllByUser(Pageable pageable);

    //
    Page<T> findAllWithEagerRelationshipsByUser(Pageable pageable);

    //
    Optional<T> findOneWithEagerRelationshipsByUser(ID id);
}
