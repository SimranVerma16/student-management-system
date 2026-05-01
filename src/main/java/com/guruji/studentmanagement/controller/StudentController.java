package com.guruji.studentmanagement.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guruji.studentmanagement.dto.StudentDTO;
import com.guruji.studentmanagement.response.ApiResponse;
import com.guruji.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "Operations related to student management")
public class StudentController {

    @Autowired
    private StudentService service;

    // CREATE
    @Operation(summary = "Create a new student")
    @PostMapping
    public ApiResponse<StudentDTO> saveStudent(@Valid @RequestBody StudentDTO studentDTO) {

        StudentDTO saved = service.saveStudent(studentDTO);

        return new ApiResponse<>(
                true,
                "Student created successfully",
                saved
        );
    }

    // READ ALL
    @Operation(summary = "Get all students with pagination")
    @GetMapping
    public ApiResponse<Page<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<StudentDTO> students = service.getAllStudents(page, size, sortBy);

        return new ApiResponse<>(
                true,
                "Students fetched successfully",
                students
        );
    }

    // UPDATE
    @Operation(summary = "Update student details")
    @PutMapping("/{id}")
    public ApiResponse<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO studentDTO) {

        StudentDTO updated = service.updateStudent(id, studentDTO);

        return new ApiResponse<>(
                true,
                "Student updated successfully",
                updated
        );
    }
    
    // DELETE
    @Operation(summary = "Delete student by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStudent(@PathVariable Long id) {

        service.deleteStudent(id);

        return new ApiResponse<>(
                true,
                "Student deleted successfully",
                null
        );
    }
}