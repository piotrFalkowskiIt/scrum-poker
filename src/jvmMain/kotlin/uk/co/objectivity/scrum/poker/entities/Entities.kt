package uk.co.objectivity.scrum.poker.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
        var name: String,
        var price: Number,
        @Id @GeneratedValue var id: Long? = null
)
