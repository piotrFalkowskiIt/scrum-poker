package uk.co.objectivity.scrum.poker.service

import org.springframework.stereotype.Component
import uk.co.objectivity.scrum.poker.entities.Product
import uk.co.objectivity.scrum.poker.repositories.ProductRepository

@Component
class Service (var productRepository: ProductRepository){

    fun getProduct(): Product? {
        return productRepository.findAll().firstOrNull()
    }
}
