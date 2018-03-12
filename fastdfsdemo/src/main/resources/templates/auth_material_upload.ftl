<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>政策申请材料上传</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/policy.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/uploadfive/uploadifive.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap3.3.7/css/bootstrap.css">

    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${ctx}/js/comm_change.js"></script>
    <script src="${ctx}/js/uploadfive/jquery.uploadifive.js"></script>
    <script src="${ctx}/js/bootstrap3.3.7/js/bootstrap.js"></script>
    <script src="${ctx}/js/bootbox.min.js"></script>

</head>
<body>
<!--网页位置-->
<div class="crumbs mid_box">
    <a href="${ctx }/index.do" class="home"><i></i>首页</a>
    <label></label>
    <a href="#">政策申请材料上传</a>
</div>
<!--政策库star-->
<div class="mid_box clearfix" id="mid_height">
    <!--申请材料上传列表-->
    <div class="policy_list_layout com_shadow fr" style="width:960px">
        <h2><span style="width:116px"><i></i>申请材料</span></h2>
        <dl>
            <dd class="title_dd" style="width:640px">材料名称</dd>
            <dd class="source_dd" style="width:140px">上传材料</dd>
            <dd class="time_dd" style="width:120px"></dd>
        </dl>
        <ul class="policy_bar" style="min-height:380px; width:926px">
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（一）《深圳市中小微企业贷款风险补偿金申请表》及申请 材料提交目录</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file1">上传</a></span></li>
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（二）该笔贷款授信审批相关意见及授信申请材料</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file2">上传</a></span></li>
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（三）该笔贷款借款合同、借据等放款证明材料</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file3">上传</a></span></li>
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（四）银行坏账损失的情况说明及中国人民银行征信报告查 询结果中该笔贷款的分类情况</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file4">上传</a></span></li>
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（五）贷款发放后每期归还本息的情况说明（包括详细数 据）、还款账户流水以及银行催收的相关工作材料;</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file5">上传</a></span></li>
            <li class="clearfix"><a href="https://fopdemo.o-banks.com/policy/badcreditpayment/payment1.do">（六）风险补偿金申请承诺书</a><i class="hot"></i><span class="time" style="width:120px">
                <input type="button" value="查看" data-id=""/></span><span class="source" style="width:140px"><a href="javascript:;" id="file6">上传</a></span></li>
        </ul>
        <div align="right"><a class="btn btn-danger btn-lg" href="#" role="button" id="btn_sbt">提交</a></div>
    </div>
    <!--政策库列表end-->
</div>
<!--政策库end-->
<!--页尾end-->
<!--最右侧小工具-->
<div class="side_Tool">
    <div class="link00"><i></i>在线客服</div>
    <div class="link01"><i></i>客户端
        <div class="lt_box">
            <img src="${ctx}/images/index.png" class="img_qr">
            <p>手机客户端</p>
        </div>
    </div>
    <div class="link02"><i></i>微信服务
        <div class="lt_box">
            <img src="${ctx}/images/wechat_public.jpg" class="img_qr">
            <p>微信公众号</p>
        </div>
    </div>
    <div class="link03"><i></i>帮助中心
    </div>
    <div class="returnTop" style="display: none"><i></i>返回顶部</div>
</div>

<script type="text/javascript">
    $(function () {
        var data = {
            buttonText: "<span class='ss'>上传要求文件<span>",
            fileObjName: "file",
            progressData: 'percentage',
            buttonClass: 'someClass',
            uploadScript: "${ctx}/singleUpload",
            onUploadComplete: function (file, data) {
                data = eval("(" + data + ")");
                if (data.success) {
                    var id = $(this).attr("id");
                    $("#uploadifive-" + id + "-queue").find(".uploadifive-queue-item.complete").remove();
                    $("#uploadifive-" + id + "-queue").closest("li").find(".time :input").attr("data-id",data.fileId);
                    bootbox.alert("上传成功", function () {
                        $(("#uploadifive-" + id + " .ss")).text("重新上传文件");
                    })
                }else {
                    bootbox.alert("上传失败，请联系管理员处理");
                }
            }
        };
        $("#file1,#file2,#file3,#file4,#file5,#file6").uploadifive(data);
        $("#btn_sbt").click(function () {
            var isComplete = true;
            //检测是否完全上传
            $(".policy_bar .clearfix .source .ss").each(function (index, domEle) {
                if ($(domEle).text() == "上传要求文件") {
                    isComplete = false;
                }
            });
            if (!isComplete) {
                bootbox.alert("请检查是否全部上传需要的文件!!");
            }else {
                //进入审核页面
                window.location.href = "${ctx}/audit";
            }
        });
        $(".policy_bar .clearfix .time :input").click(function () {
            var fileId = $(this).data("id");
            console.log(fileId);
            downloadFileByForm(fileId);
        });
    });
    function downloadFileByForm(id) {
        var url = "${downloadUrl}";
        var form = $("<form></form>").attr("action", url).attr("method", "post");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "id").attr("value", id));
        form.appendTo('body').submit().remove();
    }
</script>
</body>
</html>