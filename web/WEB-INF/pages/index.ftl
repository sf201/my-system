<#import "../macros/common.ftl" as common/>
<!DOCTYPE html>
<html lang="en" ng-app="app">
<@common.header/>
<body>
<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">我的系统</a>
        </div>
        <!-- /.navbar-header -->

        <ul  class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void();">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.do"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a ui-sref="main"><i class="fa fa-home  fa-fw"></i> 首页</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cog fa-fw"></i>系统设置<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a ui-sref="areaConfig"><i class="fa  fa-building fa-fw"></i> 区域目录维护</a>
                            </li>
                            <li>
                                <a ui-sref="erectorConfig"><i class="fa fa-users fa-fw"></i> 安装师傅名录</a>
                            </li>
                            <li>
                                <a ui-sref="priceConfig"><i class="fa fa-jpy fa-fw"></i> 打包价外费用名录</a>
                            </li>
                            <li>
                                <a ui-sref="storeConfig"><i class="fa fa-archive fa-fw"></i> 仓库名录</a>
                            </li>
                            <li>
                                <a ui-sref="fixReporterConfig"><i class="fa fa-user-md fa-fw"></i> 报修人员名录</a>
                            </li>
                            <li>
                                <a ui-sref="fixTypeConfig"><i class="fa fa-info-circle fa-fw"></i> 维修品类名录</a>
                            </li>
                            <li>
                                <a ui-sref="fixItemConfig"><i class="fa fa-bars fa-fw"></i> 维修名录</a>
                            </li>
                            <li>
                                <a ui-sref="configTypeConfig"><i class="fa fa-cogs fa-fw"></i> 配置类型名录</a>
                            </li>
                            <li>
                                <a ui-sref="exchangeReasonConfig"><i class="fa fa-reply fa-fw"></i> 退换原因名录</a>
                            </li>
                            <li>
                                <a ui-sref="exchangeRecordConfig"><i class="fa fa-retweet fa-fw"></i> 退换记录名录</a>
                            </li>
                            <li>
                                <a ui-sref="faultConfig"><i class="fa fa-wrench fa-fw"></i> 故障名录</a>
                            </li>
                            <li>
                                <a ui-sref="punishmentConfig"><i class="fa fa-money fa-fw"></i> 扣罚名录</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <#--<li>-->
                        <#--<a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>-->
                        <#--<ul class="nav nav-second-level">-->
                            <#--<li>-->
                                <#--<a href="blank.html">Blank Page</a>-->
                            <#--</li>-->
                            <#--<li>-->
                                <#--<a href="login.html">Login Page</a>-->
                            <#--</li>-->
                        <#--</ul>-->
                        <#--<!-- /.nav-second-level &ndash;&gt;-->
                    <#--</li>-->
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <ui-view></ui-view>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->
<@common.bottom/>
</body>

</html>
