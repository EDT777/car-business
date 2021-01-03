<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典目录管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">

    <#assign currentMenu="systemDictionary"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>字典目录管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/systemDictionary/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>字典目录标题</th>
                                <th>字典目录编码</th>
                                <th>字典目录简介</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as d>
                            <tr>
                                <td>${d_index}</td>
                                <td>${d.title}</td>
                                <td>${d.sn}</td>
                                <td>${d.intro}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${d.toJson()}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#include "/common/page.ftl" >
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>




<!-- Modal模态框 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                        <input type="hidden" name="id">
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="name" class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="title"
                                       placeholder="请输入字典目录标题">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="sn" class="col-sm-3 control-label">编码：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="sn"
                                       placeholder="请输入字典目录编码">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="sn" class="col-sm-3 control-label">简介：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="intro"
                                       placeholder="请输入字典目录简介">
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary btn-submit">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
<#--    添加/编辑-->
    $('.btn-input').click(function () {
        $('#editForm input').val('');
        let json = $(this).data('json');
        if (json){
            $('#editForm input[name=id]').val(json.id);
            $('#editForm input[name=sn]').val(json.sn);
            $('#editForm input[name=title]').val(json.title);
            $('#editForm input[name=intro]').val(json.intro);
        }

            $('#editModal').modal('show');
    })
</script>
</body>
</html>
