<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="permission"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box" >
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="javascript:;" class="btn btn-success btn-reload" style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span>  重新加载
                    </a>
                </form>
                <div class="box-body table-responsive ">
                <table class="table table-hover table-bordered table-striped" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pageInfo.list as p>
                    <tr>
                        <td>${p_index+1}</td>
                        <td>${p.name}</td>
                        <td>${p.expression}</td>
                        <td>
                            <a class="btn btn-danger btn-xs btn-delete" >
                                <span class="glyphicon glyphicon-trash"></span> 禁用
                            </a>
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
<script>
    $(".btn-reload").click(function () {
        Swal.fire({
            html: '是否确认重新加载?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '确认',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.value) {
                $.post('/permission/reload',function (data) {
                    if (data.success){
                       Swal.fire(
                           '加载成功',
                           '',
                           'success'
                       )
                        window.location.reload();//刷新当前页面
                    }else {
                        Swal.fire(
                            '加载失败',
                            '',
                            'error'
                        )
                    }
                });
            }
        })
    })
</script>
</body>
</html>
