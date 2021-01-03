<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>门店收入报表</title>
    <#include "/common/link.ftl" >

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl" >

    <#assign currentMenu="businessReport"/>

    <#include "/common/menu.ftl" >
    <div class="content-wrapper">
        <section class="content-header">
            <h1>门店收入报表</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/businessReport/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label>门店查询</label>
                            <select class="form-control" name="businessId">
                                <option value="">请选择门店</option>
                                <#list business as b>
                                    <option value="${b.id}">${b.name}</option>
                                </#list>
                            </select>
                            <script>
                                $("select[name=businessId]").val(${qo.businessId})
                            </script>
                        </div>

                        <div class="form-group">
                            <label>时间段查询:</label>
                            <div class="input-daterange input-group" >
                                <input type="text" class="input-sm form-control input-date" name="startDate" value="${(qo.startDate?string("yyyy-MM-dd"))!}"/>
                                <span class="input-group-addon">到</span>
                                <input type="text" class="input-sm form-control input-date" name="endDate" value="${(qo.endDate?string("yyyy-MM-dd"))!}"/>
                            </div>
                        </div><div class="form-group">
                            <label>结算单状态</label>
                            <select class="form-control" name="status">
                                <option value="">全部</option>
                             <#list consumptionStatus as cs>
                                 <option value="${cs.getValue()}">${cs.getName()}</option>
                             </#list>
                            </select>
                            <script>
                                $('select[name=status]').val(${qo.status});
                            </script>
                        </div>

                        <div class="form-group">
                            <label>预约类型</label>
                            <select class="form-control" name="ano">
                                <option value="">请选择预约类型</option>
                                <option value="1">预约</option>
                                <option value="0">非预约</option>
                            </select>
                            <script>
                                $('select[name=ano]').val(${(qo.ano?string('1','0'))!})
                            </script>
                        </div>

                        <br/>
                        <br/>

                        <div class="form-group">
                            <label for="status">分组类型:</label>
                            <select class="form-control" name="groupType">
                               <#list businessReportEnums as e>
                                   <option value="${e.getValue()}">${e.getName()}</option>
                               </#list>
                            </select>
                            <script>
                                $('select[name=groupType]').val("${qo.groupType}");
                            </script>
                        </div>

                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                        <button type="button" class="btn btn-info btn-chart" >
                            <span class="glyphicon glyphicon-stats"></span> 柱状图
                        </button>
                    </form>
                </div>
                <script>
                    $('.btn-chart').click(function () {
                        // window.location.href = "/businessReport/listBar?"+$('#searchForm').serialize();
                        $('#chartModal').modal({
                            remote : "/businessReport/listBar?"+$('#searchForm').serialize()
                        })
                        $('#chartModal').modal('show');
                    })


                </script>
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>分组类型</th>
                                <th>总订单数</th>
                                <th>总消费金额</th>
                                <th>总实收金额</th>
                                <th>总优惠金额</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list pageInfo.list as m>
                            <tr>
                                <td>${m.groupType}</td>
                                <td>${m.number}</td>
                                <td>${m.totalAmount}</td>
                                <td>${m.discountAmount}</td>
                                <td>${m.payAmount}</td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>
                    <#include "/common/page.ftl">
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>

<!-- Modal模态框 -->
<div class="modal fade" id="chartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" style="padding: 20px">

        </div>
    </div>
</div>


</body>
</html>