<div>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><i class="fa fa-jpy fa-fw"></i> 设备名录</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <div class="row">
        <div class="col-sm-12">
            <button class="btn btn-primary" ng-click="add()"><i class="fa fa-plus fa-fw"></i>新增</button>
            <button class="btn btn-danger" ng-click="delete()"><i class="fa fa-minus fa-fw"></i>删除</button>
            <button class="btn btn-success" ng-click="save()"><i class="fa fa-save fa-fw"></i>保存</button>
        </div>
    </div>

    <div ui-grid="gridOptions" ui-grid-edit ui-grid-row-edit ui-grid-save-state ui-grid-cellNav class="myGrid"></div>

</div>
<!-- /.table-responsive -->