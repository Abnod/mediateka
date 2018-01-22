var json;

document.onreadystatechange = function () {
    if (document.readyState === 'complete') {
        getAll();
    }
};

var modal = document.getElementById('infoWindow');
var span = document.getElementById("infoClose");
span.addEventListener("click", function () {
    modal.style.display = "none";
});

window.addEventListener("click", function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

function getAll() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/media/all");
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            // обработать ошибку
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            try {
                json = JSON.parse(xhr.responseText);
            } catch (e) {
                alert("Некорректный ответ " + e.message);
            }
            showAll(json);
        }
    };
}

function showAll(data) {
    data.forEach(function (media) {
        var div = media_list.appendChild(document.createElement('tr'));
        div.setAttribute("class", "media_row");
        var title = div.appendChild(document.createElement('td'));
        title.setAttribute("class", "media_row_cell");
        title.innerHTML = media.title;
        var singer = div.appendChild(document.createElement('td'));
        singer.setAttribute("class", "media_row_cell");
        singer.innerHTML = media.singer;
        var type = div.appendChild(document.createElement('td'));
        type.setAttribute("class", "media_row_cell");
        type.innerHTML = media.type;
        var path = div.appendChild(document.createElement('td'));
        path.setAttribute("class", "media_row_cell");
        path.innerHTML = media.path;
        var show = div.appendChild(document.createElement('td'));
        show.setAttribute("class", "show_button");
        show.setAttribute("id", media.id);
        show.innerHTML = "Show info";
        show.addEventListener("click", function () {
            info_singer.innerHTML = media.singer;
            if (media.type === "VIDEO") {
                info_singer.style.display = "none"
            } else {
                info_singer.style.display = "inline"
            }
            info_type.innerHTML = media.type;
            info_title.innerHTML = media.title;
            info_path.innerHTML = media.path;
            modal.style.display = "block";
        })
    });
}