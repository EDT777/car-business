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
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm" >
                    <input type="hidden" name="id" value="${employee.id}">
                    <input type="checkbox" id="status" name="status" class="checkbox" checked>
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="username" value="${employee.username}" placeholder="请输入用户名"
                            <#if employee??>
                               disabled
                            </#if>
                            >
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-2 control-label">真实姓名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name"  placeholder="请输入真实姓名" value="${employee.name}">
                        </div>
                    </div>
                    <#if !employee??>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="repassword" name="repassword" placeholder="再输入一遍密码">
                        </div>
                    </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="email" placeholder="请输入邮箱" value="${employee.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="age" placeholder="请输入年龄" value="${employee.age}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <#list departments as d>
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                            <script>
                                $('#dept').val(${employee.dept.id})
                            </script>
                        </div>
                    </div>
                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6"style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox"
                            <#if employee.admin>
                               checked
                            </#if>
                            >
                        </div>
                    </div>
                    <div class="form-group" id="roleDiv">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <#list roles as r>
                                        <option value="${r.id}">${r.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <#list employee.roles as r>
                                        <option value="${r.id}">${r.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button type="button" class="btn btn-primary btn-submit">保存</button>
                            <a href="javascript:window.history.back()" class="btn btn-danger">取消</a>
                        </div>
                    </div>

                </form>

            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
<script>
    function moveSelected(src,target) {
    //        获取源下拉框选中的数据,移动到目标下拉框中
        $("."+target).append($("."+src+"> option:selected"))
    }
    function moveAll(src,target) {
        //        获取源下拉框所有的数据,移动到目标下拉框中
        $("."+target).append($("."+src+" > option"))
    }

//    保存
    $(".btn-submit").click(function () {
    //    让右边的下面所有option都选中
        $(".selfRoles > option").prop('selected',true);
    //    提交表单
        $('#editForm').submit();
    })



//    角色去重
//    把右边的option的value存放在一个ids数组中
    let ids = [];
    $('.selfRoles > option').each(function (index,ele) {
        ids.push($(ele).val());
    })
    console.log(ids);
//    遍历左边的下拉框,拿到一个option的value,判断是否存在ids中,如果存在,则删除自己
    $('.allRoles > option').each(function (index,ele) {
            let id = $(ele).val();
            console.log(id);
            if ($.inArray(id,ids) > -1){
                $(ele).remove();
            }
    })

//    选中超级管理员按钮
    let roleDiv;
    $('#admin').click(function () {
    //        获取当前复选框状态
        let checked = $(this).prop('checked');
    //    如果是选中,则删除分配角色相关区域
        if (checked){
        roleDiv = $('#roleDiv').detach();
        }else {//如果没有选中
            $('#adminDiv').after(roleDiv);    //    如果是没选中,则恢复角色区域
        }

    })
    //页面加载完毕判断一次
    let checked = $('#admin').prop('checked');
    //    如果是选中,则删除分配角色相关区域
    if (checked){
        roleDiv = $('#roleDiv').detach();
    }

//    找到表单,并调用bootstrap-validator验证插件的方法来初始化验证规则
    $("#editForm").bootstrapValidator({
        feedbackIcons: { //图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{ //配置要验证的字段
            username:{
                validators:{ //验证的规则
                    notEmpty:{ //不能为空
                        message:"用户名必填" //错误时的提示信息
                    },
                    stringLength: { //字符串的长度范围
                        min: 1,
                        max: 9
                    },
                    remote: { //远程验证
                        type: 'POST', //请求方式
                        url: '/employee/checkName', //请求地址
                        message: '用户名已经存在', //验证不通过时的提示信息
                        delay: 1000, //输入内容1秒后发请求
                    }
                }
            },
            name:{
                validators:{
                    notEmpty:{ //不能为空
                        message:"真实姓名必填" //错误时的提示信息
                    },
                }
            },
            password:{
                validators:{
                    notEmpty:{ //不能为空
                        message:"密码必填" //错误时的提示信息
                    },
                }
            },
            repassword:{
                validators:{
                    notEmpty:{ //不能为空
                        message:"密码必填" //错误时的提示信息
                    },
                    identical: {//两个字段的值必须相同
                        field: 'password',
                        message: '两次输入的密码必须相同'
                    },
                }
            },
            email: {
                validators: {
                    emailAddress: {} //邮箱格式
                }
            },
            age:{
                validators: {
                    between: { //数字的范围
                        min: 7,
                        max: 99
                    }
                }
            }
        }
    }).on('success.form.bv', function() { //表单所有数据验证通过后执行里面的代码
        //提交异步表单
        $("#editForm").ajaxSubmit(function (data) {//不会触发验证
            if (data.success){
                Swal.fire({
                    text: '保存成功',
                    icon: 'success',
                    confirmButtonText: 'OK',
                }).then((result)=>{
                    if (result.value){
                        window.location.href="/employee/list";
                    }
                })
            }else {
                Swal.fire(
                    '保存失败',
                    '',
                    'error'
                )
            }
        });
    });
</script>
</body>
</html>
