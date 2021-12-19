package de.jan.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Family;
import de.jan.boot.model.Meal;

public interface MealDao extends JpaRepository<Meal, Long> {

	List<Meal> findAllByFamily(Family family);
}
