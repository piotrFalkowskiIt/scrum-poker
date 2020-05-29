package uk.co.objectivity.scrum.poker.repositories

import org.springframework.data.repository.CrudRepository
import uk.co.objectivity.scrum.poker.entities.Product

interface ProductRepository : CrudRepository<Product, Long>
