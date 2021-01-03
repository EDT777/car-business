<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算单明细管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="consumption"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>结算单明细</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单明细</h3>
                    </div>
                    <div class="box-body no-padding">
                        <div class="mailbox-controls">
                            <div class="btn-group">
                                <#-- 新增 -->
                                <button type="button" class="btn btn-default btn-sm checkbox-toggle btn-input"><i
                                            class="fa fa-plus"></i></button>
                                <#-- 删除 -->
                                <button type="button" class="btn btn-default btn-sm btn-batchDelete"><i
                                            class="fa fa-trash-o"></i></button>
                            </div>
                        </div>
                        <div class="table-responsive mailbox-messages">
                            <table class="table table-hover table-striped">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="allCb" onchange="checkChange(this)">全选</th>
                                    <th>业务大类</th>
                                    <th>业务小类</th>
                                    <th>结算类型</th>
                                    <th>消费金额(元)</th>
                                    <th>优惠金额(元)</th>
                                    <th>实收金额(元)</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#assign totalDiscountAmount = 0 />
                                <#assign totalAmount = 0 />

                                <#list items as i>
                                    <#if i.amount??>
                                        <#assign totalAmount += i.amount />
                                    </#if>
                                    <#if i.discountAmount??>
                                        <#assign totalDiscountAmount += i.discountAmount />
                                    </#if>
                                    <tr>
                                        <td><input type="checkbox" class="cb" name="ids" data-ids="${i.id}"></td>
                                        <td>${i.category.title}</td>
                                        <td>${i.categoryItem.title}</td>
                                        <td>${i.payType.title}</td>
                                        <td>${i.amount}</td>
                                        <td>${i.discountAmount}</td>
                                        <td>${i.payAmount}</td>
                                    </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th>总消费金额:${totalAmount}元</th>
                                    <th>总优惠金额:${totalDiscountAmount}元</th>
                                    <th>总实收金额:${totalAmount-totalDiscountAmount}元</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="box" style="border-top: none;">
                    <div class="box-header with-border">
                        <h3 class="box-title"><span class="glyphicon glyphicon-triangle-right"></span> 结算单信息</h3>
                    </div>
                    <form class="box-body" class="form-horizontal" id="editForm" action="/consumption/saveOrUpdate"
                          method="post">
                        <input type="hidden" name="id" value="${consumption.id}">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>客户名称：</label>
                                    <input type="text" name="customerName" <#if consumption.status!=0>readonly</#if>   value="${consumption.customerName}"
                                           class="form-control" placeholder="请输入客户名称">
                                </div>
                                <div class="form-group">
                                    <label>客户电话：</label>
                                    <input type="text" class="form-control"<#if consumption.status!=0>readonly</#if> name="customerTel"
                                           value="${consumption.customerTel}" placeholder="请输入客户电话">
                                </div>
                                <div class="form-group">
                                    <label>消费门店：</label>
                                    <select class="form-control" name="business.id" <#if consumption.status!=0>readonly</#if> >
                                        <option value="">请选择门店</option>
                                        <#list businesses as b>
                                            <option value="${b.id}">${b.name}</option>
                                        </#list>
                                    </select>
                                    <script>
                                        $("select[name='business.id']").val(${consumption.business.id})
                                    </script>
                                </div>
                                <div class="form-group">
                                    <label>进店时间：</label>
                                    <input type="text" class="form-control input-datetime"<#if consumption.status!=0>readonly</#if> name="checkinTime"
                                           value="${(consumption.checkinTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>离店时间：</label>
                                    <input type="text" class="form-control input-datetime"<#if consumption.status!=0>readonly</#if> name="checkoutTime"
                                           value="${(consumption.checkoutTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>车牌记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车牌记录"<#if consumption.status!=0>readonly</#if> name="carLicence"
                                           value="${consumption.carLicence}">
                                </div>
                                <div class="form-group">
                                    <label>车型记录：</label>
                                    <input type="text" class="form-control" placeholder="请输入车型记录" <#if consumption.status!=0>readonly</#if> name="carType"
                                           value="${consumption.carType}">
                                </div>

                                <div class="form-group">
                                    <label>结算单备注：</label>
                                    <textarea class="form-control" rows="4" name="info" <#if consumption.status!=0>readonly</#if>
                                              placeholder="请输入结算单备注">${consumption.info}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>结算单状态：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.statusName}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>总消费金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入总消费金额" name="totalAmount" <#if consumption.status!=0>readonly</#if>
                                           value="${(totalAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>优惠金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入优惠金额" name="discountAmount" <#if consumption.status!=0>readonly</#if>
                                           value="${(totalDiscountAmount?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>实收金额(元)：</label>
                                    <input type="text" class="form-control" placeholder="请输入实收金额" name="payAmount" <#if consumption.status!=0>readonly</#if>
                                           value="${((totalAmount-totalDiscountAmount)?c)!}">
                                </div>
                                <div class="form-group">
                                    <label>结算单流水号：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.cno}">
                                </div>
                                <div class="form-group">
                                    <label>关联预约单流水号：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${consumption.appointmentAno}">
                                </div>
                                <div class="form-group">
                                    <label>创建时间：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${(consumption.createTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>结算时间：</label>
                                    <input type="text" readonly class="form-control" readonly
                                           value="${(consumption.payTime?string('yyyy-MM-dd HH:mm:ss'))!}"/>
                                </div>
                                <div class="form-group">
                                    <label>结算人：</label>
                                    <input type="text" class="form-control" readonly value="${consumption.payee.name}">
                                </div>
                                <div class="form-group">
                                    <label>审核时间：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${(consumption.auditTime?string('yyyy-MM-dd HH:mm:ss'))!}">
                                </div>
                                <div class="form-group">
                                    <label>审核人：</label>
                                    <input type="text" class="form-control" readonly
                                           value="${consumption.auditor.name}">
                                </div>
                            </div>
                        </div>

                        <div class="pull-right">
                            <button type="submit" class="btn btn-primary btn-submit"><span
                                        class="glyphicon glyphicon-book"></span> 保存
                            </button>
                            <button type="button" class="btn btn-warning btn-consume" data-id="${consumption.id}">
                                <span class="glyphicon glyphicon-yen"></span> 结算
                            </button>
                            <button type="button" class="btn btn-success btn-audit" data-id="${consumption.id}"><span
                                        class="glyphicon glyphicon-flag"></span> 审核
                            </button>
                            <button type="button" class="btn btn-danger btn-delete" data-id="${consumption.id}"><span
                                        class="glyphicon glyphicon-trash"></span> 删除
                            </button>
                            <button type="button" class="btn btn-default backList" data-dismiss="modal">后退</button>
                        </div>

                    </form>
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
                <h4 class="modal-title" id="myModalLabel">新增结算明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="consumptionItemForm" action="/consumptionItem/saveOrUpdate" method="post">
                <#-- 结算单流水号 -->
                <input type="hidden" name="cno" value="${consumption.cno}">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务大类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="category" name="category.id">
                                <option value="">请选择业务大类</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">业务小类：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="categoryItem" name="categoryItem.id">
                                <option value="">请选择业务小类</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">结算类型：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="payType" name="payType.id">
                                <option value="">请选择结算类型</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">消费金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="amount"
                                   placeholder="请输入应收金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">优惠金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="discountAmount"
                                   placeholder="请输入优惠金额">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">实收金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="payAmount"
                                   placeholder="请输入实收金额">
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
    <#--    模态框 结算明细 保存-->
    $('#consumptionItemForm').ajaxForm(function (data) {
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

    //结算单 保存
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

    //弹出模态框
    $('.btn-input').click(function () {
        $('#editModal').modal('show');
    })
    //业务大类
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'business'}, function (data) {
        let selectStr = '';
        $.each(data, function (index, ele) {
            selectStr += '<option value="' + ele.id + '">' + ele.title + '</option>';
        })
        $('#category').append(selectStr);
    })

    //结算类型
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'account_type'}, function (data) {
        let selectStr = '';
        $.each(data, function (index, ele) {
            selectStr += '<option value="' + ele.id + '">' + ele.title + '</option>';
        })
        $('#payType').append(selectStr);
    })

    //    业务大类值改变事件  二级联动
    $('#category').change(function () {
        //获取当前选择的大类id
        let id = $(this).val();
        //    发ajax请求到后台,根据上级明细id查询当前明细
        $.get('/systemDictionaryItem/selectByParentId', {id: id}, function (data) {// 明细1,明细2
            //    查到数据 遍历循环 拼接option 设置到明细的下拉框中
            let str = '<option value="">请选择业务小类</option>';
            $.each(data, function (index, ele) {
                str += '<option value="' + ele.id + '">' + ele.title + '</option>'
            })
            $("#categoryItem").html(str);
        })
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
        var ids = [];
        $('[name=ids]:checked').each(function (index, ele) {
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
                $.post('/consumptionItem/batchDelete', {ids}, function (data) {
                    if (data.success) {
                        window.location.reload();
                    }
                });
            }
        })
    })

    //    实收金额自动生成
    $('#consumptionItemForm input[name=discountAmount]').keyup(function () {
        $('#consumptionItemForm input[name=payAmount]').val('');
        let amount = $('#consumptionItemForm input[name=amount]').val();
        let discountAmount = $('#consumptionItemForm input[name=discountAmount]').val();
        if (discountAmount!=null&&discountAmount!=''&&discountAmount!=0){
            $('#consumptionItemForm input[name=payAmount]').val(amount - discountAmount);
        }


    })
    $('#consumptionItemForm input[name=discountAmount]').change(function () {
        $('#consumptionItemForm input[name=payAmount]').val('');
        let amount = $('#consumptionItemForm input[name=amount]').val();
        let discountAmount = $('#consumptionItemForm input[name=discountAmount]').val();
        if (discountAmount!=null&&discountAmount!=''&&discountAmount!=0){
            $('#consumptionItemForm input[name=payAmount]').val(amount - discountAmount);
        }
    })
    //输入消费金额 绑定的事件
    $('#consumptionItemForm input[name=amount]').change(function () {
        $('#consumptionItemForm input[name=payAmount]').val('');
        let amount = $('#consumptionItemForm input[name=amount]').val();
        let discountAmount = $('#consumptionItemForm input[name=discountAmount]').val();
        if (discountAmount!=null&&discountAmount!=''&&discountAmount!=0){
            $('#consumptionItemForm input[name=payAmount]').val(amount - discountAmount);
        }else {
            $('#consumptionItemForm input[name=payAmount]').val(amount);
        }
    })
    $('#consumptionItemForm input[name=amount]').keyup(function () {
        $('#consumptionItemForm input[name=payAmount]').val('');
        let amount = $('#consumptionItemForm input[name=amount]').val();
        let discountAmount = $('#consumptionItemForm input[name=discountAmount]').val();
        if (discountAmount!=null&&discountAmount!=''&&discountAmount!=0){
            $('#consumptionItemForm input[name=payAmount]').val(amount - discountAmount);
        }else {
            $('#consumptionItemForm input[name=payAmount]').val(amount);
        }
    })


    //结算结算单
    $('.btn-consume').click(function () {
        let id = $(this).data('id');
        $.post('/consumption/saveConsume', {id: id}, function (data) {
            if (data.success) {
                console.log("123")
                Swal.fire({
                    html: '结算成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result) => {
                    if (result.value) {
                        window.location.reload();
                    }
                })
            }
        })
    })

    //审核结算单
    $('.btn-audit').click(function () {
        let id = $(this).data('id');
        Swal.fire({
            title: '审核结算单是否有误',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '归档',
            cancelButtonText: '废除',
        }).then((result) => {
            if (result.value){
                $.post('/consumption/saveAudit',{id:id},function (data) {
                        if (data.success){
                            Swal.fire({
                                html: '归档成功',
                                icon: 'success',
                                confirmButtonText: 'OK',
                            }).then((result) => {
                                updateStatus(id,2); //将结算单列为归档
                                window.location.reload();
                            })
                        }
                })
            }else {
                $.post('/consumption/saveAudit',{id:id},function (data) {
                    if (data.success){
                        Swal.fire({
                            html: '已列为坏账',
                            icon: 'success',
                            confirmButtonText: 'OK',
                        }).then((result) => {
                            updateStatus(id,3); //将结算单列为坏账
                            window.location.reload();
                        })
                    }
                })
            }
        })
    })

    //更改结算单状态 id status
    function updateStatus(id,status){
        $.post('/consumption/updateStatus',{id:id,status:status},function (data) {
            if (data.success){
                window.location.reload();
            }
        })
    }


    //返回页面
    $('.backList').click(function () {
            window.location.href="/consumption/list";
    })

    //删除结算单
    $('.btn-delete').click(function () {
        let id = $(this).data('id');
        $.post('/consumption/delete',{id:id},function (data) {
            if (data.success) {
                Swal.fire({
                    html: '删除成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result) => {
                    if (result.value) {
                        window.location.href="/consumption/list";
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
</script>
</body>
</html>
