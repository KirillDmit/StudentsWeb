package controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.doczilla.controller.StudentsController;
import ru.doczilla.entity.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentsControllerTest {

    @InjectMocks
    private StudentsController controller;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    public StudentsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddStudent_Success() throws Exception {
        // Мокируем поведение
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Тестовые данные
        Student student = new Student(null, "Иван", "Иванов", "Иванович", LocalDate.of(2000, 1, 1), "Группа 1");

        // Вызываем метод
        ResponseEntity<Void> response = controller.addStudent(student);

        // Проверяем результат
        assertEquals(200, response.getStatusCodeValue());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testAddStudent_Failure() throws Exception {
        // Мокируем ошибку соединения
        when(dataSource.getConnection()).thenThrow(new RuntimeException("Ошибка подключения"));

        // Тестовые данные
        Student student = new Student(null, "Иван", "Иванов", "Иванович", LocalDate.of(2000, 1, 1), "Группа 1");

        // Вызываем метод
        ResponseEntity<Void> response = controller.addStudent(student);

        // Проверяем результат
        assertEquals(500, response.getStatusCodeValue());
    }
}

