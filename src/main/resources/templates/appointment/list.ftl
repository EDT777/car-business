<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预约单管理</title>
    <#include "/common/link.ftl">
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="appointment"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>预约单管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 20px 0px 0px 10px">
                    <form class="form-inline" id="searchForm" action="/appointment/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label>预约单流水号</label>
                            <input type="text" class="form-control" placeholder="请输入预约单流水号" name="ano" value="${qo.ano}">
                        </div>
                        <div class="form-group">
                            <label>预约业务大类</label>
                            <select class="form-control" id="qoCategorySelect" name="category.id" >
                                <option value="">请选择业务大类</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>预约单状态</label>
                            <select class="form-control" id="status" name="status">
                                <option value="">全部</option>
                                <#list enums as e>
                                    <option value="${e.getValue()}">${e.getName()}</option>
                                </#list>
                            </select>
                        </div>
                        <script>
                            $('#status').val(${qo.status});
                        </script>
                        <div class="form-group">
                            <label>门店查询</label>
                            <select class="form-control" id="businessId" name="business.id">
                                <option value="">请选择门店</option>
                                <#list businesses as b>
                                    <option value="${b.id}">${b.name}</option>
                                </#list>
                            </select>
                        </div>
                        <script>
                            $('#businessId').val(${qo.business.id})
                        </script>
                        <div class="form-group">
                            <label>客户名称</label>
                            <input type="text" class="form-control" placeholder="请输入客户名称" name="contactName" value="${qo.contactName}">
                        </div>

                        <div class="form-group">
                            <label>客户手机号</label>
                            <input type="text" class="form-control" placeholder="请输入客户手机号" name="contactTel" value="${qo.contactTel}">
                        </div>

                        <br/>
                        <br/>

                        <div class="form-group">
                            <label>预约时间查询：</label>
                            <input placeholder="请输入开始时间" type="text" class="form-control input-datetime-noss" name="startTime" value="${(qo.startTime?string('yyyy-MM-dd HH:mm'))!}"/> -
                            <input placeholder="请输入结束时间" type="text" class="form-control input-datetime-noss" name="endTime" value="${(qo.endTime?string('yyyy-MM-dd HH:mm'))!}" />
                        </div>

                        <button type="submit"  class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>查询</button>


                            <a href="#" class="btn btn-success btn-input">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                    </form>

                </div>
                <div class="box-body table-responsive ">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>流水号</th>
                            <th>业务大类</th>
                            <th>预约说明</th>
                            <th>预约时间</th>
                            <th>客户名称</th>
                            <th>联系方式</th>
                            <th>预约门店</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as a>
                            <tr>
                                <td>${a_index+1}</td>
                                <td>${a.ano}</td>
                                <td>${a.category.title}</td>
                                <td>${a.info}</td>
                                <td>${(a.appointmentTime?string('yyyy-MM-dd HH:mm'))!}</td>
                                <td>${a.contactName}</td>
                                <td>${a.contactTel}</td>
                                <td>${a.business.name}</td>
                                <td>${a.getStatusName()}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${a.toJson()}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a class="btn btn-xs btn-primary btn-status" data-id="${a.id}" data-status="1">
                                        <span class="glyphicon glyphicon-phone-alt"></span> 确认预约</a>
                                    <a class="btn btn-xs btn-danger btn-status" data-id="${a.id}" data-status="4">
                                        <span class="glyphicon glyphicon-remove" ></span> 取消预约</a>
                                    <a href="#" class="btn btn-success btn-xs btn-consume btn-consume" data-id="${a.id}" data-ano="${a.ano}" >
                                        <span class="glyphicon glyphicon-shopping-cart "></span> 确认到店
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
    <#include "/common/footer.ftl">
</div>


<#-- 文件上传模态框 -->
<!--模态框-->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="editForm" action="/appointment/saveOrUpdate" method="post">
                <div class="modal-body">
                    <input type="hidden" name="id" >
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约门店：</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="business.id">
                                <option value="">请选择预约门店</option>
                                <#list businesses as b>
                                    <option value="${b.id}">${b.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约时间：</label>
                        <div class="col-sm-7">
                            <input type="text" name="appointmentTime" class="form-control input-datetime-noss" placeholder="请输入预约时间"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="categorySelect" name="category.id">
                                <option value="">请选择业务大类</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系人：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactName"
                                   placeholder="请输入联系人">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">联系电话：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="contactTel"
                                   placeholder="请输入联系电话">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">预约说明：</label>
                        <div class="col-sm-7">
                            <textarea type="text" class="form-control" name="info"
                                      placeholder="请输入预约说明"></textarea>
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
<#--点击确认到店 若消费了 则跳转至对应结算单页面-->
    $('.btn-consume').click(function () {
        let  id = $(this).data('id');
        let ano = $(this).data('ano');
        Swal.fire({
            title: '是否确认客户已经到店',
            icon: 'warning',
            showCancelButton:true,
            confirmButtonText: '是',
            cancelButtonText: '否',
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '客户是否有消费',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '有消费',
                    cancelButtonText: '无消费',
                }).then((result) => {
                    if (result.value){
                    //    有消费 ->生成一个结算单(关联预约单流水号),预约单的状态转为消费中
                        $.get('/consumption/save',{appointmentAno:ano},function (data) {
                            if (data.success){
                                window.location.href="/consumption/input?id="+data.data
                            }else {
                                Swal.fire(
                                    data.msg,
                                    '',
                                    'error'
                                )
                            }
                        })
                    }else {
                        //无消费 -> 预约单的状态为归档
                        updateStatus(id,3);
                    }
                })
            }
        })
    })

function updateStatus(id,status) {
    $.get('/appointment/updateStatus',{id:id,status:status},function (data) {
        if (data.success){
            Swal.fire({
                html: '更新成功',
                icon: 'success',
                confirmButtonText: 'OK',
            }).then((result) => {
                if (result.value) {
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
}

<#--确认/取消-->
    $('.btn-status').click(function () {
            let id =$(this).data('id');
            let status = $(this).data('status');
        updateStatus(id,status);
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
        }else {
            Swal.fire(
                data.msg,
                '',
                'error'
            )
        }
    })
    <#--    查询预约业务-->
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'business'}, function (data) {
        let str = '';
        $.each(data, function (index, ele) {
            str += '<option value="'+ele.id+'">' + ele.title + '</option>';
        })
        $('#categorySelect').append(str);
        $('#qoCategorySelect').append(str);
        $('#qoCategorySelect').val(${qo.category.id});
    })



    $('.btn-input').click(function () {
        //    清空表单数据
        $('#editForm input,#editForm select').val('');
        $('#editForm textarea').html('');
        //   获取 从当前按钮上绑定的数据
        let json = $(this).data('json');
        if (json) {
            $('#editForm input[name=id]').val(json.id);
            $('#editForm select[name="business.id"]').val(json.businessId);
            $('#editForm select[name="category.id"]').val(json.categoryId);
            console.log(json.categoryId);
            $('#editForm input[name=contactName]').val(json.contactName);
            $('#editForm textarea[name=info]').html(json.info);
            $('#editForm input[name=contactTel]').val(json.contactTel);
            $('#editForm input[name=appointmentTime]').val(json.appointmentTime);
        }
        //显示模态框
        $('#editModal').modal('show');
    });
</script>
</body>
</html>
