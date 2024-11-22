function deleteStudent(id) {
    $.ajax({
        url: `/students/${id}`,
        type: "DELETE",
        success: function () {
            alert("Студент удален!");
            loadStudents();
        },
        error: function () {
            alert("Ошибка при удалении студента.");
        }
    });
}
