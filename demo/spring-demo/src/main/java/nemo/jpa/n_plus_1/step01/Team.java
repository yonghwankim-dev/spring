package nemo.jpa.n_plus_1.step01;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
	@Builder.Default
	private List<Member> members = new ArrayList<>();

	public void addMember(Member member){
		members.add(member);
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d, members=%d명]", name, id, members.size());
	}
}
