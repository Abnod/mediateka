var json;
var roleAdmin;
var modalAdd;
var spanAdd;

var modal = document.getElementById('infoWindow');
var span = document.getElementById("infoClose");
span.addEventListener("click", function () {
    modal.style.display = "none";
});

window.addEventListener("click", function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    } else if (event.target === modalAdd) {
        modalAdd.style.display = "none";
    }
});

document.onreadystatechange = function () {
    if (document.readyState === 'complete') {
        var checkRights = new XMLHttpRequest();
        checkRights.open("POST", "media/add");
        checkRights.send();
        checkRights.onreadystatechange = function () {
            if (checkRights.readyState !== 4) return;
            roleAdmin = checkRights.status !== 403;
            setupAddFunc();
            getAll();
        };
    }
};

function getAll() {
    clearTableContent();
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
        var type = div.appendChild(document.createElement('td'));
        type.setAttribute("class", "media_row_cell");
        type.innerHTML = media.type;
        var show = div.appendChild(document.createElement('td'));
        show.setAttribute("class", "show_button");
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
        });
        if (roleAdmin) {
            var remove = div.appendChild(document.createElement('td'));
            remove.setAttribute("class", "remove_button");
            remove.innerHTML = "Delete";
            remove.addEventListener("click", function () {
                var removeRequest = new XMLHttpRequest();
                removeRequest.open("DELETE", 'media/remove/' + media.id);
                removeRequest.send();
                removeRequest.onreadystatechange = function () {
                    if (removeRequest.readyState !== 4) return;
                    getAll();
                }
            });
        }
    });
}

function clearTableContent() {
    var rowsForRemoval = media_list.getElementsByClassName("media_row");
    while (rowsForRemoval[0]) rowsForRemoval[0].parentNode.removeChild(rowsForRemoval[0]);
}

function setupAddFunc() {
    var adb = document.getElementById("addLink");
    modalAdd = document.getElementById('newForm');
    spanAdd = document.getElementById("newClose");
    if (roleAdmin) {
        adb.style.display = "block";
    } else {
        adb.style.display = "none";
    }
    adb.addEventListener("click", function (event) {
        event.preventDefault();
        modalAdd.style.display = "block";
    });
    spanAdd.addEventListener("click", function () {
        modalAdd.style.display = "none";
    });
    modalAdd.addEventListener("submit", function (event) {
        event.preventDefault();
        var request = new XMLHttpRequest();
        var formData = new FormData(document.forms.newM);
        request.open("POST", '/media/add');
        request.send(formData);
        request.onreadystatechange = function () {
            if (request.readyState !== 4) return;
            getAll();
        }
    })
}