<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id', {
        uploadJson:"${path}/editor/upload",
        filePostName:"photo",
        allowFileManager:true,
        fileManagerJson:"${path}/editor/queryAllPhoto",
        afterBlur:function (){  //编辑器失去焦点(blur)时执行的回调函数。
            this.sync();  //将编辑器中的内容同步到表单
        }
    });
</script>

<script>
    $(function(){

        /*jqgrid表格*/
        $("#atctable").jqGrid({
            url:"${path}/article/showBypage",
            editurl:"${path}/article/edit",
            datatype : "json",
            autowidth:true,  //自适应父容器
            height:'auto',
            rownumbers:true,
            styleUI:'Bootstrap', //使用BootStrap风格的样式
            rowNum : 2,
            rowList : [ 2,5,10, 20, 30 ],
            pager : '#atcpager',
            viewrecords:true,  //显示总条数
            colNames : [ 'Id', '名字', '内容', '上传时间','所属上师' ],
            colModel : [
                {name : 'id',width : 55,hidden:true},
                {name : 'title',editable:true,width : 90},
                {name : 'content',editable:true,width : 100,align : "center"},
                {name : 'up_date',width : 80,align : "right"},
                {name : 'guruid',editable:true,width : 80,align : "right"}
            ]
        });

        //增删改查操作
        $("#atctable").jqGrid('navGrid', '#atcpager',
            {edit : false,add : false,add : false,search:false,del : true,edittext:"编辑"}
        );
    });

    /*点击展示详情*/
    $("#btn1").click(function(){
        //展示模态框
        $("#modals").modal("show");
    });

    /*点击添加文章*/
    $("#btn2").click(function(){
        //展示模态框
        $("#modals").modal("show");
    });

    /*点击添加按钮操作*/
    function addArticle(){
        //关闭模态框
        $('#modals').modal('hide');
    }

    /*点击删除文章*/
    $("#btn3").click(function(){
        alert("点击删除文章");
    });

    /*修改的提交按钮*/
    function updateArticle(rowId){
        //关闭模态框
        $('#modals').modal('hide');

    }

</script>

<%--初始化一个面板--%>
<div class="panel panel-danger">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#"><b>文章信息</b></a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" id="btn1" class="btn btn-info" >查看文章</button>&emsp;
        <button type="button" id="btn2" class="btn btn-success" >添加文章</button>&emsp;
        <button type="button" id="btn3" class="btn btn-warning" >删除文章</button>&emsp;
    </div>

    <%--初始化表单--%>
    <table id="atctable" />

    <%--分页工具栏--%>
    <div id="atcpager" />

</div>
<%--初始化模态框--%>
<div id="modals" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <p>One fine body&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
