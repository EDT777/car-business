<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告通知管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="notice"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>公告通知管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/notice/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">

                        <div class="form-group">
                            <label>创建日期查询：</label>
                            <input placeholder="请输入开始日期" type="text" class="form-control input-date" name="startTime"
                                   value="${(qo.startTime?string('yyyy-MM-dd'))!}"/> -
                            <input placeholder="请输入结束日期" type="text" class="form-control input-date" name="endTime"
                                   value="${(qo.endTime?string('yyyy-MM-dd'))!}"/>
                        </div>

                        <div class="form-group">
                            <label>公告级别：</label>
                            <select class="form-control" name="level">
                                <option value="">请选择级别</option>
                                <#list levels as l>
                                    <option value="${l.getValue()}">${l.getName()}</option>
                                </#list>
                            </select>
                            <script>
                                $('select[name=level]').val(${qo.level});
                            </script>
                        </div>
                        <div class="form-group">
                            <label>阅读状态：</label>
                            <select class="form-control" name="seeRead">
                                <option value="">全部</option>
                                <option value="0">未读</option>
                                <option value="1">已读</option>
                            </select>
                            <script>
                                $('select[name=seeRead]').val(${(qo.seeRead?string('1','0'))!});
                            </script>
                        </div>

                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                        <#if currentUser.admin==true>
                            <a href="#" class="btn btn-success btn-input">
                                <span class="glyphicon glyphicon-plus"></span> 添加
                            </a>
                        </#if>
                    </form>

                </div>
                <div class="box-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>公告标题</th>
                            <th>创建人</th>
                            <th>创建时间</th>
                            <th>公告级别</th>
                            <th>是否已读</th>
                            <#if currentUser.admin==true>
                                <th>已读情况</th>
                                <th>发布状态</th>
                            </#if>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as n>

                            <tr>
                                <td>${n_index+1}</td>
                                <td>${n.title}</td>
                                <td>${n.creator.name}</td>
                                <td>${(n.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                                <td>${n.getLevelName()}</td>
                                <td>
                                    <span style="color:${n.seeRead?string('green','red')}">${n.seeRead?string('已读','未读')}</span>
                                </td>
                                <#if currentUser.admin==true>
                                    <td>${n.readNumber} / ${totalUserNumber}</td>
                                    <td>${n.status?string('已发布','未发布')}</td>
                                </#if>
                                <td>
                                    <a class="btn btn-success btn-xs  btn-show" data-id="${n.id}" data-empId="${currentUser.id}">
                                        <span class="glyphicon glyphicon-phone-alt"></span>查看</a>
                                    <#if currentUser.admin==true>
                                        <a class="btn btn-primary btn-xs btn-input" data-json='${n.toJson()}'>
                                            <span class="glyphicon glyphicon-pencil"></span> 编辑
                                        </a>
                                        <a class="btn btn-xs btn-info btn-push" data-id="${n.id}">
                                            <span class="glyphicon glyphicon-share"></span> 发布</a>
                                        <a class="btn btn-xs btn-danger btn-remove" data-id="${n.id}">
                                            <span class="glyphicon glyphicon-remove"></span> 取消</a>
                                    </#if>
                                </td>
                            </tr>

                        </#list>
                        </tbody>
                    </table>
                    <#include "/common/page.ftl">
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>

<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="editForm" action="/notice/saveOrUpdate" method="post">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">公告标题：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="请输入公告标题" name="title"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">公告内容：</label>
                        <div class="col-sm-7">
                            <textarea type="text" class="form-control" rows="8" name="content"
                                      placeholder="请输入公告内容"></textarea>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">公告级别：</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="level" name="level">
                                <option value="">请选择级别</option>
                                <option value="2">紧急</option>
                                <option value="1">重要</option>
                                <option value="0">普通</option>
                            </select>
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
    <#--    显示模态框-->
    $(".btn-input").click(function () {
        $("#editModal").modal('show')
    })

    //  查看
    $(".btn-show").click(function () {
        let id = $(this).data('id');
        let empId =${currentUser.id};
        console.log(empId);
        window.location.href = '/notice/show?id='+ id+'&empId='+empId;
    })

    //    取消发布状态
    $('.btn-remove').click(function () {
        let id = $(this).data('id');
        $.post('/notice/removeStatus', {id: id}, function (data) {
            if (data.success) {
                Swal.fire({
                    html: '取消成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result) => {
                    if (result.value) {
                        window.location.reload();
                    }
                })
            } else {
                Swal.fire(
                    data.msg,
                    '',
                    'error'
                )
            }
        })
    })

    //    发布
    $('.btn-push').click(function () {
        let id = $(this).data('id');
        $.post('/notice/pushStatus', {id: id}, function (data) {
            if (data.success) {
                Swal.fire({
                    html: '发布成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result) => {
                    if (result.value) {
                        window.location.reload();
                    }
                })
            } else {
                Swal.fire(
                    data.msg,
                    '',
                    'error'
                )
            }
        })
    })

    $('.btn-input').click(function () {
        $('#editForm input,#editForm select').val('');
        $('#editForm textarea').html('');
        let json = $(this).data('json');
        if (json) {
            $('input[name=id]').val(json.id);
            $('input[name=title]').val(json.title);
            $('textarea[name=content]').html(json.content);
            $('select[name=level]').val(json.level);
        }
        $('#editModal').modal('show');
    })

    //保存
    $('#editForm').ajaxForm(function (data) {
        if (data.success) {
            Swal.fire({
                html: '保存成功',
                icon: 'success',
                confirmButtonText: 'OK',
            }).then((result) => {
                if (result.value) {
                    window.location.reload();
                }
            })
        } else {
            Swal.fire(
                data.msg,
                '',
                'error'
            )
        }
    })
</script>

</body>
</html>
