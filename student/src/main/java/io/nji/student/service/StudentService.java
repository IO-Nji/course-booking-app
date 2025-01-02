package io.nji.student.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nji.student.dto.StudentDTO;
import io.nji.student.mapper.StudentMapper;
import io.nji.student.model.Student;
import io.nji.student.repository.StudentRepository;;

@Service
public class StudentService {

        private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = studentMapper.toDTOList(students);
        logger.info("Received students: {}", studentDTOs);
        return studentDTOs;
    }

    public StudentDTO getStudentById(Long studentId) {
        logger.info("Getting student with id: {}", studentId);
        Optional<Student> student = studentRepository.findById(studentId);
        StudentDTO studentDTO = student.map(studentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        logger.info("Received student: {}", studentDTO);
        return studentDTO;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        logger.info("Received student DTO: {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        logger.info("Saved student");
        return studentMapper.toDTO(savedStudent);
    }

    public void deleteStudent(Long studentId) {
        logger.info("Deleting student with id: {}", studentId);
        studentRepository.deleteById(studentId);
        logger.info("Deleted student");
    }

    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        logger.info("Updating student with id: {}", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setSkillLevel(studentDTO.getSkillLevel());
        Student updatedStudent = studentRepository.save(student);
        logger.info("Updated student");
        return studentMapper.toDTO(updatedStudent);
    }
}
