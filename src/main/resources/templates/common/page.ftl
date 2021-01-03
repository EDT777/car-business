<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script>
    //分页
    $(function(){
        $("#pagination").twbsPagination({
                totalPages: ${pageInfo.pages} || 1, //总页数
                startPage: ${pageInfo.pageNum} || 1, //当前页
                visiblePages:5, //显示几页
                first:"首页",
                prev:"上页",
                next:"下页",
                last:"尾页",
                initiateStartPageClick:false,
                onPageClick:function(event,page){//点击页数 会执行方法
					$("#currentPage").val(page);
					$("#searchForm").submit();
				}
		});
    })
</script>