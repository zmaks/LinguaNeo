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
                showError(jsonValue.errorMessage);
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
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var jsonValue = jQuery.parseJSON(xhr.responseText);
                    //alert(jsonValue.errorMessage);
                    showError(jsonValue.errorMessage);
                }
            })
        }
    );

    $("#search-input").keydown(function (e) {
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
        var inp = $("#group-name");
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
                alert(jsonValue.errorMessage);
                //showError(jsonValue.errorMessage);
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
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        var jsonValue = jQuery.parseJSON(xhr.responseText);
                        showError(jsonValue.errorMessage);
                        //alert(jsonValue.errorMessage);
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
        var loader = $("#loader");
        $(".trans-text-tr").remove();
        loader.append("<div class='loader' style='margin: auto'></div>")
        $.ajax({
            method: "POST",
            url: "/rest/text/translate/",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                text: text
            }),
            success: function (response) {
                loader.empty();
                var wTable = $("#result-table");
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
                loader.empty();
                var jsonValue = jQuery.parseJSON(xhr.responseText);
                showError(jsonValue.errorMessage);
                //alert(jsonValue.errorMessage);
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
                showError(jsonValue.errorMessage);
                //alert(jsonValue.errorMessage);
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
                    showError(jsonValue.errorMessage);
                    //alert(jsonValue.errorMessage);
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

function showError(mes) {
    var aldiv = $("#alert-div");
    aldiv.empty();
    aldiv.append("<div class='alert alert-danger alert-dismissable fade in' id='alrt'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>Ошибка!</strong> "+mes+"</div>");
    $(".close").click(function(){
        //$("#alrt").alert("close");
        $("#alrt").fadeOut(500);
    });
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
            showError(jsonValue.errorMessage);
            //alert(jsonValue.errorMessage);
        }
    });
}

function fillTable() {
    $(".tab-row").remove();
    $.ajax({
        method: "GET",
        url: "/rest/words/",
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            var count = response.length;
            for (var i = 0; i < count; i++) {
                addToTable(response[i]);
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var jsonValue = jQuery.parseJSON(xhr.responseText);
            showError(jsonValue.errorMessage);
            //alert(jsonValue.errorMessage);
        }
    });
}

function fillStat() {
    var progBar = $("#prog-bar");
    var progMBar = $("#prog-m-bar");
    var hword = $("#hard-word");
    var table = $("#train-table");

    $.ajax({
        method: "GET",
        url: "/rest/statistic/",
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            progBar.css("width",function(i){
                var all = response.allCount;
                var ler = response.learnedCount;
                var res = Math.floor((ler / all) * 100);
                var percent = res+'%';
                progBar.empty();
                progBar.append(percent);
                return percent;
            });
            progMBar.css("width",function(i){
                var percent = response.mistakesPercent+'%';
                progMBar.empty();
                progMBar.append(percent);
                return percent;
            });
            hword.empty();
            hword.append(response.difficultWord.eng + " - " + response.difficultWord.rus);
            $(".tr-row").remove();
            var trainings = response.trainings;
            for (var i = 0; i < trainings.length; i++) {
                var fragTrow = $("<tr class='tr-row'>").appendTo(table);
                var am = trainings[i].wordsAmount;
                var mis = trainings[i].mistakesAmount;
                $("<td>").appendTo(fragTrow).html(trainings[i].id);
                $("<td>").appendTo(fragTrow).html((new Date(trainings[i].trainingDate)).toLocaleString());
                $("<td>").appendTo(fragTrow).html(am);
                $("<td>").appendTo(fragTrow).html(mis);
                var per = Math.floor(((am-mis) / am) * 100);
                if(per > 89) fragTrow.toggleClass('success');
                if(per > 50 && per < 90) fragTrow.toggleClass('warning');
                if(per < 51 && per >= 0) fragTrow.toggleClass('danger');
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var jsonValue = jQuery.parseJSON(xhr.responseText);
            showError(jsonValue.errorMessage);
            //alert(jsonValue.errorMessage);
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
    $.ajax({
        method: "GET",
        url: "/rest/groups/",
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            var drop = $("#drop");
            var dropTr = $("#drop-train");
            drop.empty();
            dropTr.empty();
            for (var i = 0; i < response.length; i++) {
                drop.append("<li><a onclick='addToGroup(" + response[i].id + ")'>" + response[i].name + "</a></li>");
                dropTr.append("<li><a onclick='startTraining(" + response[i].id + ")'>" + response[i].name + "</a></li>");
            }
            drop.append("<li class='divider'></li>");
            drop.append("<li class='bg-success'><a onclick='addGroupOpenModal()'><span class='glyphicon glyphicon-plus'></span> Создать группу</a></li>")
        },
        error: function (xhr, ajaxOptions, thrownError) {
            var jsonValue = jQuery.parseJSON(xhr.responseText);
            showError(jsonValue.errorMessage);
            //alert(jsonValue.errorMessage);
        }
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
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    var jsonValue = jQuery.parseJSON(xhr.responseText);
                    showError(jsonValue.errorMessage);
                    //alert(jsonValue.errorMessage);
                }
            })
        }
    });
    fillTable();
}
