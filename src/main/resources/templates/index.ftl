<#include "/part/header.ftl">

<script xmlns="http://www.w3.org/1999/html">
$(document).ready(function() {
    fillTable();

    $.getJSON('/rest/groups/', function (data) {
        var count = data.length;
        for (var i = 0; i < count; i++) {
            addToDropDown(data[i]);
        }
    });

    $("#trans-button").click(function () {
        var word = $("#search-input").val();
        $.ajax({
            method: "POST",
            url: "/rest/words/translate/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                word: word
            }),
            success: function (response) {
                if (!response.exists) {
                    var eng = $("#eng");
                    var rus = $("#rus");
                    eng.empty();
                    rus.empty();
                    eng.append(response.eng);
                    rus.append(response.rus);
                    $('#trans-modal').modal({
                        show: 'show'
                    });
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var jsonValue = jQuery.parseJSON(xhr.responseText);
                alert(jsonValue[0].errorMessage);
                alert(thrownError);
            }
        });
    });
    $("#add-btn").click(function () {
                var eng = $("#eng").text();
                var rus = $("#rus").text();
                $.ajax({
                    method: "POST",
                    url: "/rest/words/",
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        eng: eng,
                        rus: rus
                    }),
                    success: function (response) {
                        addToTable(response);
                        $('#trans-modal').modal('toggle');
                        $("#search-input").val('');
                        searchWords();
                    }
                })
            }
    );

    $("#search-input").keypress(function (e) {
        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
            $('#trans-button').click();
            return false;
        } else {
            return true;
        }
    });

    $("#delete-btn").click(function () {
        $('input[type=checkbox]').each(function () {
            if ($(this).is(":checked")) {
                var wordId = parseInt($(this).closest('tr').find('.word-id').text(), 10);
                $.ajax({
                    method: "DELETE",
                    url: "/rest/words/" + wordId,
                    contentType: 'application/json; charset=utf-8',
                    success: function (response) {

                        $(this).closest('tr').fadeOut(500);


                        //fillTable();
                        //alert("Слова успешно удалены");
                    }
                })
            }
        });
    });

    // Text translate functions:
    $("#trans-text-btn").click(function () {
        var text = $("#trans-textarea").val();
        $.ajax({
            method: "POST",
            url: "/rest/text/translate/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                text: text
            }),
            success: function (response) {
                var wTable = $("#result-table");
                for(var i = 0; i<response.length; i++){

                    var eng = response[i].eng;
                    var rus = response[i].rus;
                    var exists = response[i].exists;
                    var newTr = $("<tr>").appendTo(wTable);
                    $("<td class='engVal'>").appendTo(newTr).html(eng);
                    $("<td class='rusVal'>").appendTo(newTr).html(rus);
                    if (exists){
                        $("<td>").appendTo(newTr);
                        newTr.addClass("success")
                    } else {
                        $("<td>").appendTo(newTr).html("<input class='res-check' type='checkbox' value='yes'>");
                        //newTr.append("<td><input class='res-check' type='checkbox' value='yes'></td>")
                    }
                }
                /*if(!response.exists){
                    var eng = $("#eng");
                    var rus = $("#rus");
                    eng.empty();
                    rus.empty();
                    eng.append(response.eng);
                    rus.append(response.rus);
                    $('#trans-modal').modal({
                        show: 'show'
                    });
                }*/
                //alert(response);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var jsonValue = jQuery.parseJSON(xhr.responseText);
                alert(jsonValue[0].errorMessage);
                alert(thrownError);
            }
        });
    });
});
function fillTable() {
    $(".tab-row").remove();
    $.getJSON('/rest/words/', function(data) {
        var count = data.length;
        for (var i = 0; i < count; i++) {
            addToTable(data[i]);
        }
    });
}

function addToTable(data) {
    var aTable = $("#dict-table");

	var fragTrow = $("<tr class='tab-row'>").appendTo(aTable);
	$("<td class='word-id'>").appendTo(fragTrow).html(data.id);
	$("<td>").appendTo(fragTrow).html(data.eng);
	$("<td>").appendTo(fragTrow).html(data.rus);
	$("<td>").appendTo(fragTrow).html("<input class='check' type='checkbox' value='yes'>");
}

