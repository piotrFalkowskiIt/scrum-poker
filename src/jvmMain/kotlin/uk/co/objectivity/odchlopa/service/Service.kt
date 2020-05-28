package uk.co.objectivity.odchlopa.service

import org.springframework.stereotype.Component
import uk.co.objectivity.odchlopa.entities.Product
import uk.co.objectivity.odchlopa.repositories.ProductRepository

@Component
class Service (var productRepository: ProductRepository){

    fun getProduct(): Product? {
        return productRepository.findAll().firstOrNull()
    }
}