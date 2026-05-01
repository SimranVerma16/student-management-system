package com.guruji.studentmanagement.service;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import com.guruji.studentmanagement.dto.StudentDTO;
import com.guruji.studentmanagement.entity.StudentEntity;
import com.guruji.studentmanagement.exception.StudentNotFoundException;
import com.guruji.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // CREATE
    public StudentDTO saveStudent(StudentDTO dto) {

        StudentEntity student = new StudentEntity();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setLocation(dto.getLocation());

        StudentEntity saved = repository.save(student);

        return new StudentDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getLocation()
        );
    }

    // READ
    public Page<StudentDTO> getAllStudents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return repository.findAll(pageable)
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getLocation()
                ));
    }
    
    // DELETE
    public void deleteStudent(Long id) {

        StudentEntity student = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id " + id)
                );

        repository.delete(student);
    }

    // UPDATE
    public StudentDTO updateStudent(Long id, StudentDTO dto) {

        StudentEntity existingStudent = repository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id " + id)
                );

        existingStudent.setName(dto.getName());
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setLocation(dto.getLocation());

        StudentEntity updated = repository.save(existingStudent);

        return new StudentDTO(
                updated.getId(),
                updated.getName(),
                updated.getEmail(),
                updated.getLocation()
        );
    }
}