<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>工资管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">

    <#assign currentMenu="salary"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>工资管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/salary/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" name="keyword" placeholder="请输入姓名"
                               value="${qo.keyword}">
                    </div>
                    <div class="form-group">
                        <label> 发放方式:</label>
                        <select class="form-control" name="payout.id" id="payoutSelect">
                            <option value="">全部</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>工资范围查询：</label>
                        <input placeholder="请输入最低工资" type="text" class="form-control " name="minMoney"
                               value="${(qo.minMoney?c)!}"/> -
                        <input placeholder="请输入最高工资" type="text" class="form-control " name="maxMoney"
                               value="${(qo.maxMoney?c)!}"/>
                    </div>
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询
                    </button>
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
                            <th>年份</th>
                            <th>月份</th>
                            <th>员工</th>
                            <th>工资</th>
                            <th>发放方式</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as p>
                            <tr>
                                <td>${p_index+1}</td>
                                <td>${p.year?c}</td>
                                <td>${p.month}</td>
                                <td>${p.employee.name}</td>
                                <td>${p.money}</td>
                                <td>${p.payout.title}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${p.toJson()}'>
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


<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <form class="form-horizontal" action="/salary/saveOrUpdate" method="post" id="editForm">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">年份：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control " name="year"
                                   placeholder="请输入年份">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">月份：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control " name="month"
                                   placeholder="请输入月份">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">员工：</label>
                        <div class="col-sm-6">
                            <select type="text" class="form-control" name="employee.id">
                                <option value="">请选择员工</option>
                                <#list employees as e>
                                    <option value="${e.id}">${e.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">工资：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="money"
                                   placeholder="请输入工资">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">发放方式：</label>
                        <div class="col-sm-6">
                            <select type="text" class="form-control" name="payout.id">
                                <option value="">请选择发放方式</option>
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
    <#--    弹出模态框-->
    $('.btn-input').click(function () {
        let json = $(this).data('json');
        if (json) {
            $('#editModal input[name=id]').val(json.id);
            $('#editModal input[name=year]').val(json.year);
            $('#editModal input[name=month]').val(json.month);
            $('#editModal select[name="employee.id"]').val(json.employeeId);
            $('#editModal input[name=money]').val(json.money);
            $('#editModal select[name="payout.id"]').val(json.payoutId);
        }
        $('#editModal').modal('show');
    })

    //获取支付方式下拉框数据
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'pay_type'}, function (data) {
        let str = '';
        $.each(data, function (index, ele) {
            str += '<option value="' + ele.id + '">' + ele.title + '</option>';
        })
        $('#editForm select[name="payout.id"]').append(str);
        $('#searchForm select[name="payout.id"]').append(str);
        $('#payoutSelect').val(${qo.payout.id});
    })

    //将表单升级为ajax提交
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
