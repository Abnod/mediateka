$(document).ready(function () {

    $("#fill_media").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/media/all",
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = JSON.parse().stringify(data, null, 4);
            $('#media_list').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        }
    });

}