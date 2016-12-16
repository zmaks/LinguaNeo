<#include "/part/header.ftl">

<script xmlns="http://www.w3.org/1999/html">
$(document).ready(function() {
    fillTable();
    loadGroups();
    $("#dictId").click(function () {
        fillTable();
    });

    $("#trans-button").click(function () {
        var inp = $("#search-input");
        var word = inp.val();

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
                inp.val('');
                searchWords();
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

    $("#search-input").keyup(function (e) {
        var inp = $(this);
        var btn = $('#trans-button');
        var correct = true;
        if (inp.val().length<2 || inp.val().length>20) {
            correct = false;
        }
        if(correct){
            btn.attr('disabled', false);
            if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
                btn.click();
                return false;
            } else {
                return true;
            }
        }else {
            btn.attr('disabled', 'disabled');
        }
    });

    $("#trans-textarea").keyup(function (e) {
        var inp = $(this);
        var btn = $('#trans-text-btn');
        var correct = true;
        if (inp.val().length<2 || inp.val().length>200) {
            correct = false;
        }
        if(correct) {
            btn.attr('disabled', false);
            if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
                btn.click();
                return false;
            } else {
                return true;
            }
        } else {
            btn.attr('disabled', 'disabled');
        }
    });

    $("#create-gr-btn").click(function () {
        var inp = $("#group-name")
        var name = inp.val();
        $.ajax({
            method: "POST",
            url: "/rest/groups/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                name: name
            }),
            success: function (response) {
                loadGroups();
                $('#group-modal').modal('toggle');
                inp.val('');
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var jsonValue = jQuery.parseJSON(xhr.responseText);
                alert(jsonValue[0].errorMessage);
                alert(thrownError);
            }
        });
    });

    $("#delete-btn").click(function () {
        $('input.check:checkbox').each(function () {
            if ($(this).is(":checked")) {
                var row = $(this);
                var wordId = parseInt($(this).closest('tr').find('.word-id').text(), 10);
                $.ajax({
                    method: "DELETE",
                    url: "/rest/words/" + wordId,
                    contentType: 'application/json; charset=utf-8',
                    success: function (response) {
                        row.closest('tr').fadeOut(500);
                    }
                })
            }
        });
    });

    $("#check-all-dict").click(function () {
        $('input.check:checkbox').not(this).prop('checked', this.checked);
    });

    $("#check-all-trans").click(function () {
        $('input.res-check:checkbox').not(this).prop('checked', this.checked);
    });


    // Text translate functions:
    $("#trans-text-btn").click(function () {
        var inp = $("#trans-textarea");
        var text = inp.val();

        $.ajax({
            method: "POST",
            url: "/rest/text/translate/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                text: text
            }),
            success: function (response) {
                var wTable = $("#result-table");
                $(".trans-text-tr").remove();
                for(var i = 0; i<response.length; i++){

                    var eng = response[i].eng;
                    var rus = response[i].rus;
                    var exists = response[i].exists;
                    var newTr = $("<tr class='trans-text-tr'>").appendTo(wTable);
                    $("<td class='engVal'>").appendTo(newTr).html(eng);
                    $("<td class='rusVal'>").appendTo(newTr).html(rus);
                    if (exists){
                        $("<td>").appendTo(newTr);
                        newTr.addClass("success")
                    } else {
                        $("<td>").appendTo(newTr).html("<input class='res-check' type='checkbox' value='yes'>");
                    }
                }
                var tbfoot = $("#tb-footer");
                if (!tbfoot.has("#add-words-btn").length) {
                    tbfoot.append("<button id='add-words-btn' class='btn btn-success pull-right' type='button'><span class='glyphicon glyphicon-plus'></span> Добавить в словарь </button>");
                    $("#add-words-btn").click(function () {
                        $('input.res-check:checkbox').each(function () {
                            if ($(this).is(":checked")) {
                                var eng = $(this).closest('tr').find('.engVal').text();
                                var rus = $(this).closest('tr').find('.rusVal').text();
                                var row = $(this);
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
                                        row.closest('tr').fadeOut(500);
                                    }
                                })
                            }
                        });
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




    // Trainig functions:
    $("#training-btn").click(function () {
        startTraining(null);
    });

    $("#ans-btns").on('click', '.answer-btn', function () {
        var question = $("#question").text();
        var ans = $(this);
        var ansTxt = ans.text();
        for(var i = 0; i < trainingWords.length; i++){
            console.log(trainingWords[i].question);
        }
        $.ajax({
            method: "POST",
            url: "/rest/training/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                question: question,
                answer: ansTxt
            }),
            success: function (response) {
                var ansbtns = $("#ans-btns");
                ans.removeClass('btn-primary');
                if (response.correct) {
                    ans.toggleClass('btn-success');
                    trueAnsCount++;
                }else {
                    var trueAns = ansbtns.find("button:contains("+response.answer+")").parent();
                    trueAns.removeClass('btn-primary');
                    trueAns.toggleClass('btn-success');
                    ans.toggleClass('btn-danger');
                }
                ansbtns.find("button").prop("disabled",true);
                wordInd++;
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var jsonValue = jQuery.parseJSON(xhr.responseText);
                alert(jsonValue[0].errorMessage);
                alert(thrownError);
            }
        });

    });

    $("#next-btn").click(function () {
        var amount = trainingWords.length;
        if(wordInd == amount){
            $("#ans-btns").empty();
            var q = $("#question");
            q.empty();
            q.append("<h2>Тренировка закончена!</h2><h4>Количество правильных ответов: "+trueAnsCount+"</h4><h4> Всего слов: "+amount+"</h4>")
            $.ajax({
                method: "PUT",
                url: "/rest/training/",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({
                    id: trainingId,
                    mistakes: (amount-trueAnsCount)
                }),
                error: function (xhr, ajaxOptions, thrownError) {
                    var jsonValue = jQuery.parseJSON(xhr.responseText);
                    alert(jsonValue[0].errorMessage);
                    alert(thrownError);
                }
            });
            $(this).hide();//toggleClass("disabled")
            wordInd=0;
            trueAnsCount = 0;
        } else {
            addToTraining();
        }
    });
});
var trainingWords;
var wordInd = 0;
var trueAnsCount = 0;
var trainingId = 0;

