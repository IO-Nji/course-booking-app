package io.nji.student.mapper;

import io.nji.student.model.Student;
import io.nji.student.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

        // Convert Student entity to StudentDTO
        public StudentDTO toDTO(Student student) {
            StudentDTO dto = new StudentDTO();
            dto.setStudentId(student.getStudentId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setSkillLevel(student.getSkillLevel());
            return dto;
        }
    
        // Convert StudentDTO to Student entity
        public Student toEntity(StudentDTO dto) {
            Student student = new Student();
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setSkillLevel(dto.getSkillLevel());
            return student;
        }
    
        // Convert a list of Student entities to a list of StudentDTOs
        public List<StudentDTO> toDTOList(List<Student> students) {
            return students.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }

}
