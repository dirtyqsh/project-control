package com.dirtyqsh.laba7_mispis.repo;

import com.dirtyqsh.laba7_mispis.DTO.ProjectDTO;
import com.dirtyqsh.laba7_mispis.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query(value = """
                SELECT 
                    p.id AS projectId,
                    p.name AS projectName,
                    p.cost AS projectCost,
                    p.startdate AS projectStart,
                    p.enddate AS projectEnd,
                    p.status AS projectStatus,
                    GROUP_CONCAT(s.name) AS subcontractors
                FROM project p
                JOIN worksubcontractor ws ON ws.id_project = p.id
                JOIN subcontractor s ON ws.id_subcontractor = s.id
                GROUP BY p.id
            """, nativeQuery = true)
    List<ProjectDTO> findAllWithSubcontractors();


    @Query(value = """
            SELECT
                p.id AS projectId,
                p.name AS projectName,
                p.cost AS projectCost,
                p.startdate AS projectStart,
                p.enddate AS projectEnd,
                p.status AS projectStatus
            FROM project p
            JOIN worksubcontractor ws ON p.id = ws.id_project
            JOIN subcontractor s ON ws.id_subcontractor = s.id
            WHERE s.id = :subcontractorId
            GROUP BY p.id
        """, nativeQuery = true)
    List<ProjectDTO> findProjectsBySubcontractorId(@Param("subcontractorId") int subcontractorId);


}