function addToTraining() {
    var word = trainingWords[wordInd];
    var q = $("#question");
    var ansDiv = $("#ans-btns");
    ansDiv.empty();
    q.empty();
    q.append(word.question);
    var ans = word.answers;
    for(var i = 0; i<ans.length; i++){
        $("<button class='btn btn-primary answer-btn'>").appendTo(ansDiv).html(ans[i]);
    }
}

function startTraining(groupId) {
    $("#next-btn").show();//removeClass("disabled");
    $.ajax({
        method: "GET",
        url: "/rest/training/"+ (groupId == null ? '' : groupId),
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            trainingWords = response.words;
            trainingId = response.id;
            addToTraining();
            $('#training-modal').modal('toggle');

        },
        error: function (xhr, ajaxOptions, thrownError) {
            var jsonValue = jQuery.parseJSON(xhr.responseText);
            alert(jsonValue[0].errorMessage);
            alert(thrownError);
        }
    });
}

function fillTable() {
    $(".tab-row").remove();
    $.getJSON('/rest/words/', function(data) {
        var count = data.length;
        for (var i = 0; i < count; i++) {
            addToTable(data[i]);
        }
    });
}

function fillStat() {
    var progBar = $("#prog-bar");
    var progMBar = $("#prog-m-bar");
    var hword = $("#hard-word");
    var table = $("#train-table");

    $.getJSON('/rest/statistic/', function(data) {
        progBar.css("width",function(i){
            var all = data.allCount;
            var ler = data.learnedCount;
            var res = Math.floor((ler / all) * 100);
            var percent = res+'%';
            progBar.empty();
            progBar.append(percent);
            return percent;
        });
        progMBar.css("width",function(i){
            var percent = data.mistakesPercent+'%';
            progMBar.empty();
            progMBar.append(percent);
            return percent;
        });
        hword.empty();
        hword.append(data.difficultWord.eng + " - " + data.difficultWord.rus);
        $(".tr-row").remove();
        var trainings = data.trainings;
        for (var i = 0; i < trainings.length; i++) {
            var fragTrow = $("<tr class='tr-row'>").appendTo(table);
            var am = trainings[i].wordsAmount;
            var mis = trainings[i].mistakesAmount;
            $("<td>").appendTo(fragTrow).html(trainings[i].id);
            $("<td>").appendTo(fragTrow).html((new Date(trainings[i].trainingDate)).toLocaleString());
            $("<td>").appendTo(fragTrow).html(am);
            $("<td>").appendTo(fragTrow).html(mis);
            var per = Math.floor(((am-mis) / am) * 100);
            if(per > 79) fragTrow.toggleClass('success');
            if(per > 50 && per < 80) fragTrow.toggleClass('warning');
            if(per < 51 && per > 0) fragTrow.toggleClass('danger');
        }
    });
}

function addToTable(data) {
    var aTable = $("#dict-table");

	var fragTrow = $("<tr class='tab-row'>").appendTo(aTable);
	$("<td class='word-id'>").appendTo(fragTrow).html(data.id);
	$("<td>").appendTo(fragTrow).html(data.eng);
	$("<td>").appendTo(fragTrow).html(data.rus);
	var grName = data.wordsGroup ? data.wordsGroup.name : '';
	$("<td>").appendTo(fragTrow).html(grName);
	$("<td>").appendTo(fragTrow).html("<input class='check' type='checkbox' value='yes'>");
	if (data.mistakeIndex==0) fragTrow.toggleClass('success');
	if (data.mistakeIndex>0&&data.mistakeIndex<4) fragTrow.toggleClass('warning');
	if (data.mistakeIndex>3) fragTrow.toggleClass('danger');
}

