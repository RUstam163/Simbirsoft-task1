function deleteBook(id) {
    $.ajax({
        url: "/books/" + id,
        type: "DELETE",
        contentType: "application/json",
        dataType: "text",
        data: JSON.stringify(id),
        success: function () {
            alert("Книга успешно удалена");
        },
        error: function (err) {
            alert("Ошибка удаления книги" + JSON.stringify(err));
        }
    });
}



