<header class="main-header">
    <a href="../../index2.html" class="logo"
       style="background-color: #222d32;color: #fff;border-right: none;border-bottom: 1px solid grey;">
        <span class="logo-mini">Edric_豪</span>
        <span class="logo-lg"><b>小豪门店管理系统</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">
                    <a href="#">
                        <i class="fa fa-envelope-o"></i>
                    </a>
                </li>
                <li class="dropdown tasks-menu">
                    <a href="#">
                        <i class="fa fa-flag-o"></i>
                    </a>
                </li>
                <li class="dropdown notifications-menu">
                    <a href="#">
                        <i class="fa fa-bell-o"></i>
                    </a>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i></a>
                    <ul class="dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close"
                        style="min-width: 100px;">
                        <li>
                            <a href="#">
                                <i class="fa fa-cog"></i>
                                个人设置
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>

                            <a href="#" class="updatePassword">
                                <i class=" fa fa-user"></i>
                                修改密码
                            </a>

                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/userLogout">
                                <i class="fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

</header>

<!-- 修改密码的模态框 -->
<div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>

            <form class="form-horizontal" action="/updatePassword" method="post" id="passwordEditForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">原密码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="password" placeholder="请输入原来的密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="newPassword" id="newPassword"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-3 control-label">确认新密码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="re_newPassword" id="re_newPassword"
                                   placeholder="请再次输入新密码">
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
    <#--        点击修改密码 弹出修改密码模态框-->
    $('.updatePassword').click(function () {
        $('#passwordEditForm input').val('');
        $('#passwordModal').modal('show');
    })

    //    修改密码模态框的表单验证
    //    找到表单,并调用bootstrap-validator验证插件的方法来初始化验证规则
    $("#passwordEditForm").bootstrapValidator({
        feedbackIcons: { //图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: { //配置要验证的字段
            password: {
                validators: {
                    notEmpty: { //不能为空
                        message: "原密码必填" //错误时的提示信息
                    },
                    stringLength: { //字符串的长度范围
                        min: 1,
                        max: 17
                    },
                }
            },
            newPassword: {
                validators: {
                    notEmpty: { //不能为空
                        message: "密码必填" //错误时的提示信息
                    },
                    stringLength: { //字符串的长度范围
                        min: 1,
                        max: 17
                    },
                }
            },
            re_newPassword: {
                validators: {
                    notEmpty: { //不能为空
                        message: "确认密码必填" //错误时的提示信息
                    },
                    identical: {//两个字段的值必须相同
                        field: 'newPassword',
                        message: '两次输入的密码必须相同'
                    },
                    stringLength: { //字符串的长度范围
                        min: 1,
                        max: 17
                    },
                }
            },
        }
    }).on('success.form.bv', function () { //表单所有数据验证通过后执行里面的代码
        //提交异步表单
        $("#passwordEditForm").ajaxForm(function (data) {//不会触发验证
            if (data.success) {
                Swal.fire({
                    text: '修改密码成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result) => {
                    if (result.value) {
                        window.location.href="/userLogout";
                    }
                })
            } else {
                Swal.fire(
                    data.msg,
                    '',
                    'error'
                )
            }
        });
    });
</script>