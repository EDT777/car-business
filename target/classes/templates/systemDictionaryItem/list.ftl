<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典明细管理</title>
    <#-- 使用相对当前模板文件的路径 再去找另一个模板文件 -->
    <#include "/common/link.ftl">


</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--定义一个变量  菜单回显-->
    <#assign currentMenu="systemDictionaryItem"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>字典明细管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="row" style="margin:20px">
                    <div class="col-xs-2">
                        <div class="panel panel-default">
                            <div class="panel-heading">字典目录</div>
                            <div class="panel-body">
                                <div class="list-group" id="dic">
                                    <#list dictionaries as ds>
                                        <a data-id="${ds.id}" href="/systemDictionaryItem/list?typeId=${ds.id}" class="list-group-item">${ds.title}</a>
                                    </#list>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-10">
                        <!--高级查询--->
                        <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list" method="post">
                            <input type="hidden" name="currentPage" id="currentPage" value="1">
                            <input type="hidden" name="typeId"  value="${qo.typeId}">
                            <input type="hidden" name="parentId"  value="${qo.parentId}">
                            <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                                <span class="glyphicon glyphicon-plus"></span> 添加
                            </a>
                        </form>
                        <!--编写内容-->
                        <div class="box-body table-responsive no-padding ">
                            <table class="table table-hover table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>字典明细标题</th>
                                    <th>字典明细序号</th>
                                    <th>上级明细</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list pageInfo.list as item>
                                    <tr>
                                        <td>${item_index}</td>
                                        <td>
                                            <a href="/systemDictionaryItem/list?parentId=${item.id}">${item.title}</a>
                                        </td>
                                        <td>${item.sequence}</td>
                                        <td>${item.parent.title!"无"}</td>
                                        <td>
                                            <a href="#" class="btn btn-info btn-xs btn-input" data-json='${item.toJson()}' >
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
                </div>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl" >
</div>




<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">字典目录：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="type.id" >
                                <option value="">请选择所属目录</option>
                                <#list dictionaries as ds>
                                    <option value="${ds.id}">${ds.title}</option>
                                </#list>

                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">上级明细目录：</label>
                        <div class="col-sm-6">
                            <select class="form-control"  id="typeSelect">
                                <option value="">无</option>
                                <#list dictionaries as ds>
                                    <option value="${ds.id}">${ds.title}</option>
                                </#list>

                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label  class="col-sm-3 control-label">上级明细：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="parent.id" id="parentSelect" >
                                <option value="">无</option>
                                <#--                                   <#list  as >-->
                                <#--                                       -->
                                <#--                                   </#list>-->
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label  class="col-sm-3 control-label">明细标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title"
                                   placeholder="请输入字典明细编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-3 control-label">明细序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence"
                                   placeholder="请输入字典明细序号">
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
    <#if qo.typeId?? >
    $("a[data-id=${qo.typeId}]").addClass('active');//设置高亮代码
    </#if>


    <#--    添加/编辑-->
    $('.btn-input').click(function () {
        $('#editForm input,#editForm select').val('');
        let json = $(this).data('json');
        if (json){
            $('#editForm input[name=id]').val(json.id);
            $('#editForm input[name=title]').val(json.title);
            $('#editForm input[name=sequence]').val(json.sequence);
            $("#editForm select[name='type.id']").val(json.typeId);

            //上级明细
            let str ='<option value="'+json.parentId+'">'+json.parentTitle+'</option>';
            $('#parentSelect').html(str);
            $("#editForm select[name='parent.id']").val(json.parentId);
        }

        $('#editModal').modal('show');
    })

    $('#editForm').ajaxForm(function (data) {
        if (data.success){
            Swal.fire({
                html: '保存成功',
                icon: 'success',
                confirmButtonText: 'OK',
            }).then((result) => {
                if (result.value) {
                    window.location.reload();
                }
            })
        }
    })

    //    字典目录值改变事件
    $('#typeSelect').change(function () {
        //获取当前选择的目录id
        let id = $(this).val();
        //    发ajax请求到后台,根据目录查询明细
        $.get('/systemDictionaryItem/selectByTypeId',{id:id},function (data) {// 明细1,明细2
            //    查到数据 遍历循环 拼接option 设置到明细的下拉框中
            let str = '<option value="">无</option>';
            $.each(data,function (index,ele) {
                str +='<option value="'+ele.id+'">'+ele.title+'</option>'
            })
            $("#parentSelect").html(str)
        })
    })
</script>
</body>
</html>
