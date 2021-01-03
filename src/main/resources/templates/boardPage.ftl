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
        <div class="row justify-content-between">
            <a href="/index">
                <div class="col-md-4 border-right" style="padding: 0px;">
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

            </div>
            <div class="col-md-4 tel" style="padding: 15px;">
                <h2 style="vertical-align:middle;"><i class="fa fa-phone"></i></h2>
                <h5 style="color:#434343; ">全国免费热线:</h5>
                <h2>020-77777</h2>
            </div>
        </div>
    </div>

</div>

<h1 style="text-align: center">
    小豪留言列表页
    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
        <span class="glyphicon glyphicon-plus"></span> 我要留言
    </a>
    <!--高级查询--->
    <form class="form-inline" id="searchForm" action="/pageBoard" method="post">
        <input type="hidden" name="currentPage" id="currentPage" value="1">
    </form>
</h1>
<div class="container">
    <#list pageInfo.list as b>
        <div class="box box-widget">
            <div class="box-header with-border">
                <div class="user-block">
                    <img class="img-circle" src="/js/adminlte/img/user2-160x160.jpg" alt="User Image">
                    <span class="username"><a href="#">${b.nickname}</a></span>
                    <span class="description">${(b.createTime?string('yyyy-MM-dd HH:mm:ss'))!}<span>咨询类型：</span><span>${b.category.title}</span>-<span>${b.categoryItem.title}</span></span>
                </div>
            </div>
            <div class="box-body">
                <p>${b.content}</p>
                <span class="pull-right text-muted">${b.replystatus?string('已回复','未回复')}
                <a href="/listBoard?id=${b.id}">查看详情</a>
            </span>
            </div>
        </div>
    </#list>
    <!--分页-->
    <#include "/common/page.ftl" >
</div>

<!-- Modal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog " style="max-width: 380px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入留言信息</h4>
            </div>
            <form id="messageForm" method="post" action="/messageBoard/saveOrUpdate">
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                        <input class="form-control" placeholder="请输入您的昵称" name="nickname">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-home"></i></span>
                        <select class="form-control categorySelect" name="category.id" id="akaCategory">
                            <option value="">请选择所属业务大类</option>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"></i></span>
                        <select class="form-control" name="categoryItem.id" id="categoryItem">
                            <option value="">无</option>
                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                        <textarea class="form-control" placeholder="请输入您的留言内容" name="content"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary-full">确定留言</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#--预约模态框-->
<div class="modal fade" id="appointmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog " style="max-width: 380px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入预约信息</h4>
            </div>
            <form id="appointmentForm" method="post" action="/appointment/save">
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i> </span>
                        <input class="form-control" placeholder="请输入您的姓名" name="contactName">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                        <input class="form-control" placeholder="请输入您的电话" name="contactTel">
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
                        <select class="form-control categorySelect" placeholder="请选择预约业务" name="category.id">
                            <option value="">请选择预约业务</option>

                        </select>
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <input class="form-control input-datetime-noss" placeholder="请选择预约时间" name="appointmentTime">
                    </div>
                    <br/>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                        <textarea class="form-control" placeholder="备注说明" name="info"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary-full">确定预约</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>

    $('.btn-input').click(function () {
        $('#messageForm input,#messageForm select').val('');
        $('#messageForm textarea').html('');
        $('#messageModal').modal('show');
    })

    $('#messageForm').ajaxForm(function (data) {
        if (data.success) {
            Swal.fire({
                html: '留言成功',
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

    //    字典目录值改变事件
    $('#akaCategory').change(function () {
        //获取当前选择的目录id
        let id = $(this).val();
        //    发ajax请求到后台,根据目录查询明细
        $.get('/systemDictionaryItem/selectByParentId', {id: id}, function (data) {// 明细1,明细2
            //    查到数据 遍历循环 拼接option 设置到明细的下拉框中
            let str = '<option value="">无</option>';
            $.each(data, function (index, ele) {
                str += '<option value="' + ele.id + '">' + ele.title + '</option>';
            })
            $("#categoryItem").html(str);
        })
    })


    <#--    预约按钮部分的-->
    <#--    查询预约业务-->
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'business'}, function (data) {
        let str = '';
        let selectStr = '';
        $.each(data, function (index, ele) {
            str += '<button class="btn btn-primary-cus">' + ele.title + '</button>';
            selectStr += '<option value="' + ele.id + '">' + ele.title + '</option>';
        })
        $('.category').append(str);
        $('.categorySelect').append(selectStr);
    })
    //马上预约按钮
    $('#btn-appointment').click(function () {
        $('#appointmentModal').modal('show');
    })

    $('#appointmentForm').ajaxForm(function (data) {
        if (data.success) {
            Swal.fire({
                html: '预约成功',
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
