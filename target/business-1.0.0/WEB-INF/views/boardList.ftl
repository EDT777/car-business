<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小豪互联网门店管理平台</title>
    <#include "/common/link.ftl"/>
    <link rel="stylesheet" href="/css/index_css.css">
</head>
<body>
<div class="nav">
    <div class="nav-div">
        <div class="row justify-content-between" >
            <a href="/index">
            <div class="col-md-4 border-right" style="padding: 0px;" >
                <h4>${business.name}</h4>
                <h5 style="color:#434343;font-size: 18px">连锁运营顾问 / 高级服务专家</h5>
            </div>
            </a>
            <div class="col-md-3 category" style="padding-left: 30px">
            </div>
            <div class="col-md-1" style="text-align: center;padding: 20px;">
                <button class="btn btn-primary-full" type="button" id="btn-appointment">
                    马上预约
                </button>
                <br/>
                <br/>
                <button class="btn btn btn-info" type="button" id="btn-board">
                    留言板
                </button>
            </div>
            <div class="col-md-4 tel"  style="padding: 15px;">
                <h2 style="vertical-align:middle;"><i class="fa fa-phone"></i></h2>
                <h5 style="color:#434343; ">全国免费热线:</h5>
                <h2 >020-77777</h2>
            </div>
        </div>
    </div>

</div>

<h1 style="text-align: center">
    小豪留言详情页
</h1>
<div class="container">
    <div class="box box-widget">
        <div class="box-header with-border">

            <div class="user-block">
                <img class="img-circle" src="/js/adminlte/img/user2-160x160.jpg" alt="User Image">
                <span class="username"><a href="#">${messageBoard.nickname}</a></span>
                <span class="description">${(messageBoard.createTime?string('yyyy-MM-dd HH:mm:ss'))!}<span>咨询类型：</span><span>${messageBoard.category.title}</span>-<span>${messageBoard.categoryItem.title}</span></span>
            </div>
        </div>
        <div class="box-body">
            <p>${messageBoard.content}</p>
            <span class="pull-right text-muted">${messageReply?size}条回复</span>
            <#if USER_IN_SESSION??>
            <button class="btn btn btn-info" type="button" id="reply" data-id="${messageBoard.id}">
                回复
            </button>
            </#if>
        </div>
        <div class="box-footer box-comments">
            <#list messageReply as mr>
            <div class="box-comment">
                <img class="img-circle img-sm" src="/js/adminlte/img/a.jpg" alt="User Image">
                <div class="comment-text">
                    <span class="username">${mr.replyUser.name}<span class="text-muted pull-right">${(mr.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</span></span>
                    <p>${mr.content}</p>
                </div>
            </div>
            </#list>
        </div>
    </div>

</div>

<#--预约模态框-->
<div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog " style="max-width: 380px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >请输入预约信息</h4>
            </div>
            <form id="appointmentForm" method="post" action="/appointment/save">
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                        <input  class="form-control" placeholder="请输入您的姓名" name="contactName">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                        <input  class="form-control" placeholder="请输入您的电话" name="contactTel">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-home"></i></span>
                        <select class="form-control" placeholder="请选择预约门店" name="business.id">
                            <option value="">请选择预约门店</option>
                            <#list businesses as bs>
                                <option value="${bs.id}">${bs.name}</option>
                            </#list>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                        <select class="form-control"  placeholder="请选择预约业务" id="categorySelect" name="category.id">
                            <option value="">请选择预约业务</option>

                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input  class="form-control input-datetime-noss"  placeholder="请选择预约时间" name="appointmentTime">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                        <textarea  class="form-control" placeholder="备注说明" name="info"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary-full">确定预约</button>
                </div>
            </form>
        </div>
    </div>
</div>
<#--评论回复模态框-->
<!-- Modal -->
<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">回复留言信息</h4>
            </div>

            <form class="form-horizontal" action="/messageBoard/reply" method="post" id="replyForm">
                <input type="hidden" name="id"/>
                <input type="hidden" name="boardId" value="${messageBoard.id}"/>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">回复留言</label>
                        <div class="col-sm-10">
                            <textarea name="content"></textarea>
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
<script>
<#--回复留言-->
$('#reply').click(function () {
    $('#replyForm textarea').html('');
    $('#replyModal').modal('show');
})

//保存
$('#replyForm').ajaxForm(function (data) {
    if (data.success) {
        Swal.fire({
            html: '回复成功',
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


    <#--    预约按钮部分的-->
    <#--    查询预约业务-->
    $.get('/systemDictionaryItem/selectByTypeSn',{sn:'business'},function (data) {
        let str = '';
        let selectStr = '';
        $.each(data,function (index,ele) {
            str+='<button class="btn btn-primary-cus">'+ele.title+'</button>';
            selectStr+='<option value="'+ele.id+'">'+ele.title+'</option>';
        })
        $('.category').append(str);
        $('#categorySelect').append(selectStr);
    })
    //马上预约按钮
    $('#btn-appointment').click(function () {
        $('#appointmentModal').modal('show');
    })

    $('#appointmentForm').ajaxForm(function (data) {
        if (data.success){
            Swal.fire({
                html: '预约成功',
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

$('#btn-board').click(function () {
    window.location.href="/pageBoard";
})
</script>
</body>
</html>
