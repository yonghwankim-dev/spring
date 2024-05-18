package io.security.corespringsecurity.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RESOURCES")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = "resourcesRoleSet")
public class Resources implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "resource_id")
	private Long id;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "http_method")
	private String httpMethod;

	@Column(name = "order_num")
	private int orderNum;

	@Column(name = "resource_type")
	private String resourceType;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<ResourcesRole> resourcesRoleSet = new HashSet<>();

	public static Resources createResources(String resourceName, String httpMethod, int orderNum, String resourceType) {
		Resources resources = new Resources();
		resources.resourceName = resourceName;
		resources.httpMethod = httpMethod;
		resources.orderNum = orderNum;
		resources.resourceType = resourceType;
		resources.resourcesRoleSet = new HashSet<>();
		return resources;
	}

	public void setResourcesRoleSet(Set<ResourcesRole> resourcesRoleSet) {
		this.resourcesRoleSet = resourcesRoleSet;
	}

	public List<String> getRoleNames() {
		return resourcesRoleSet.stream().map(ResourcesRole::getRoleName).toList();
	}
}
