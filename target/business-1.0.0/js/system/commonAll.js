//commonAll.js 是所有页面都会引入的js

$(function () {//包起来 页面加载完毕后 执行以下
//删除按钮
    $(".btn-delete").click(function () {
        let url = $(this).data('url');
        Swal.fire({
            title: '是否确认删除?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '确认',
            cancelButtonText: '取消'
        }).then((result) => {
            if (result.value) {
                window.location.href=url;
            }
        })
    })

    $('.input-date').datetimepicker({
        format:'yyyy-mm-dd', //格式
        language:'zh-CN', //中文
        autoclose: true,//选择后自动关闭
        // showMeridian:true, //是否显示上下午
        minView:2,//精确到哪位
      //  endDate: new Date(), //定义当天之后日期不能选定
    });

    $('.input-datetime-noss').datetimepicker({
        format:'yyyy-mm-dd hh:ii', //格式
        language:'zh-CN', //中文
        autoclose: true,//选择后自动关闭
        minView:0,//精确到哪位
    });

    $('.input-datetime').datetimepicker({
        format:'yyyy-mm-dd hh:ii:ss', //格式
        language:'zh-CN', //中文
        autoclose: true,//选择后自动关闭
        minView:0,//精确到哪位
    });

});

