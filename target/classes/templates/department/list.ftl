<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <#-- 使用相对当前模板文件的路径 再去找另一个模板文件 -->
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--定义一个变量  用于菜单回显-->
    <#assign currentMenu="department"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive ">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list pageInfo.list as d>
                        <tr>
                            <td>${d_index+1}</td>
                            <td>${d.name}</td>
                            <td>${d.sn}</td>
                            <td>
                                <a href="#" class="btn btn-info btn-xs btn-input " data-json='${d.toJson()}'>
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                                <a class="btn btn-danger btn-xs btn-delete" data-url="/department/delete?id=${d.id}">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                        </#list>

                    </table>
                    <!--分页-->
                    <#include "/common/page.ftl" >
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>

<script>
    // $(".btn-delete").click(function () {
    //     let url = $(this).data('url');
    //     Swal.fire({
    //         title: '是否确认删除?',
    //         icon: 'warning',
    //         showCancelButton: true,
    //         confirmButtonText: '确认',
    //         cancelButtonText: '取消'
    //     }).then((result) => {
    //         if (result.value) {
    //             window.location.href=url;
    //         }
    //     })
    // })
    //添加和编辑按钮
    $(".btn-input").click(function () {
    //    首先清空表单数据
        $("#editForm input").val('');

    //从当前按钮上获取绑定的数据
    let json = $(this).data('json');
    if (json){
        console.log(json);
        //回显表单数据
        $("input[name=id]").val(json.id);
        $("input[name=name]").val(json.name);
        $("input[name=sn]").val(json.sn);
    }

    //找到要显示的模态框
        $('#myModal').modal('show')
    });

</script>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>

            <form class="form-horizontal" action="/department/saveOrUpdate" method="post" id="editForm">
            <input type="hidden" name="id"/>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">部门名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" placeholder="部门名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">部门编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="sn" id="sn" placeholder="部门编号">
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary">保存</button>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
