var createBook = {
    setup: function(){
        $(document).on('click', '#submit', createBook.PostBook);
    }
    ,PostBook: function(){
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
        $('#result').html($('<p>' + jsonData.toString() + '</p>')).show();
    }
}

$(document).ready(function (){
    createBook.setup()
})
