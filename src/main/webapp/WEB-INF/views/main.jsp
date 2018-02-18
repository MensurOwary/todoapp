<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>To Do List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/main.css">
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-aqua">
                <div class="box-header ui-sortable-handle" style="cursor: move;">
                    <i class="ion ion-clipboard"></i>
                    <h3 class="box-title">To Do List</h3>
                </div>

                <div class="box-body" id="box-body">
                    <ul class="todo-list ui-sortable">
                        <c:forEach items="${list}" var="todo">
                        <li>
                            <span class="handle ui-sortable-handle">
                              <i class="fa fa-ellipsis-v"></i>
                              <i class="fa fa-ellipsis-v"></i>
                            </span>
                            <span class="text"><i class="fa fa-thumb-tack"></i></span>

                            <c:choose>
                                <c:when test="${todo.done}">
                                    <span class="text title" style="text-decoration: line-through">${todo.title}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="text title">${todo.title}</span>
                                </c:otherwise>
                            </c:choose>


                            <div class="tools">
                                <i class="fa fa-edit update"></i>
                                <i class="fa fa-trash-o delete"></i>
                            </div>

                            <br>
                            <span class="text desc"><em>${todo.description}</em></span>

                            <span class="text id" style="display: none">${todo.id}</span>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i>  ${todo.timePassed}</small>
                        </li>
                        </c:forEach>

                    </ul>
                </div>

                <div class="box-footer clearfix no-border">
                    <button type="button" class="btn btn-default pull-right" id="addBtn"><i class="fa fa-plus"></i> Add item</button>
                </div>

                <!-- The Modal -->
                <div id="myModal" class="modal">

                    <!-- Modal content -->
                    <div class="modal-content">
                        <span class="close">&times;</span>

                        <form action="" method="post" id="form">
                            <div class="form-group">
                                <input type="hidden" class="form-control" name="id" id="id" value="">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="title" id="title" placeholder="Title" required>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" rows="3" name="desc" id="desc" placeholder="Little bit of info here..."></textarea>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default">Submit</button>
                            </div>
                        </form>

                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="well">
                <p>
                This file has been downloaded from bootdey.com and @bootdey on twitter <br>
                All snippets are MIT license http://bootdey.com/license <br>

                The codes are free, but we require linking to our web site. <br>
                Why to Link? <br>
                A true story: one girl didn't set a link and had no decent date for two years, and another guy set a link and got a top ranking in Google!
                    <br>
                Where to Put the Link? <br>
                home, about, credits... or in a good page that you want <br>
                THANK YOU MY FRIEND! <br>
                </p>
            </div>
        </div>
    </div>
</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="resources/js/main.js" charset="utf-8"></script>

</body>
</html>
