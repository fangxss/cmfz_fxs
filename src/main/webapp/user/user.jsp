<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />


<script>
    $(function(){
        alert(2222);
        $("#ustable").jqGrid({
            url : "${path}/user/showBypage",
            editurl:"${path}/user/edit",
            datatype : "json",
            rowNum : 3,
            autowidth:true,
            height:"auto",
            styleUI:"Bootstrap",
            rowList : [ 5,10, 20, 30 ],
            pager : '#uspager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'Id', '名字', '密码', '头像', '法名','性别','状态','手机号','注册时间' ],
            colModel : [
                {name : 'id',width : 55,hidden:true},
                {name : 'name',width : 90},
                {name : 'password',width : 90},
                {name : 'pic_img', id:"img_path",width : 100,align : "center",
                    formatter:function(cellvalue){
                        return "<img src='${path}/upload/userimg/"+cellvalue+"' style='width:180px;height:80px' />";
                    },// 返回图片。
                },
                {name : 'ahama',width : 80,align : "right"},
                {name : 'sex',width : 80,align : "right"},
                {name : 'status',width : 80,align : "center",
                    formatter:function(cellvalue,option,row){
                        if(cellvalue=="1"){
                            //展示状态
                            return "<button class='btn btn-success' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >可用</button>";
                        }else{
                            //不展示状态
                            return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >冻结</button>";
                        }
                    },
                },
                {name : 'phone',width : 80,align : "center"},
                {name : 'reg_date',width : 80,align : "center"}
            ]
        });
        /*增删改查操作*/
        $("#ustable").jqGrid('navGrid', '#uspager',
            {edit : true,add : false,del : false,search:false,addtext:"添加",edittext:"编辑"}
        );
    });

    //点击修改
    function change(id,value){

        if(value==1){

            $.ajax({
                url:"${path}/user/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"2"},
                success:function(data){
                    //页面的刷新
                    $("#ustable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();

                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }else{
            $.ajax({
                url:"${path}/user/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"1"},
                success:function(data){
                    //页面的刷新
                    $("#ustable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();
                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }

    }
</script>


<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户管理</a></li>
    </ul>

    <div id="show" class="alert alert-warning alert-dismissible" role="alert"  style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>
    <%--初始化表单--%>
    <table id="ustable" />

    <%--定义分页工具栏--%>
    <div id="uspager"></div>

</div>