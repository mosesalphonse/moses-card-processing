function refresh() {
    $.get('/cards', function (cards) {
        var list = '';
        (cards || []).forEach(function (card) {
            list = list
                + '<tr>'
                + '<td>' + card.id + '</td>'
                + '<td>' + card.name + '</td>'
                + '<td>' + card.cardNumber + '</td>'
                + '<td>' + '£' + card.balance + '</td>'
                + '<td>' + '£' + card.limit + '</td>'
                + '</tr>'
        });
        document.getElementById('card-name').value = '';
        document.getElementById('card-number').value = '';
        document.getElementById('card-limit').value = '';

        if (list.length > 0) {
            list = ''
                + '<table border="1" ><thead><th>Id</th><th>Name</th><th>Card Number</th><th>Balance</th><th>Limit</th><th></th></thead>'
                + list
                + '</table>';
        } else {
            list = "No cards in database"
        }
        $('#all-cards').html(list);
    });
}


$(document).ready(function () {

    $('#create-card-button').click(function () {

        if(!IsEmpty())
        {
            return false;  
        }
        var cardName = $('#card-name').val();
        var cardNumber = $('#card-number').val();
        var cardLimit = $('#card-limit').val();
        document.getElementById('card-name').value = '';
        document.getElementById('card-number').value = '';
        document.getElementById('card-limit').value = '';
        $.post({
            url: '/cards',
            contentType: 'application/json',
            data: JSON.stringify({ name: cardName, cardNumber: cardNumber, limit: cardLimit })
        }).then(refresh);
    });
    refresh();

});

function IsEmpty() {
    if (((document.getElementById('card-name').value === "")) || (document.getElementById('card-number').value === "") || (document.getElementById('card-limit').value === "")){
      alert("Please Enter valid input");
      return false;
    }
    return true;
  }
