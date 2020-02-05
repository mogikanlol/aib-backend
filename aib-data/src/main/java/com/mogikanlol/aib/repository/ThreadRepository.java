package com.mogikanlol.aib.repository;

import com.mogikanlol.aib.domain.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
