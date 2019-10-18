$(document).ready(() => {
    $(".editForm").submit(function (e) {
        e.preventDefault(); //отключаем поведение формы по умолчанию (отправка на сервер)

        var bookId = $(".book-id").val();
        var bookName = $(".book-name").val();
        var bookAuthor = $(".book-author").val();
        var bookYear = $(".book-year").val();
        var json = { "id" : bookId, "name" : bookName, "author": bookAuthor, "year": bookYear };

        //собираем в объект данные по книге из полей формы
        // const dataToSend = {
        //     id: bookId,
        //     name: bookName,
        //     author: bookAuthor,
        //     year: bookYear
        // };

        //отправляем данные на сервер
        $.ajax({
            url: "/books/" + bookId ,
            type: "PUT",
            contentType: "application/json",
            dataType: "text",
            // data: JSON.stringify(dataToSend),
            data: JSON.stringify(json),
            success: function () {
                alert("Книга успешно изменена");
            },
            error: function (err) {
                alert("Ошибка изменения книги" + JSON.stringify(err));
            }
        });
    });
});