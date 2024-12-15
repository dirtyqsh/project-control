package com.dirtyqsh.laba7_mispis.repo;

import com.dirtyqsh.laba7_mispis.model.WorkSubcontractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSubcontractorRepository extends JpaRepository<WorkSubcontractor, Integer> {
    List<WorkSubcontractor> findBySubcontractorId(Integer subcontractorId);
}