function addToDropDown(data) {
    var drop = $("#drop");
    drop.append("<li><a onclick=\"addToGroup("+data.id+")\">"+data.name+"</a></li>")
}
function searchWords() {
    // Declare variables
    var input, filter, table, tr, eng, rus, i;
    input = document.getElementById("search-input");
    filter = input.value.toUpperCase();
    table = document.getElementById("dict-table");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        eng = tr[i].getElementsByTagName("td")[1];
        rus = tr[i].getElementsByTagName("td")[2];

        if (eng || rus) {
            if (eng.innerHTML.toUpperCase().indexOf(filter) > -1 || rus.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function addToGroup(id) {
    $('input[type=checkbox]').each(function() {
        if ($(this).is(":checked")) {
            var wordId = parseInt($(this).closest('tr').find('.word-id').text(), 10);
            $.ajax({
                method: "PUT",
                url: "/rest/words/group/",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({
                    groupId: id,
                    wordId: wordId
                }),
                success: function (response){
                    //alert("Слова успешно добалены в группу");
                }
            })
        }
    });
}

</script>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
		<ul class="nav nav-tabs">
		  <li class="active" id="dictId"><a data-toggle="tab" href="#dict">Словарь</a></li>
		  <li><a data-toggle="tab" href="#menu1">Перевод текста</a></li>
		  <li><a data-toggle="tab" href="#menu2">Тренировка</a></li>
		</ul>

		<div class="tab-content">
		  <div id="dict" class="tab-pane fade in active">
			<div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">
                  <div class="row">
                      <div class="col-md-12">
                          <div class="input-group">
                              <input id="search-input" onkeyup="searchWords()" type="text" class="form-control" placeholder="Найти слово...">
                              <span class="input-group-btn">
                                <button id="trans-button" class="btn btn-default" type="button" >Перевести</button>
                              </span>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-md-12">
                          <div class="btn-group pull-right" style="margin-top: 10px">
                              <button id="addgr-btn" class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  <span class="glyphicon glyphicon-plus"></span> Добавить в группу <span class="caret"></span>
                              </button>
                              <ul id="drop" class="dropdown-menu">
                              </ul>
                          </div>
                          <button id="delete-btn" class="btn btn-danger pull-right" type="button" style="margin: 10px 5px 0 0">
                              <span class="glyphicon glyphicon-trash"></span> Удалить
                          </button>
                      </div>

                  </div>
			  </div>

			  <!-- Table -->
			  <table id="dict-table" class="table table-hover">
				<tr>
					<th class="col-xs-1">#</th>
					<th>English</th>
					<th>Русский</th>
					<th class="col-xs-1"></th>
				</tr>
			  </table>
			</div>
		  </div>
		  <div id="menu1" class="tab-pane fade">
              <div class="container-fluid">
                  <div class="row">
                      <div class="col-md-12">
                      <h3>Разбор текста по словам и перевод:</h3>
                      </div>
                  </div>
                  <div class="row" style="margin-top: 10px">
                      <div class="col-md-12">
                          <textarea id="trans-textarea" class="form-control" placeholder="Введите текст на английском или русском языке" rows="3" style="min-width: 100%; max-width: 100%" ></textarea>
                      </div>
                  </div>
                  <div class="row" style="margin-top: 5px">
                      <div class="col-md-12">
                          <button id="trans-text-btn" class="btn btn-success pull-right" type="button" >
                              <span class="glyphicon glyphicon-play-circle"></span> Перевести
                          </button>
                      </div>
                  </div>
                  <div class="row" style="margin-top: 20px">
                      <div class="col-md-12">
                            <h4>Выберите слова, которые необходимо добавить в словарь:</h4>
                          <h6 class="bg-success"> Слова, которые уже есть в словаре, помечены зеленым цветом.</h6>
                          <table id="result-table" class="table table-hover">
                              <tr>
                                  <th>English</th>
                                  <th>Русский</th>
                                  <th class='col-xs-1'></th>
                              </tr>
                          </table>

                      </div>
                  </div>
              </div>
		  </div>
		  <div id="menu2" class="tab-pane fade">
			<h3>Menu 2</h3>
			<p>Some content in menu 2.</p>
		  </div>
		</div>
    </div>
</div>

<div class="modal fade" id="trans-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Перевод слова</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div style="text-align: center">
                    	<h2><span id="eng"></span> - <span id="rus"></span></h2>
					</div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="add-btn" type="button" class="btn btn-primary">Добавить в словарь</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<#include "/part/footer.ftl">