function addGroupOpenModal() {
    $('#group-modal').modal('toggle');
}

function loadGroups() {
    $.getJSON('/rest/groups/', function (data) {
        var drop = $("#drop");
        var dropTr = $("#drop-train");
        drop.empty();
        dropTr.empty();
        for (var i = 0; i < data.length; i++) {
            drop.append("<li><a onclick='addToGroup(" + data[i].id + ")'>" + data[i].name + "</a></li>");
            dropTr.append("<li><a onclick='startTraining(" + data[i].id + ")'>" + data[i].name + "</a></li>");
        }
        drop.append("<li class='divider'></li>");
        drop.append("<li class='bg-success'><a onclick='addGroupOpenModal()'><span class='glyphicon glyphicon-plus'></span> Создать группу</a></li>")
    });

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
    $('input.check:checkbox').each(function() {
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
    fillTable();
}

</script>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
		<ul class="nav nav-tabs">
		  <li class="active" id="dictId"><a data-toggle="tab" href="#dict">Словарь</a></li>
		  <li><a data-toggle="tab" href="#menu1">Перевод текста</a></li>
		  <li><a data-toggle="tab" href="#menu2">Тренировка</a></li>
		  <li><a data-toggle="tab" href="#menu3" onclick="fillStat()">Статистика</a></li>
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
                                <button id="trans-button" class="btn btn-default" type="button" disabled="disabled">Перевести</button>
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
					<th class="col-xs-4">English</th>
					<th class="col-xs-4">Русский</th>
					<th class="col-xs-3">Группа</th>
					<th class="col-xs-1"><input type="checkbox" id="check-all-dict"></th>
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
                          <button id="trans-text-btn" class="btn btn-success pull-right" type="button" disabled="disabled">
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
                                  <th class='col-xs-1'><input type="checkbox" id="check-all-trans"></th>
                              </tr>
                          </table>

                      </div>
                  </div>
                  <div class="row">
                      <div class="col-md-12" id="tb-footer">
                      </div>
                  </div>

              </div>
		  </div>
		  <div id="menu2" class="tab-pane fade">
              <div class="container-fluid">
                  <div class="row">
                      <div class="col-md-12">
                          <h3>Тренировка слов</h3>
                      </div>
                  </div>
                  <div class="row" style="margin-top: 5px">
                      <div class="col-md-9">
                          <h4> Вам поочередно будет предложены несколько английских слов из Вашего словаря и по несколько вариантов ответов на русском языке для каждого. <br>Удачи!</h4>
                      </div>
                      <div class="col-md-3">
                          <button id="training-btn" class="btn btn-success" type="button" style="margin-top: 5px">
                              <span class="glyphicon glyphicon-play-circle"></span> Начать тренировку
                          </button>
                          <div class="btn-group" style="margin-top: 5px">
                              <button id="train-gr-btn" class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Тренировка группы <span class="caret"></span>
                              </button>
                              <ul id="drop-train" class="dropdown-menu">
                              </ul>
                          </div>
                      </div>
                  </div>

              </div>
		  </div>
            <div id="menu3" class="tab-pane fade">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Ваш прогресс</h3>
                            <h6 style="color: grey">Процент изученных слов. Изученным является слово, которое Вы правильно перевели 3 и более раз.</h6>

                            <div class="progress">
                                <div id="prog-bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                                </div>
                            </div>
                            <h3>Процент ошибок в тренировках:</h3>
                            <div class="progress">
                                <div id="prog-m-bar" class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-danger">
                                <div class="panel-heading">Одно из самых сложных для Вас слов</div>
                                <div class="panel-body" style="text-align: center">
                                    <h2 id="hard-word"></h2>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">Пройденные тренеровки</div>
                                <div class="panel-body">
                                    <table id="train-table" class="table table-hover">
                                        <tr>
                                            <th class="col-xs-1">#</th>
                                            <th>Дата</th>
                                            <th>Изучено слов</th>
                                            <th>Кол-во ошибок</th>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
		</div>
    </div>
</div>

<div class="modal fade" id="trans-modal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
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

<div class="modal fade" id="training-modal" tabindex="-1" role="dialog" aria-labelledby="trainingModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Тренировка слов</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div style="text-align: center">
                            <h1><span id="question">Question</span></h1>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px">
                        <div class="col-md-6 col-md-offset-3">
                            <div class="btn-group-vertical" style="min-width: 100%" id="ans-btns">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="next-btn" type="button" class="btn btn-primary">Следующее слово</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade bs-example-modal-sm" id="group-modal" tabindex="-1" role="dialog" aria-labelledby="trainingModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Создание группы</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-10 col-md-offset-1">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon3">Название</span>
                            <input id="group-name" type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                <button id="create-gr-btn" type="button" class="btn btn-primary">Создать</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<#include "/part/footer.ftl">
