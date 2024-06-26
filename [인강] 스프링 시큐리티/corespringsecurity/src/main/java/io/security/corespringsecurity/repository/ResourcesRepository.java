package io.security.corespringsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.security.corespringsecurity.domain.entity.Resources;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
	@Query("select r from Resources r join fetch r.resourcesRoleSet resourcesRole join fetch resourcesRole.role role where r.resourceType = 'url' order by r.orderNum desc")
	List<Resources> findAllUrlResources();

	@Query("select r from Resources r join fetch r.resourcesRoleSet resourcesRole join fetch resourcesRole.role role where r.resourceType = 'method' order by r.orderNum desc")
	List<Resources> findAllMethodResources();

	@Query("select r from Resources r join fetch r.resourcesRoleSet resourcesRole join fetch resourcesRole.role role where r.resourceType = 'pointcut' order by r.orderNum desc")
	List<Resources> findAllPointcutResources();

	@Query("select r from Resources r join fetch r.resourcesRoleSet resourcesRole join fetch resourcesRole.role role where r.resourceType = 'url' and r.id = :id order by r.orderNum desc")
	Optional<Resources> findResourcesById(@Param("id") Long id);

	@Query("select r from Resources r where r.resourceName = :resourceName and r.httpMethod = :httpMethod")
	Resources findByResourceNameAndHttpMethod(@Param("resourceName") String resourceName,
		@Param("httpMethod") String httpMethod);
}
