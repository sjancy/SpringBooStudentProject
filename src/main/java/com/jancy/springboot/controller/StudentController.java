package com.jancy.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jancy.springboot.exception.ResourceNotFoundException;
import com.jancy.springboot.model.Student;
import com.jancy.springboot.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	//get all students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
		
	}
	
	//craete student api
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	//get student by id rest api
	@GetMapping("/students/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
		Student student=studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student not exits with id:" + studentId));
		
		return ResponseEntity.ok(student);
	}
	
	
	//update student rest api
	@PutMapping("/students/{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId, @RequestBody Student studentDetails){
		
		Student student=studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student not exits with id:" + studentId));
		
		student.setName(studentDetails.getName());
		student.setAge(studentDetails.getAge());
		student.setAddress(student.getAddress());
		
		Student updateStudent= studentRepository.save(student);
		return ResponseEntity.ok(updateStudent);
		
	}
	
	//delete student rest api
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer studentId){
		
		Student student=studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student not exits with id:" + studentId));
		studentRepository.delete(student);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted" , Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
