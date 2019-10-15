$(function(){
    $('#type_div').hradio();
    $("#monthly_report_select").validate({
        rules:{
            month:"required",
        },
        messages:{
            month:"请输入月份",
        }
    });

    $("#qryBtn").click(function(){
        if( $("#monthly_report_select").valid() ){
             $.post(ctx + "/statistics/query/monthly/report.do",
                        $("#monthly_report_select").serialize(),
                        function(data) {
                            if (data.code == "000000") {
                                $("#Pagination").pagination(data.failListResult.total, {
                                    current_page:(data.failListResult.pageNum - 1),
                                    items_per_page:data.failListResult.pageSize,
                                    callback: function (current_page,per_page){
                                        $.post(ctx + "/statistics/query/monthly/report.do",
                                                 $("#monthly_report_select").serialize()+"&page=" + (current_page) + "&rows=" + per_page,
                                                function callback(data) {
                                                    if (data) {
                                                        if(data.code == "000000") {
                                                            analyzeData(data);
                                                        } else {
                                                            alertify.error(data.msg);
                                                        }
                                                    } else {
                                                        alertify.error("查询失败, 请重试!");
                                                    }
                                                }
                                        );
                                    }
                                });
                            }
                            analyzeData(data);
                        },"json");
        }
    });

    $("#retryBtn").click(function(){
        if( $("#monthly_report_select").valid() ){
            if($("#monthly_fail_list").children().length > 0) {
                    $.post(ctx + "/statistics/query/retry.do",
                            $("#monthly_report_select").serialize(),
                            function(data) {
                                if (data.code == "000000") {
                                    alertify.success('请求提交成功');
                                } else {
                                    alertify.error('请求提交失败: ' + data.msg);
                                }

                            },"json");
            } else {
                alertify.error('请先查询需要补发的数据');
            }
        }

    });

    // 解析数据
    function analyzeData(data) {
		if ( data ) {
			if( "400000" == data.code ){
				alertify.error('出错啦: ' + data.msg );
				return ;
			}
			$("#monthly_statistics_list").html("");
			var statisticsStr = "<tr><td>" + data.enterpriseCount + "</td>";
			statisticsStr += "<td>" + data.successCount + "</td>";
			statisticsStr += "<td>" + data.failCount + "</td>";
			$("#monthly_statistics_list").append(statisticsStr);

		     $("#monthly_fail_list").html("");
		     var failListResult = data.failListResult.list;
		     for (var i = 0; i < failListResult.length; i++) {
                 var str = "<tr><td>" + (i+1) + "</td>";
                 str += "<td>" + checkValue(failListResult[i].orgName) + "</td>";
                 str += "<td>" + checkValue(failListResult[i].errMsg) + "</td>";
                 $("#monthly_fail_list").append(str);
             }

             $(".table").tableUI();
		 }
    };


    function checkValue( val ){
    	if(val){
    		return val;
    	}
    	return "";
    };

});


