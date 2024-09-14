package com.sergiodev.catbreeds

import com.sergiodev.catbreeds.catList.interactor.dto.BreedsDTO
import com.sergiodev.catbreeds.catList.interactor.toBreedsDTO
import com.sergiodev.catbreeds.catList.interactor.toCatEntity
import com.sergiodev.catbreeds.catList.repository.CatListRepository
import com.sergiodev.catbreeds.core.database.dao.CatDAO
import com.sergiodev.catbreeds.core.database.entity.CatEntity
import com.sergiodev.catbreeds.core.network.ApiClient
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CatListRepositoryTest {

    @Mock
    private lateinit var apiClient: ApiClient

    @Mock
    private lateinit var catDAO: CatDAO

    private lateinit var catListRepository: CatListRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        catListRepository = CatListRepository(apiClient, catDAO)
    }


    @Test
    fun `when getCats is called then it should return list from API`() = runTest {
        // when
        val mockCats = listOf(
            BreedsDTO().apply {
                id = "1"
            }, BreedsDTO().apply {
                id = "2"
            })
        whenever(apiClient.getCats()).thenReturn(mockCats)

        // Then
        val result = catListRepository.getCats()

        // Assert
        assertEquals(mockCats, result)
        verify(apiClient, times(1)).getCats()
    }

    @Test
    fun `when saveCats is called then it should save list into DAO`() = runTest {

        // when
        val mockCats = listOf(BreedsDTO().apply {
            id = "1"
        }, BreedsDTO().apply {
            id = "2"
        })

        val mockCatEntities = mockCats.map { it.toCatEntity() }

        //Then
        catListRepository.saveCats(mockCats)

        // Assert
        verify(catDAO, times(1)).insertAll(mockCatEntities)
    }

    @Test
    fun `when getLocalCats is called then it should return list from DAO`() = runTest {

        // When
        val mockCatEntities = listOf(
            CatEntity(
                id = "1",
                name = "Persian",
                url = "https://example.com/persian.jpg",
                origin = "Iran",
                intelligence = 5,
                description = "The Persian cat is a long-haired breed with a calm personality.",
                lifeSpan = "10-15 years",
                altNames = "Persian Longhair",
                urlWiki = "https://en.wikipedia.org/wiki/Persian_cat",
                urlVetstreet = "https://www.vetstreet.com/cats/persian"
            ), CatEntity(
                id = "2",
                name = "Siamese",
                url = "https://example.com/siamese.jpg",
                origin = "Thailand",
                intelligence = 8,
                description = "The Siamese cat is one of the first distinctly recognized breeds of Asian cat.",
                lifeSpan = "12-20 years",
                altNames = "Thai Cat",
                urlWiki = "https://en.wikipedia.org/wiki/Siamese_cat",
                urlVetstreet = "https://www.vetstreet.com/cats/siamese"
            )
        )

        val mockBreedsDTOs = mockCatEntities.map { it.toBreedsDTO() }
        whenever(catDAO.getLocalCats()).thenReturn(mockCatEntities)

        // Then
        val result = catListRepository.getLocalCats()

        // Assert
        assertEquals(mockBreedsDTOs.count(), result.count())
        verify(catDAO, times(1)).getLocalCats()
    }

}