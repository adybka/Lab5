var createBook = {
    setup: function(){
        $(document).on('click', '#submit', createBook.PostBook);
    }
    ,PostBook: function(e){
        e.preventDefault();
        var input = $("input");
        var params = {
            name:input[0].value
        };
        var uri = "/addressBooks";

        $.ajax({
            method: "POST",
            url: uri,
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: createBook.GetBooks
        })
    }
    ,GetBooks: function(){
        $.ajax({
            method: "GET",
            url: "/addressBooks",
            dataType: 'json',
            success: createBook.showResult
        })
    }
    ,showResult: function(jsonData){
        $('#form').hide()
        var books = jsonData._embedded.addressBooks;

        var list = document.createElement("ul");
        for( book in books) {
            var text = "Name: " + books[book].name;
            var listItem = document.createElement("li");
            var textItem = document.createTextNode(text);
            listItem.appendChild(textItem);
            list.appendChild(listItem);
        }
        var elem = document.getElementById("blah")
        elem.appendChild(list);

    }
}

$(document).ready(function (){
    createBook.setup()
})
