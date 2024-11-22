$("#addStudentForm").on("submit", function (e) {
    e.preventDefault();
    const data = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        patronymic: $("#patronymic").val(),
        birthDate: $("#birthDate").val(),
        groupName: $("#groupName").val()
    };
    $.ajax({
        url: "/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            alert("Студент добавлен!");
            loadStudents();
        },
        error: function () {
            alert("Ошибка при добавлении студента.");
        }
    });
});
