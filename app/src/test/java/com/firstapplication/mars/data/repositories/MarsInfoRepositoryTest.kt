package com.firstapplication.mars.data.repositories

import com.firstapplication.mars.data.model.MarsProperty
import com.firstapplication.mars.data.remote.MarsApiServiceTest
import com.firstapplication.mars.data.remote.ReadingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MarsInfoRepositoryTest {

    @Test
    fun correctMapping_AfterReadingDataFromServer_ReturnListOfOnlyOneMarsModelObject() = runTest {
        val marsApiService = MarsApiServiceTest(
            listOf(
                MarsProperty("1", "url1")
            )
        )
        val repository = MarsInfoRepositoryImpl(Dispatchers.IO, marsApiService)

        val data = repository.getMarsProperties()

        assertEquals(1, data.size)
        assertEquals("1", data[0].id)
        assertEquals("url1", data[0].imageSrcUrl)
    }

    @Test(expected = ReadingException::class)
    fun throwReadingException_AfterReadingDataFromServer_ThrowsException(): Unit = runTest {
        val marsApiService = MarsApiServiceTest(throwException = true)
        val repository = MarsInfoRepositoryImpl(Dispatchers.IO, marsApiService)

        repository.getMarsProperties()
    }

    @Test
    fun getCorrectModels_AfterReadingDataFromServer_ReturnListOfThreeMarsModelObjects() = runTest {
        val marsApiService = MarsApiServiceTest(
            listOf(
                MarsProperty("1", "url1"),
                MarsProperty("2", "url2"),
                MarsProperty("3", "url3")
            )
        )
        val repository = MarsInfoRepositoryImpl(Dispatchers.IO, marsApiService)

        val data = repository.getMarsProperties()

        assertEquals(3, data.size)
        assertEquals("1", data[0].id)
        assertEquals("2", data[1].id)
        assertEquals("3", data[2].id)
    }

    @Test
    fun getEmptyList_AfterReadingDataFromServer_ReturnEmptyList() = runTest {
        val marsApiService = MarsApiServiceTest()
        val repository = MarsInfoRepositoryImpl(Dispatchers.IO, marsApiService)

        val data = repository.getMarsProperties()

        assertEquals(0, data.size)
    }
}