<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>操作日志</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <!--定义一个变量  用于菜单回显-->
    <#assign currentMenu="systemLog"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>操作日志</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/systemLog/list" method="post" style="margin: 20px 0px 0px 10px">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">

                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control"  placeholder="请输入用户名" name="userName" value="${qo.userName}">
                    </div>

                    <div class="form-group">
                        <label>请求方法</label>
                        <input type="text" class="form-control"  placeholder="请输入请求方法" name="operationMethod" value="${qo.operationMethod}">
                    </div>

                    <div class="form-group">
                        <label>操作IP</label>
                        <input type="text" class="form-control"  placeholder="请输入操作IP" name="ipAddress" value="${qo.ipAddress}">
                    </div>

                    <div class="form-group">
                        <label>操作结果</label>
                        <select class="form-control" name="operationResult">
                            <option value="">全部</option>
                            <option value="1">成功</option>
                            <option value="0">失败</option>
                        </select>
                        <script>
                            $('select[name=operationResult]').val(${(qo.operationResult?string('1','0'))!});
                        </script>
                    </div>

                    <div class="form-group">
                        <label>操作时间查询：</label>
                        <input placeholder="请输入开始时间" type="text"  class="form-control input-datetime" name="startTime"  value="${(qo.startTime?string('yyyy-MM-dd HH:mm:ss'))!}"/> -
                        <input placeholder="请输入结束时间" type="text"  class="form-control input-datetime" name="endTime" value="${(qo.endTime?string('yyyy-MM-dd HH:mm:ss'))!}"/>
                    </div>

                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>

                </form>
                <!--编写内容-->
                <div class="box-body table-responsive ">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名</th>
                            <th>用户操作</th>
                            <th>请求方法</th>
                            <th>请求参数</th>
                            <th>执行时长(毫秒)</th>
                            <th>IP地址</th>
                            <th>操作时间</th>
                            <th>操作结果</th>
                            <th>错误信息</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as l>
                        <tr>
                            <td>${l_index+1}</td>
                            <td>${l.userName}</td>
                            <td>${l.operationName}</td>
                            <td>${l.operationMethod}</td>
                            <td>${l.operationParameters}</td>
                            <td>${l.operationDuration}(毫秒)</td>
                            <td>${l.ipAddress}</td>
                            <td>${(l.operationTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                            <td>${l.operationResult?string("成功","失败")}</td>
                            <td>
                                <#if l.errorInfo??>
                                    ${l.errorInfo}
                                    <#else>无
                                </#if>

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


</body>
</html>
