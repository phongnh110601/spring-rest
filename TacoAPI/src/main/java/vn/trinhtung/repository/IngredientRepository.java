package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
	
}
