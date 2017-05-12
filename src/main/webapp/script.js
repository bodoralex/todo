var filter = 0;
var status = "a";
function refresh() {
    $.get("./todo",{state:status}, show);
}

function show(toDoList) {

    $("#todos").empty();
    toDoList.forEach(function (toDo) {
        var li = document.createElement("li");
        li.id = toDo.id;
        li.innerHTML += '<i class="fa fa-trash icons" aria-hidden="true" onclick="del(' + toDo.id + ')"></i>';
        li.innerHTML += '<i class="fa fa-check-square icons" aria-hidden="true" onclick="toggle('+toDo.id+')"></i>';

        var text = document.createElement("span");
        text.innerHTML = toDo.text;
        if (toDo.done == true) {
            text.className = "done";
        }
        li.append(text);
        $("#todos").append(li);
    });
}

function toggle(id) {
    $.post("./toggle", {id:id}, refresh)
}

function del(id) {
    $.post("./delete", {id:id}, refresh)
}

function showDone() {
    status = "d";
    refresh();
}

function showInProgress() {
    status = "p";
    refresh();
}

function showAll() {
    status = "a";
    refresh();
}
$(function () {


    $('#newtodo').keydown(function (event) {
        var keyCode = (event.keyCode ? event.keyCode : event.which);
        if (keyCode == 13) {
            $.post("./todo", {text: $("#newtodo").val()}, function () {
                $('#newtodo').val("");
                refresh();
            });
        }
    });


});
