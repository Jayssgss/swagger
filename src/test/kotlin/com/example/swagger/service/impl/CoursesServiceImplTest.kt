package com.example.swaggerjenkins.service.impl

import com.example.swaggerjenkins.entity.Course
import com.example.swaggerjenkins.repo.CourseRepo
import com.example.swaggerjenkins.request.CourseRequest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*

@org.junit.jupiter.api.extension.ExtendWith(io.mockk.junit5.MockKExtension::class)
class CoursesServiceImplTest {


    @MockK
    private lateinit var courseRepo: CourseRepo

    @InjectMockKs
    private lateinit var coursesService: CoursesServiceImpl

    private val testCourse = Course(1L, "Kotlin", "Kotlin Programming")
    private val testCourseRequest = CourseRequest(1L, "Kotlin", "Kotlin Programming")

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `getAllCourses should return all courses`() {
        // Given
        val expectedCourses = listOf(testCourse, Course(2L, "Java", "Java Programming"))
        every { courseRepo.findAll() } returns expectedCourses

        // When
        val result = coursesService.getAllCourses()

        // Then
        assertEquals(expectedCourses, result)
        verify(exactly = 1) { courseRepo.findAll() }
    }

    @Test
    fun `getAllCourses should return empty list when no courses exist`() {
        // Given
        every { courseRepo.findAll() } returns emptyList()

        // When
        val result = coursesService.getAllCourses()

        // Then
        assertTrue(result.isEmpty())
        verify(exactly = 1) { courseRepo.findAll() }
    }

}