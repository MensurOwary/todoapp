// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("addBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

function closer(){
  modal.style.display = "block";
}

function closeModal(){
    modal.style.display = "none";
}

$('body').on('click', '.title', function(e){
    e.preventDefault();
    var value = $(this).css("text-decoration");
    var id = $(".id",$(this).parent()).text();
    if (value=="line-through"){
        var changes = {
            textDecoration: "none"
        };
        $(this).css(changes);
        postRequester(id, false);
    }else{
        var changes = {
            textDecoration: "line-through"
        };
        $(this).css(changes);
        postRequester(id, true);
    }
});

$('body').on('click', '.update', function(e){
    e.preventDefault();
    var id = $(".id",$(this).parent().parent()).text();
    var title = $(".title",$(this).parent().parent()).text();
    var desc = $(".desc",$(this).parent().parent()).text();

    $("#id").val(id);
    $("#title").val(title);
    $("#desc").val(desc);
    $("#form").attr('action','update');
    closer();
});

$('#addBtn').click(function () {
    $("#title").val("");
    $("#desc").val("");
    $("#form").attr('action','add');
    closer();
});

$('body').on('click', '.delete', function(e){
    e.preventDefault();
    var id = $(".id",$(this).parent().parent()).text();
    $.post("delete",{id: id},function (data) {
        if(data=="true"){
            pageRefresh()
        }else{
            alert("Error Occured While Deleting");
        }
    },'html');
});

$("#form").submit(function (e) {
    e.preventDefault();
    var value = $("#form").attr('action');
    if(value=="add"){
        addSubmission();
    }else if(value="update"){
        updateSubmission();
    }
});

function addSubmission(){
    var title = $("#title").val();
    var desc = $("#desc").val();
    $.post("add", {title:title, desc:desc}, function (data) {
        if (data=="true"){
            closeModal();
            pageRefresh();
        }else{
            closeModal();
            alert("Error Occured");
        }
    })
}

function updateSubmission(){
    var id = $("#id").val();
    var title = $("#title").val();
    var desc = $("#desc").val();
    $.post("update", {id:id, title:title, desc:desc}, function (data) {
        if (data=="true"){
            closeModal();
            pageRefresh();
        }else{
            closeModal();
            alert("Error Occured");
        }
    })
}

function pageRefresh(){
    $.get(baseURL,function (data) {
        var value = $(data).find("#box-body").html();
        $("#box-body").html(value);
    },'html');
}

function postRequester(id, done) {
    if (done){
        $.post("done",{id: id},function (data) {
                if(data=="true"){
                    pageRefresh();
                }
        },'html');
    }else {
        $.post("undone",{id: id},function (data) {
            if(data=="true"){
                pageRefresh();
            }
        },'html');
    }
}

// When the user clicks the button, open the modal
btn.onclick = function() {
    closer();
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

