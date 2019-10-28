$(document).ready(() => {
    $(".editForm").submit(function (e) {
        e.preventDefault(); //отключаем поведение формы по умолчанию (отправка на сервер)

        const bookId = $(".book-id").val();
        const bookName = $(".book-name").val();
        const bookAuthor = $(".book-author").val();
        const bookYear = $(".book-year").val();
        const bookCensored = Boolean( $(".book-censored").val());
        const json = {
            id: bookId,
            name: bookName,
            author: bookAuthor,
            year: bookYear,
            censored : bookCensored
        };

        //отправляем данные на сервер
        $.ajax({
            url: "/books/" + bookId,
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