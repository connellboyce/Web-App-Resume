function sendEmail() {
    var returnEmail = $("#returnEmail").val();
    var emailSubject = $("#emailSubject").val();
    var content = $("#content").val();

    var emailRequest = {
        'returnEmail' : returnEmail,
        'emailSubject' : emailSubject,
        'content' : content
    };

    console.log(emailRequest);

    $.ajax({
        url: '/sns/send',
        type: "POST",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(emailRequest),
        success: function (result) {
            console.log("E-Mail successfully sent!");
            location.reload();
        },
        error: function (xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    })
}