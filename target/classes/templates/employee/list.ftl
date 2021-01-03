
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" name="keyword" placeholder="请输入姓名/邮箱" value="${qo.keyword}">
                        </div>
                        <div class="form-group">
                            <label for="dept"> 部门:</label>
                            <select class="form-control" id="dept" name="deptId">
                                <option value="">全部</option>
                                <#list departments as d>
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                            <script>
                                $('#dept').val(${qo.deptId})
                            </script>
                        </div>
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>查询</button>
                        <a href="/employee/input" class="btn btn-success btn-input">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                        <a href="#" class="btn btn-danger btn-batchDelete">
                            <span class="glyphicon glyphicon-trash"></span> 批量删除
                        </a>
                        <a href="/employee/exportXls?keyword=${qo.keyword}&deptId=${qo.deptId}" class="btn btn-warning ">
                            <span class="glyphicon glyphicon-download"></span> 导出
                        </a>
                        <a href="#" class="btn btn-warning btn-import ">
                            <span class="glyphicon glyphicon-upload"></span> 导入
                        </a>
                    </form>
                </div>
                <div class="box-body table-responsive ">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="allCb" onchange="checkChange(this)">全选</th>
                        <th>编号</th>
                        <th>用户名</th>
                        <th>真实姓名</th>
                        <th>密码</th>
                        <th>邮箱</th>
                        <th>年龄</th>
                        <th>权限</th>
                        <th>部门名称</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>

                    </thead>
                    <tbody>
                    <#list pageInfo.list as e>
                    <tr>
                        <td><input type="checkbox" class="cb" name="ids" data-ids="${e.id}"></td>
                        <td>${e_index+1}</td>
                        <td>${e.username}</td>
                        <td>${e.name}</td>
                        <td>${e.password}</td>
                        <td>${e.email}</td>
                        <td>${e.age}</td>
                        <td>
                            <#if e.admin>
                                有
                                <#else>无
                            </#if>
                        </td>
                        <td>${e.dept.name}</td>
                        <td><#if e.status>
                                正常
                            <#else>禁用
                            </#if></td>
                        <td>
                            <a href="/employee/input?id=${e.id}" class="btn btn-info btn-xs btn_redirect">
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a href="#" class="btn btn-danger btn-xs btn-delete" data-url="/employee/delete?id=${e.id}">
                                <span class="glyphicon glyphicon-trash"></span> 删除
                            </a>
                            <a href="/employee/status?id=${e.id}" class="btn
                             <#if e.status>
                                    btn-warning
                                <#else>btn-success
                                </#if>
                             btn-xs btn_redirect">
                                <span class="glyphicon glyphicon-fire"></span>
                                <#if e.status>
                                    禁用
                                <#else>恢复
                                </#if>
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                    <!--分页-->
                    <#include "/common/page.ftl">
                </div>

            </div>
        </section>
    </div>

<!--    导入数据模态框部分-->
    <div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">导入</h4>
                </div>
                <form class="form-horizontal" action="/employee/importXls" enctype="multipart/form-data" method="post" id="importForm">
                    <div class="modal-body">
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="name" class="col-sm-3 control-label"></label>
                            <div class="col-sm-6">
                                <!-- 文件上传框 -->
                                <input type="file" name="file" accept="application/vnd.ms-excel" >
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-6">
                                <a href="/xls/employee_import.xls" class="btn btn-success" >
                                    <span class="glyphicon glyphicon-download"></span> 下载模板
                                </a>
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

    <#include "/common/footer.ftl">
</div>
<script>
    //    导入按钮,点击后显示模态框
    $('.btn-import').click(function () {
        $('#importModal').modal('show');
    })
//提交导入的表单即excel表
    $('#importForm').ajaxForm(function (data) {
        if (data.success){
            Swal.fire({
                text: '导入成功',
                icon: 'success',
                confirmButtonText: 'OK',
            }).then((result)=>{
                if (result.value){
                    window.location.reload();
                }
            })
        }else {
            Swal.fire(
                data.msg,
                '',
                'error'
            )
        }
    })

//批量删除按钮实现
function checkChange(src) {
// 根据被点击的复选框，来决定下面这些爱好的复选框的选中状态
    var checked = $(src).prop('checked');
    checkAll(checked);
}

function checkAll(flag) {
    $('[name=ids]').prop('checked', flag);
// 点击全选按钮的时候，最上面复选框要选中
// 点击全不选按钮的时候，最上面复选框不要选中
    $('#allCb').prop('checked', flag);
}


$(function () {
// 点击所有爱好复选框，若爱好全选中，最上面复选框要选中，反之不要选中
    $('[name=ids]').click(function () {
        check();
    });
});
function check() {
    var total = true; // 统计
    $('[name=ids]').each(function (i, domEle) {
        var checked = $(domEle).prop('checked'); // 取每个爱好选中状态值
        total = total && checked; // 跟变量 total 与并设置存到变量 total 中
    });
// 当遍历结束的时候，total 值仍是 true, 那么代表所有爱好复选框是选中的
    $('#allCb').prop('checked', total);
}

//批量删除按钮实现
$(".btn-batchDelete").click(function () {
    var ids =[];
     $('[name=ids]:checked').each(function (index,ele) {
            ids.push($(ele).data('ids'));
     });

    Swal.fire({
        title: '是否确认批量删除?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '确认',
        cancelButtonText: '取消'
    }).then((result) => {
        if (result.value) {
            $.post('/employee/batchDelete',{ids},function (data) {
                if (data.msg){
                    window.location.href="/employee/list";
                }
            });
        }
    })
})


</script>

</body>
</html>
