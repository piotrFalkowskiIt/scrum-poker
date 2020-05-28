package uk.co.objectivity.odchlopa.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import uk.co.objectivity.odchlopa.repositories.ProductRepository

@RunWith(MockitoJUnitRunner::class)
internal class ServiceTest {

    @Test
    fun getProduct() {
        //given
        val mockRepository = mock(ProductRepository::class.java)
        val service = Service(mockRepository)

        //when
        service.getProduct()

        //then
        verify(mockRepository).findAll()
    }
}