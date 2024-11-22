function loadStudents() {
    $.ajax({
        url: "/students",
        type: "GET",
        success: function (data) {
            let table = "<table><tr><th>ID</th><th>Имя</th><th>Фамилия</th><th>Группа</th></tr>";
            data.forEach(student => {
                table += `<tr>
                            <td>${student.id}</td>
                            <td>${student.firstName}</td>
                            <td>${student.lastName}</td>
                            <td>${student.groupName}</td>
                            <td><button onclick="deleteStudent(${student.id})">Удалить</button></td>
                          </tr>`;
            });
            table += "</table>";
            $("#studentList").html(table);
        }
    });
}
