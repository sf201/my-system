<#assign base=request.contextPath />
<#macro header>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="author" content=""/>
<#--<base href="${base}"/>-->
    <link rel="stylesheet" href="${base}/resource/bootstrap/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${base}/resource/css/metisMenu.min.css" type="text/css">
    <link rel="stylesheet" href="${base}/resource/css/sb-admin-2.min.css" type="text/css">
    <link rel="stylesheet" href="${base}/resource/css/ui-grid.min.css" type="text/css">
    <link rel="stylesheet" href="${base}/resource/css/font-awesome/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${base}/css/main.css" type="text/css">
</head>
</#macro>
<#macro bottom>
<script type="text/javascript" src="${base}/resource/javascript/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/angular.min.js"></script>
<script type="text/javascript" src="${base}/resource/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/ui-bootstrap-tpls-2.5.0.min.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/angular-ui-router.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/sb-admin-2.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/metisMenu.min.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/ui-grid.min.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/layer.js"></script>
<script type="text/javascript" src="${base}/resource/javascript/angular-popups.js"></script>
<script type="text/javascript" src="${base}/js/app.js"></script>
<script type="text/javascript" src="${base}/js/invoke.service.js"></script>
<script type="text/javascript" src="${base}/js/dict.service.js"></script>
<script type="text/javascript" src="${base}/js/dict.filter.js"></script>
<script type="text/javascript" src="${base}/js/system.controller.js"></script>
</#macro>