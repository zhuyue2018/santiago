<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="easipay" uri="/WEB-INF/tag/easipay-tag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>结算经办</title>
    <link href="${ctx}/styles/jquery-confirm.css" rel="stylesheet">
    <link href="${ctx}/styles/bundled.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/scripts/js/jquery/jquery-confirm.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/js/clean.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

        //查询
        function query() {
            $("#queryForm").submit();
        }

        //清除
        function clearText() {
            document.getElementById("createFromTime").value = "";
            document.getElementById("createToTime").value = "";
            document.getElementById("sttFromTime").value = "";
            document.getElementById("sttToTime").value = "";
            document.getElementById("cusName").value = "";
            document.getElementById("cusNo").value = "";
            document.getElementById("sttBatchNo").value = "";
            document.getElementById("auditState").value = "";
        }

        function audit(id, index, auditFlag, cusName, sttDateStr, sttId) { //审核结果
            sttDateStr = sttDateStr.replace(/-/g, '/');
            var sttDate = new Date(Date.parse(sttDateStr));
            var date = new Date();
            if (sttDate > date) {
                msg2 = "商户名称:" + cusName + ",结算编号:" + sttId + ",尚未到结算日期;<br>是否确认经办？";
                confirmBtnClass = "btn-default";
                cancelBtnClass = "btn-primary";
            } else {
                msg2 = "确认经办？";
                confirmBtnClass = "btn-primary";
                cancelBtnClass = "btn-default";
            }
            if ("00" == auditFlag) { // 审核通过
                $.confirm({
                    title: 'Confirm!',
                    content: msg2,
                    buttons: {
                        confirm: {
                            text: "确认",
                            btnClass: confirmBtnClass,
                            action: function () {
                                $("#auditAccept" + index).attr("disabled", true);
                                $("#auditReject" + index).attr("disabled", true);
                                $("#auditAccept" + index).hide();
                                $("#auditReject" + index).hide();
                                $("#" + id).attr("checked", false);
                                $("#" + id).attr("disabled", true);
                                //通知后台处理
                                $.ajax({
                                    url: "/fm/settle/remit/audit",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        cmdId: id,
                                        auditType: "audit",
                                        auditFlag: auditFlag
                                    },
                                    success: function (data) {
                                        $.alert(data.message);
                                        // $("form[name='splitPageForm']").submit();
                                    },
                                    error: function (data) {
                                        $.alert("经办通过异常," + data);
                                    }
                                });
                            }
                        },
                        cancel: {
                            text: "取消",
                            btnClass: cancelBtnClass,
                            action: function () {
                            }
                        }
                    }
                });
            } else if ("01" == auditFlag) { // 审核拒绝
                $.confirm({
                    title: 'Confirm!',
                    content: '' +
                        '<form action="" class="formName">' +
                        '<div class="form-group">' +
                        '<label>确认经办不通过？</label>' +
                        '<input type="text" placeholder="请填写经办不通过原因！" class="rejectcause form-control" required />' +
                        '</div>' +
                        '</form>',
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'btn-blue',
                            action: function () {
                                $("#auditAccept" + index).attr("disabled", true);
                                $("#auditReject" + index).attr("disabled", true);
                                $("#auditAccept" + index).hide();
                                $("#auditReject" + index).hide();
                                $("#" + id).attr("checked", false);
                                $("#" + id).attr("disabled", true);
                                var rejectCause = this.$content.find('.rejectcause').val();
                                if (!rejectCause) {
                                    $.alert('请填写拒绝原因');
                                    return false;
                                }
                                $.ajax({
                                    url: "/fm/settle/remit/audit",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        cmdId: id,
                                        auditType: "audit",
                                        auditFlag: auditFlag,
                                        rejectCause: rejectCause
                                    },
                                    success: function (data) {
                                        $.alert(data.message);
                                        // $("form[name='splitPageForm']").submit();
                                    },
                                    error: function (data) {
                                        $.alert("经办异常," + data);
                                    }
                                });
                            }
                        },
                        cancel: {
                            text: '取消',
                            btnClass: 'btn-default'
                        }
                    }
                });
            }
        }

        //
        // function audit(id, index, auditFlag, cusName, sttDateStr, sttId) { //审核结果
        //     sttDateStr = sttDateStr.replace(/-/g, '/');
        //     var sttDate = new Date(Date.parse(sttDateStr));
        //     var date = new Date();
        //     if (sttDate > date) {
        //         msg2 = "商户名称:" + cusName + ",结算编号:" + sttId + ",尚未到结算日期;<br>是否确认经办";
        //     } else {
        //         msg2 = "确认经办";
        //     }
        //     confirmResult = "";
        //     rejectCause = "";
        //     if ("00" == auditFlag) { // 审核通过
        //         confirmResult = confirm(msg2 + "通过?");
        //     } else if ("01" == auditFlag) { // 审核拒绝
        //         rejectCause = window.prompt("确认经办不通过？", "请填写经办不通过原因！");
        //     }
        //     if (true == confirmResult ||  ("" != rejectCause && null != rejectCause)) {
        //         $("#auditAccept" + index).attr("disabled", true);
        //         $("#auditReject" + index).attr("disabled", true);
        //         $("#auditAccept" + index).hide();
        //         $("#auditReject" + index).hide();
        //         //通知后台处理
        //         $.ajax({
        //             url: "/fm/settle/remit/audit",
        //             type: "POST",
        //             dataType: "json",
        //             data: {
        //                 cmdId: id,
        //                 auditType: "audit",
        //                 auditFlag: auditFlag,
        //                 rejectCause: rejectCause
        //             },
        //             success: function (data) {
        //                 alert(data.message);
        //                 $("form[name='splitPageForm']").submit();
        //             },
        //             error: function (data) {
        //                 alert("经办" + msg + "异常," + data);
        //             }
        //         });
        //     }
        // }

        function batchAudit(auditFlag) {
            var ids = "";
            var msg2 = "";
            var box = document.getElementsByName('box');
            confirmBtnClass = "btn-primary";
            cancelBtnClass = "btn-default";
            for (var i = 0; i < box.length; i++) {
                sttDateStr = box[i].getAttribute("sttDate");
                cusName = box[i].getAttribute("cusName");
                sttId = box[i].getAttribute("sttId");
                if (box[i].checked) {
                    var value = box[i].id;
                    ids = value + "|" + ids;
                    sttDateStr = sttDateStr.replace(/-/g, '/');
                    var sttDate = new Date(Date.parse(sttDateStr));
                    var date = new Date();
                    if (sttDate > date) {
                        msg2 = msg2 + "商户名称:" + cusName + ",结算编号:" + sttId + ",尚未到结算日期;<br>";
                        confirmBtnClass = "btn-default";
                        cancelBtnClass = "btn-primary";
                    }
                }
            }
            if (ids.length == 0) {
                $.alert("请勾选批次进行操作!");
                return;
            }
            // confirmResult = "";
            // rejectCause = "";
            if ("00" == auditFlag) { // 审核通过
                $.confirm({
                    title: 'Confirm!',
                    content: msg2 + "确认批量经办通过？",
                    buttons: {
                        confirm: {
                            text: "确认",
                            btnClass: confirmBtnClass,
                            action: function () {
                                for (var i = 0; i < box.length; i++) {
                                    if (box[i].checked) {
                                        box[i].checked = false;
                                        box[i].setAttribute("disabled", true);
                                        index = i+1;
                                        $("#auditAccept" + index).attr("disabled", true);
                                        $("#auditReject" + index).attr("disabled", true);
                                        $("#auditAccept" + index).hide();
                                        $("#auditReject" + index).hide();
                                    }                                }
                                var url = "/fm/settle/remit/audit";
                                $.ajax({
                                    url: url,
                                    cache: false,
                                    data: {
                                        cmdIds: ids,
                                        auditType: "audit",
                                        auditFlag: auditFlag
                                    },
                                    type: "POST",
                                    dataType: "json",
                                    success: function (data) {
                                        $.alert(data.message);
                                        // $("form[name='splitPageForm']").submit();
                                    },
                                    error: function (data) {
                                        $.alert("批量经办异常," + data);
                                    }
                                });
                            }
                        },
                        cancel: {
                            text: "取消",
                            btnClass: cancelBtnClass,
                            action: function () {
                            }
                        }
                    }
                });
                // confirmResult = confirm(msg2 + "是否确认批量经办通过?");
            } else if ("01" == auditFlag) { // 审核拒绝
                // rejectCause = window.prompt("确认批量经办不通过？", "请填写经办不通过原因！");
                $.confirm({
                    title: 'Confirm!',
                    content: '' +
                        '<form action="" class="formName">' +
                        '<div class="form-group">' +
                        '<label>确认批量经办不通过？</label>' +
                        '<input type="text" placeholder="请填写批量经办不通过原因！" class="rejectcause form-control" required />' +
                        '</div>' +
                        '</form>',
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'btn-blue',
                            action: function () {
                                for (var i = 0; i < box.length; i++) {
                                    if (box[i].checked) {
                                        box[i].checked = false;
                                        box[i].setAttribute("disabled", true);
                                        index = i+1;
                                        $("#auditAccept" + index).attr("disabled", true);
                                        $("#auditReject" + index).attr("disabled", true);
                                        $("#auditAccept" + index).hide();
                                        $("#auditReject" + index).hide();
                                    }
                                }
                                var rejectCause = this.$content.find('.rejectcause').val();
                                if (!rejectCause) {
                                    $.alert('请填写拒绝原因');
                                    return false;
                                }
                                $.ajax({
                                    url: "/fm/settle/remit/audit",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        cmdIds: ids,
                                        auditType: "audit",
                                        auditFlag: auditFlag,
                                        rejectCause: rejectCause
                                    },
                                    success: function (data) {
                                        $.alert(data.message);
                                        // $("form[name='splitPageForm']").submit();
                                    },
                                    error: function (data) {
                                        $.alert("批量经办拒绝异常," + data);
                                    }
                                });
                            }
                        },
                        cancel: {
                            text: '取消',
                            btnClass: 'btn-default'
                        }
                    }
                });
            }
            // if (confirmResult || ("" != rejectCause && null != rejectCause)) {
            //     $("#batchAuditAccept").attr("disabled", true);
            //     $("#batchAuditReject").attr("disabled", true);
            //     if (ids == "") {
            //         alert("请勾选批次进行操作!");
            //         return;
            //     }
            //     var url = "/fm/settle/remit/audit";
            //     $.ajax({
            //         url: url,
            //         cache: false,
            //         data: {
            //             cmdIds: ids,
            //             auditType: "audit",
            //             auditFlag: auditFlag,
            //             rejectCause: rejectCause
            //         },
            //         type: "POST",
            //         dataType: "json",
            //         success: function (data) {
            //             alert(data.message);
            //             $("form[name='splitPageForm']").submit();
            //         },
            //         error: function (data) {
            //             alert("批量经办" + msg + "异常," + data);
            //         }
            //     });
            // }
        }

        function checkboxall(Allname, name) {
            var ischecked = document.getElementById(Allname).checked;
            if (ischecked) {
                checkallbox(name);
            } else {
                discheckallbox(name);
            }
            calculateAmount();
        }

        //选中全部复选框
        function checkallbox(name) {
            var boxarray = document.getElementsByName(name);
            for (var i = 0; i < boxarray.length; i++) {
                if (!boxarray[i].getAttribute("disabled")) {
                    boxarray[i].checked = true;
                }
            }
        }

        //取消选中全部复选框
        function discheckallbox(name) {
            var boxarray = document.getElementsByName(name);
            for (var i = 0; i < boxarray.length; i++) {
                boxarray[i].checked = false;
            }
        }

        function calculateAmount(id) {
            var totalAT = 0;
            var totalCT = 0;
            var box = document.getElementsByName('box');
            for (var i = 0; i < box.length; i++) {
                if (box[i].checked) {
                    var value = box[i].value;
                    var num1 = parseFloat(value).toFixed(2);
                    num1 = parseFloat(num1);
                    var num2 = parseFloat(totalAT).toFixed(2);
                    num2 = parseFloat(num2);
                    totalAT = (num1 + num2).toFixed(2);
                    totalCT = totalCT + 1;
                }
                document.getElementById("selectedSpan").innerHTML = '所选待结算金额总计:' + totalAT + ' ,待结算总笔数:' + totalCT;
            }
        }

        function changeBatchHandleButton() {
            var auditState = $("#auditState").val();
            if ('1' == auditState) {
                document.getElementById("batchAuditAccept").style.display = "";
                document.getElementById("batchAuditReject").style.display = "";
            } else {
                document.getElementById("batchAuditAccept").style.display = "none";
                document.getElementById("batchAuditReject").style.display = "none";
            }
        }

        function download() {
            $("#queryForm").attr('action', "auditDownload");
            $("#queryForm").submit();
            $("#queryForm").attr('action', "audit");
        }
    </script>

    <style>
        table.table-1 {
            /*table-layout:fixed;*/
            empty-cells: show !important;
            border-collapse: collapse !important;
            word-wrap: break-word !important;
            margin: 0 auto !important;
            width: 100% !important;
            border-spacing: 0 !important;
        }

        table.table-1 th {
            font-weight: bold !important;
            background-color: #f8f8f8 !important;
            text-align: inherit !important;
            border-bottom: 1px solid #ededed !important;
        }

        table.table-1 th,
        table.table-1 td {
            word-wrap: normal !important;
            word-break: break-all !important;
            text-overflow: ellipsis !important;
            vertical-align: middle !important;
            padding: 1px 12px;
            height: 28px;
            border: 1px solid #ededed;
            white-space: nowrap;
        }

        .u-table {
            width: 100%;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -o-box-sizing: border-box;
            -ms-box-sizing: border-box;
            box-sizing: border-box;
            transition: opacity .3s ease;
            overflow: auto;
            overflow-x: scroll;
        (or overflow-y: scroll )
        }
    </style>
