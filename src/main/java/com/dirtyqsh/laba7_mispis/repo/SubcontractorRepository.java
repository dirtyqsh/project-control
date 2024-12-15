package com.dirtyqsh.laba7_mispis.repo;

import com.dirtyqsh.laba7_mispis.DTO.SubcontractorDTO;
import com.dirtyqsh.laba7_mispis.model.Subcontractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcontractorRepository extends JpaRepository<Subcontractor, Integer> {
    @Query(value = """
        SELECT 
            s.id AS subcontractorId,
            s.name AS subcontractorName,
            s.country AS subcontractorCountry,
            s.address AS subcontractorAddress,
            s.phone AS subcontractorPhone
        FROM Subcontractor s
    """, nativeQuery = true)
    List<SubcontractorDTO> findAllSubcontractors();
}
