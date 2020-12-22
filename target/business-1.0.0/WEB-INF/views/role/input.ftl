

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="role"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate" method="post" id="editForm">

                    <input type="hidden"  name="id" value="${role.id}">
                    <div class="form-group"  style="margin-top: 10px;">
                        <label  class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="name"  placeholder="请输入角色名称" value="${role.name}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="sn"  placeholder="请输入角色编号" value="${role.sn}">
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" size="15">
                                   <#list permissions as p>
                                       <option value="${p.id}">${p.name}</option>
                                   </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" size="15" name="ids">
                                        <#list role.permissions as ps>
                                            <option value="${ps.id}">${ps.name}</option>
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
        $(".selfPermissions > option").prop('selected',true);
        //    提交表单
        $('#editForm').submit();
    })

    //    角色去重
    //    把右边的option的value存放在一个ids数组中
    let ids = [];
    $('.selfPermissions > option').each(function (index,ele) {
        ids.push($(ele).val());
    })
    console.log(ids);
    //    遍历左边的下拉框,拿到一个option的value,判断是否存在ids中,如果存在,则删除自己
    $('.allPermissions > option').each(function (index,ele) {
        let id = $(ele).val();
        console.log(id);
        if ($.inArray(id,ids) > -1){
            $(ele).remove();
        }
    })

</script>
</body>
</html>
