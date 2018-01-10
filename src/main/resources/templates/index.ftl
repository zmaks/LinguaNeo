<#include "/part/header.ftl">

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div id="alert-div" style="margin-top: 20px">
        </div>

		<ul class="nav nav-tabs">
		  <li class="active" id="dictId"><a data-toggle="tab" href="#dict">Словарь</a></li>
		  <li><a data-toggle="tab" href="#menu1">Перевод текста</a></li>
		  <li><a data-toggle="tab" href="#menu2">Тренировка</a></li>
		  <li><a data-toggle="tab" href="#menu3" onclick="fillStat()" >Статистика</a></li>
		</ul>

        <!-- Словарь -->

		<div class="tab-content">
		  <div id="dict" class="tab-pane fade in active">
			<div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">
                  <div class="row">
                      <div class="col-md-12">
                          <div class="input-group">
                              <input id="search-input" onkeyup="searchWords()" type="text" class="form-control" placeholder="Введите слово...">
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
              <h6 class="bg-success"> Выученные слова помечены зеленым цветом</h6>
              <h6 class="bg-warning"> Изучающиеся слова помечены желтым цветом</h6>
              <h6 class="bg-danger"> Слова, в которых Вы делаете ошибки, помечены красным цветом.</h6>
		  </div>


            <!-- Перевод текста -->

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
                            <div id="loader"    >
                            </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-md-12" id="tb-footer">
                      </div>
                  </div>
              </div>
		  </div>


            <!-- Тернировки -->

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

            <!-- Статистика -->

            <div id="menu3" class="tab-pane fade">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Ваш прогресс</h3>
                            <h6 style="color: grey">Процент изученных слов. Изученным является слово, которое Вы правильно перевели 3 и более раз.</h6>

                            <div class="progress">
                                <div id="prog-bar" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:1%">
                                </div>
                            </div>
                            <h3>Процент ошибок в тренировках:</h3>
                            <div class="progress">
                                <div id="prog-m-bar" class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:1%">
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

<!-- Перевод слова (окно) -->
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

<!-- Тренировка (окно) -->
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

<!-- Создание группы (окно) -->
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
