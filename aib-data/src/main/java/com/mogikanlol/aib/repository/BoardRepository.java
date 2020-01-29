package com.mogikanlol.aib.repository;

import com.mogikanlol.aib.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, String> {
}
