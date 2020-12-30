<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>入职测试</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <#assign currentMenu="exam"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>试卷预览</h1>

        </section>
        <section class="content">
            <div class="box" style="padding: 10px">
                <div class="row">

                    <div class="col-md-offset-4 col-md-8">
                        <h1 class="col-md-offset-1">${exam.title}</h1>
                    </div>
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="col-md-4">时间：${exam.examMinute} </div>
                                <div class="col-md-4">总分：${exam.totalScore}</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>一、单选题（共${singles}分）</h3>
                    </div>
                    <#list exam.singles as s>

                    <div class="panel-body">
                        <p><span>${s_index+1}.${s.title}（${oneSingle}分）</span></p>
                        <#list s.items as i>
                        <p>${i.title}</p>
                        </#list>

                        <div style="color:red">正确答案：

                        <#list s.items as i2>
                            <#if i2.judgeRight>
                                ${i2.title?substring(0,1)}
                            </#if>
                        </#list>
                      </div>

                    </div>
                    </#list>


                    <div class="panel-heading">
                        <h3>二、多选题（共${multiples}分）</h3>
                    </div>
                    <#list exam.multiples as m>
                    <div class="panel-body">
                        <p>${m_index+1}.${m.title}<span>（${oneMultiple}分）</span></p>
                        <#list m.items as mi>
                            <p>${mi.title}</p>
                        </#list>

                        <div style="color:red">正确答案：
                            <#list m.items as m2>
                                <#if m2.judgeRight>
                                    ${m2.title?substring(0,1)}
                                </#if>
                            </#list>
                        </div>
                    </div>
                    </#list>

                    <div class="panel-heading">
                        <h3>三、判断题（共${judges}分）</h3>
                    </div>
                    <#list exam.judges as j>
                    <div class="panel-body">
                        <p>${j_index+1}.${j.title}<span>（${oneJudge}分）</span></p>
                        <div style="color:red">正确答案：
                            <#if j.judgeRight>
                                正确
                                <#else> 错误
                            </#if>
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
        </section>
    </div>
    <#include "/common/footer.ftl">
</div>
</body>
</html>
