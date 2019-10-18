$(document).ready(() => {
    $(".addForm").submit(function (e) {
        e.preventDefault(); //отключаем поведение формы по умолчанию (отправка на сервер)

        const bookName = $(".book-name").val();
        const bookAuthor = $(".book-author").val();
        const bookYear = $(".book-year").val();
        const json = {
            name: bookName,
            author: bookAuthor,
            year: bookYear
        };

        //отправляем данные на сервер
        $.ajax({
            url: "/books",
            type: "POST",
            contentType: "application/json",
            dataType: "text",
            data: JSON.stringify(json),
            success: function () {
                alert("Книга успешно добавлена");
            },
            error: function (err) {
                alert("Ошибка добавления книги" + JSON.stringify(err));
            }
        });
    });
});