package com.brq.projeto1.repositories;

import com.brq.projeto1.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repositório da Classe Categoria
 * @author WGomes
 * @since release 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
