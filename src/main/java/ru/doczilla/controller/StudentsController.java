package ru.doczilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.doczilla.entity.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final DataSource dataSource;

    @Autowired
    public StudentsController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        String query = "INSERT INTO students (first_name, last_name, patronymic, birth_date, group_name) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getPatronymic());
            preparedStatement.setDate(4, Date.valueOf(student.getBirthDate()));
            preparedStatement.setString(5, student.getGroupName());
            preparedStatement.executeUpdate();
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
