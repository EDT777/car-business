<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算单明细</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">

    <#assign currentMenu="consumptionItem"/>

    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>结算单明细</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/consumptionItem/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">结算单:</label>
                        <input type="text" class="form-control" name="cno" placeholder="请输入结算单流水号" value="${qo.cno}">
                    </div>
                    <div class="form-group">
                        <label for="keyword">创建人:</label>
                        <input type="text" class="form-control" name="keyword" placeholder="请输入姓名" value="${qo.keyword}">
                    </div>
                    <div class="form-group">
                        <label > 业务大类类型:</label>
                        <select class="form-control" name="category.id" >
                            <option value="">全部</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>实收金额范围查询：</label>
                        <input placeholder="请输入最低实收" type="text"  class="form-control "  name="minPayAmount" value="${(qo.minPayAmount?c)!}" /> -
                        <input placeholder="请输入最高实收" type="text"  class="form-control "  name="maxPayAmount" value="${(qo.maxPayAmount?c)!}" />
                    </div>
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
<#--                    <a href="#" class="btn btn-success btn-input" style="margin: 10px">-->
<#--                        <span class="glyphicon glyphicon-plus"></span> 添加-->
<#--                    </a>-->
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>结算单流水号</th>
                            <th>创建人</th>
                            <th>创建时间</th>
                            <th>业务大类</th>
                            <th>业务小类</th>
                            <th>实收金额</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as p>
                        <tr>
                            <td>${p_index+1}</td>
                            <td>${p.cno}</td>
                            <td>${p.createUser.name}</td>
                            <td>${(p.createTime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
                            <td>${p.category.title}</td>
                            <td>${p.categoryItem.title}</td>
                            <td>${p.payAmount}</td>
                            <td>
                                <a href="#" class="btn btn-info btn-xs btn-input" data-cno="${p.cno}">
                                    <span class="glyphicon glyphicon-envelope"></span> 所属结算单
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

<script>
<#--    跳转至所属结算单-->
    $('.btn-input').click(function () {
            let cno =$(this).data('cno');
            $.get('/consumption/selectIdByCno',{cno:cno},function (data) {
                    window.location.href='/consumption/input?id='+data.id;
            })
    })

    //获取业务大类下拉框数据
    $.get('/systemDictionaryItem/selectByTypeSn', {sn: 'business'}, function (data) {
        let str = '';
        $.each(data, function (index, ele) {
            str += '<option value="' + ele.id + '">' + ele.title + '</option>';
        })
        $('#searchForm select[name="category.id"]').append(str);
        $('#searchForm select[name="category.id"]').val(${qo.category.id});
    })
</script>

</body>
</html>
