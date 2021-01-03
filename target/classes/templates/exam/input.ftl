

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>试卷管理</title>
    <#include "/common/link.ftl">

</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <#include "/common/navbar.ftl">
    <#assign currentMenu="examInput"/>
    <#include "/common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>试卷编辑</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px">
                <form class="form-horizontal" action="/exam/save" method="post" id="editForm" >

                    <input type="hidden"  name="id">
                    <div class="form-group"  style="margin-top: 10px;">
                        <label  class="col-sm-2 control-label">试卷名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  placeholder="请输入试卷名称" name="title">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">考试时长：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"   placeholder="请输入考试时长" name="examMinute">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">单选题：</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入随机组题数量" name="singleNum">
                        </div>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入每题分数" name="singleGrade">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">多选题：</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入随机组题数量" name="multipleNum">
                        </div>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入每题分数" name="multipleGrade">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">判断题：</label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入随机组题数量" name="judgeNum">
                        </div>

                        <div class="col-sm-3">
                            <input type="text" class="form-control"   placeholder="请输入每题分数" name="judgeGrade">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">试卷总分：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"   placeholder="请输入试卷总分" name="totalScore">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" class="btn btn-primary btn-submi">生成试卷</button>
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
<#--试卷总分自动生成-->
    $('#editForm input').keyup(function () {
        $('#editForm input[name=totalScore]').val('');
        let singleGrade = $('#editForm input[name=singleGrade]').val()*$('#editForm input[name=singleNum]').val();
        let multipleGrade = $('#editForm input[name=multipleGrade]').val()*$('#editForm input[name=multipleNum]').val();
        let judgeGrade = $('#editForm input[name=judgeGrade]').val()*$('#editForm input[name=judgeNum]').val();
        $('#editForm input[name=totalScore]').val(singleGrade+multipleGrade+judgeGrade);
    })

//    生成试卷表单用ajax提交
$('#editForm').ajaxForm(function (data) {
    if (data.success) {
        Swal.fire({
            html: '试卷生成成功',
            icon: 'success',
            confirmButtonText: 'OK',
        }).then((result) => {
            if (result.value) {
                console.log(data.id)
                window.location.href='/exam/list?id='+data.id;
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
