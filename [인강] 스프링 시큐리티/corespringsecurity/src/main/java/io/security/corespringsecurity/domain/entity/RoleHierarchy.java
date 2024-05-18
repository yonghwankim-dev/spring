package io.security.corespringsecurity.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLE_HIERARCHY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RoleHierarchy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "child_name", unique = true)
	private String childName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_name", referencedColumnName = "child_name")
	private RoleHierarchy parentName;

	@OneToMany(mappedBy = "parentName", cascade = CascadeType.ALL)
	private Set<RoleHierarchy> roleHierarchy = new HashSet<>();

	public static RoleHierarchy createRoleHierarchy(String childName) {
		return new RoleHierarchy(null, childName, null, new HashSet<>());
	}
}
