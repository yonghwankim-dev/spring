package io.security.corespringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.security.corespringsecurity.domain.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

	@Query("select r from RoleHierarchy r where r.childName = :childName")
	RoleHierarchy findByChildName(@Param("childName") String childName);
}
