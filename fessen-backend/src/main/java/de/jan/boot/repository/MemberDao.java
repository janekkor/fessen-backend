package de.jan.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Family;
import de.jan.boot.model.Member;

public interface MemberDao extends JpaRepository<Member, Long> {

	List<Member> findAllByFamily(Family family);
	
}
