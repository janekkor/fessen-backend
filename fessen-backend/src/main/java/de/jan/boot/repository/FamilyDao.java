package de.jan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Family;

public interface FamilyDao extends JpaRepository<Family, Long> {
	
	Family findByCode(String familyCode);
}