</head>

<body>
<div class="content">
    <div class="con ">
        <form id="queryForm" name="queryForm" action="audit" method="GET">
            <div class="table fontcolor4 fontsize1 fontfamily2">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="display: none">
                            <input type="text" name="queryFlag" value="0">
                        </td>
                        <td align="right">结算创建时间（起）：</td>
                        <td>
                            <input type="text" name="createFromTime" id="createFromTime" class="txt2"
                                   value="${queryDTO.createFromTime}"
                                   onfocus="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'createToTime\')||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})"/>
                        </td>
                        <td align="right">结算创建时间（止）：</td>
                        <td>
                            <input type="text" name="createToTime" id="createToTime" class="txt2"
                                   value="${queryDTO.createToTime}"
                                   onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'createFromTime\')}',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">待结算时间（起）：</td>
                        <td>
                            <input type="text" name="sttFromTime" id="sttFromTime" class="txt2"
                                   value="${queryDTO.sttFromTime}"
                                   onfocus="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'sttToTime\')||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})"/>
                        </td>
                        <td align="right">待结算时间（止）：</td>
                        <td>
                            <input type="text" name="sttToTime" id="sttToTime" class="txt2"
                                   value="${queryDTO.sttToTime}"
                                   onfocus="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'sttFromTime\')}',dateFmt:'yyyyMMdd'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">商户名称：</td>
                        <td>
                            <input name="cusName" class="txt2" id="cusName" value="${queryDTO.cusName}"/>
                        </td>
                        <td align="right">商户编号：</td>
                        <td>
                            <input name="cusNo" class="txt2" id="cusNo" value="${queryDTO.cusNo}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">结算批次号：</td>
                        <td>
                            <input type="text" name="sttBatchNo" id="sttBatchNo" class="txt2"
                                   value="${queryDTO.sttBatchNo}"/>

                        </td>
                        <td align="right">审核状态：</td>
                        <td>
                            <select id="auditState" name="auditState" class="select1"
                                    onchange="changeBatchHandleButton()">
                                <option value="1" <c:if test="${1 == queryDTO.auditState}"> selected="selected"</c:if>>
                                    初始
                                </option>
                                <option value="2" <c:if test="${2 == queryDTO.auditState}"> selected="selected"</c:if>>
                                    经办通过待审核
                                </option>
                                <option value="3" <c:if test="${3 == queryDTO.auditState}"> selected="selected"</c:if>>
                                    经办审核不通过
                                </option>
                                <%--<option value="4" <c:if test="${4 == queryDTO.auditState}"> selected="selected"</c:if>>--%>
                                <%--审核通过待经办--%>
                                <%--</option>--%>
                                <%--<option value="5" <c:if test="${5 == queryDTO.auditState}"> selected="selected"</c:if>>--%>
                                <%--审核不通过--%>
                                <%--</option>--%>
                                <%--<option value="6" <c:if test="${6 == queryDTO.auditState}"> selected="selected"</c:if>>--%>
                                <%--复核通过--%>
                                <%--</option>--%>
                                <%--<option value="7" <c:if test="${7 == queryDTO.auditState}"> selected="selected"</c:if>>--%>
                                <%--复核不通过--%>
                                <%--</option>--%>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td align="right">结算订单号：</td>
                        <td>
                            <input type="text" name="remitOrderId" id="remitOrderId" class="txt2"
                                   value="${queryDTO.remitOrderId}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td>
                            <input name="submitbtn" type="button" value="查询" class="bluebtn" id="submitbtn"
                                   onclick="query();"/>
                        </td>
                        <td align="right">&nbsp;</td>
                        <td>
                            <input name="clearBtn" type="button" value="清除" class="bluebtn" id="clearBtn"
                                   onclick="clean();clearText();"/>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>

    <div class="table fontcolor4 fontsize1 fontfamily2">
        <div class="u-table">
            <table class="table-1">
                <thead>
                <tr align="center" height="35" bgcolor="#cccccc">
                    <th class="border"><input id="checkall" type="checkbox" value=""
                                              onclick="checkboxall('checkall','box')"/></th>
                    <th class="border">序号</th>
                    <th class="border">结算批次号</th>
                    <th class="border">结算订单号</th>
                    <th class="border">结算创建时间</th>
                    <th class="border">待结算时间</th>
                    <th class="border">商户名称</th>
                    <th class="border">商户编号</th>
                    <%--<th class="border">批次订单总额</th>--%>
                    <%--<th class="border">批次分账总额</th>--%>
                    <th class="border">结算金额</th>
                    <th class="border">结算方式</th>
                    <th class="border">收款银行名称</th>
                    <th class="border">收款方银行账号</th>
                    <th class="border">审核状态</th>
                    <th class="border">审核原因</th>
                    <th class="border">备注</th>
                    <th class="border">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty sttCollectList }">
                    <tr>
                        <td class="fontfamily1" colspan="5" align="center">没有相关记录!</td>
                    </tr>
                </c:if>
                <c:forEach items="${sttCollectList}" var="item" varStatus="st">
                <c:choose>
                <c:when test="${st.index %2 == 0 }">
                <tr align="center" height="35">
                    </c:when>
                    <c:otherwise>
                <tr align="center" height="35" bgcolor="#eeeeee">
                    </c:otherwise>
                    </c:choose>
                    <td><input type="checkbox" name="box" id="${item.id}" value="${item.sttAmount}"
                               sttDate=${item.sttCalculateTime} cusName=${item.cusName} sttId="${item.sttId}"
                               onclick="calculateAmount('${item.id}');"/>
                    </td>
                    <td class="fontfamily1">${st.index + 1}</td>
                    <td class="fontfamily1">${item.sttBatchNo}</td>
                    <td class="fontfamily1">${item.remitOrderId}</td>
                        <%--<fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>--%>
                    <td class="fontfamily1">${item.createTime}
                            <%--<fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>--%>
                    </td>
                    <td class="fontfamily1">${item.sttCalculateTime}</td>
                    <td class="fontfamily1">${item.cusName}</td>
                    <td class="fontfamily1">${item.cusNo}</td>
                        <%--<td class="fontfamily1">${item.batchTotalAmount}</td>--%>
                        <%--<td class="fontfamily1">${item.batchTotalClearAmount}</td>--%>
                    <td class="fontfamily1">${item.sttAmount}</td>
                    <td class="fontfamily1">
                        <c:if test="${item.sttWay eq '1'}">结算至支付账户</c:if>
                        <c:if test="${item.sttWay eq '2'}">结算至银行账户</c:if>
                    </td>
                    <td class="fontfamily1">${item.sttBankName}</td>
                    <td class="fontfamily1">${item.sttBankNo}</td>
                    <td class="fontfamily1">
                        <c:if test="${item.auditState eq '1'}">初始</c:if>
                        <c:if test="${item.auditState eq '2'}">经办通过待审核</c:if>
                        <c:if test="${item.auditState eq '3'}">经办审核不通过</c:if>
                        <c:if test="${item.auditState eq '4'}">审核通过待复核</c:if>
                        <c:if test="${item.auditState eq '5'}">审核不通过</c:if>
                        <c:if test="${item.auditState eq '6'}">复核通过</c:if>
                        <c:if test="${item.auditState eq '7'}">复核不通过</c:if>
                    </td>
                    <td class="fontfamily1">${item.auditCause}</td>
                    <td class="fontfamily1">${item.remark}</td>
                    <td class="fontfamily1" align="left">
                        <c:if test="${item.auditState eq '1'}">
                            <input id="auditAccept${st.index + 1}" class="bluebtn" type="button" value="经办通过"
                                   onclick="audit('${item.id}',${st.index + 1},'00', '${item.cusName}', '${item.sttCalculateTime}', '${item.sttId}');"/>
                            <input id="auditReject${st.index + 1}" class="bluebtn" type="button" value="经办不通过"
                                   onclick="audit('${item.id}',${st.index + 1},'01', '${item.cusName}','${item.sttCalculateTime}', '${item.sttId}');"/>
                        </c:if>
                    </td>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="height: 50px; width:100%; float: right;" id="pageDiv" class="pagination1 btn">
            <table align="right" cellspacing="0" cellpadding="0" height="24">
                <tr>
                    <td align="right">
                        <easipay:pageNum pageSize="${queryDTO.pageSize}" count="${queryDTO.totalCount}"
                                         pageNo="${queryDTO.pageNo}" url="/settle/remit/init/audit"/>
                    </td>
                </tr>
            </table>
            <table align="left" cellspacing="0" cellpadding="0" height="50">
                <tr>
                    <td align="left">
                        <span>当日待结算金额总计:${totalAmount}, 待结算总笔数:${queryDTO.totalCount}</span>
                    </td>
                </tr>
                <tr>
                    <td align="left" height="24" style="width:70%"><span
                            id="selectedSpan">所选待结算金额总计:0.00, 待结算总笔数:0</span>
                    </td>
                </tr>
            </table>
        </div>
        <div style="height: 50px; width:100%; float: left;">
            <table align="right">
                <tr>
                    <td rowspan="2" align="right"><input name="submitbtn" type="button"
                                                         value="批量经办通过" style="width:95px" class="bluebtn batchAudit"
                                                         id="batchAuditAccept" onclick="batchAudit('00');"/>
                    </td>
                    <td rowspan="2" align="right"><input name="submitbtn" type="button"
                                                         value="批量经办不通过" style="width:105px" class="bluebtn batchAudit"
                                                         id="batchAuditReject" onclick="batchAudit('01');"/>
                    </td>
                    <td rowspan="2" align="right"><input name="submitbtn" type="button"
                                                         value="下载" style="width:105px" class="bluebtn batchAudit"
                                                         onclick="download();"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>