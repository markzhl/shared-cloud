var baseAuthorityResource = {
    baseUrl: "/back/sys/baseResource",
    entity: "baseResource",
    tableId: "baseResourceTable",
    toolbarId: "baseResourceToolbar",
    unique: "id",
    order: "asc",
    currentItem: {}
};
baseAuthorityResource.columns = function () {
    return [{
        checkbox: true
    }, {
        field: 'name',
        title: '按钮'
    }, {
        field: 'code',
        title: '权限编码'
    }, {
        field: 'uri',
        title: '资源路径'
    }, {
        field: 'method',
        title: '方式'
    }];
};
baseAuthorityResource.queryParams = function (params) {
    if (!params)
        return {
            menuId: baseGroup.currentAuthorityMenu.id
        };
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit, //页面大小
        offset: (params.offset / params.limit)+1, //页码
        menuId: baseGroup.currentAuthorityMenu.id
    };
    return temp;
};

baseAuthorityResource.init = function () {
    baseAuthorityResource.table = $('#' + baseAuthorityResource.tableId).bootstrapTable({
        url: baseAuthorityResource.baseUrl + '/page', //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + baseAuthorityResource.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: baseAuthorityResource.order, //排序方式
        queryParams: baseAuthorityResource.queryParams,//传递参数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 20, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: baseAuthorityResource.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        columns: baseAuthorityResource.columns(),
        onUncheck: baseAuthorityResource.onUncheck,
        onCheck: baseAuthorityResource.onCheck,
        checkboxHeader: false
        //onCheckAll: baseAuthorityResource.onCheckAll,
        //onUncheckAll: baseAuthorityResource.onUncheckAll
    });
    $('#' + baseAuthorityResource.tableId).on('load-success.bs.table', function (data) {
        $.get(baseGroup.baseUrl + "/" + baseGroup.currentItem.id + "/authority/resource", null, function (data) {
                if (data.rel) {
                    baseAuthorityResource.table.bootstrapTable("checkBy", {field: "id", values: data.result});
                }
            }
        )
        ;
    });
};

baseAuthorityResource.refresh = function () {
    baseAuthorityResource.table.bootstrapTable("refresh");
}
;
baseAuthorityResource.onCheck = function (row, $element) {
    var menuId = baseGroup.currentAuthorityMenu.id;
    $.post(baseGroup.baseUrl + "/" + baseGroup.currentItem.id + "/authority/resource/add", {
        menuId: menuId,
        resourceId: row.id
    }, function (data) {
        if (!data.rel) {
            layui.use(['form', 'layedit', 'laydate', 'element'], function () {
                var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
                layerTips.tips("后端异常！");
            });
        }
    });
};

baseAuthorityResource.onUncheck = function (row, $element) {
    var menuId = baseGroup.currentAuthorityMenu.id;
    $.ajax({
            url: baseGroup.baseUrl + "/" + baseGroup.currentItem.id + "/authority/resource/remove",
            type: "POST",
            data: {
                menuId: menuId,
                resourceId: row.id
            },
            success: function (data) {
                if (!data.rel) {
                    layui.use(['form', 'layedit', 'laydate', 'element'], function () {
                        var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
                        layerTips.tips("后端异常！");
                    });
                }
            }
        }
    )
    ;
}
;
//baseAuthorityResource.onCheckAll = function (rows) {
//    console.log(rows);
//    var menuId = baseGroup.currentAuthorityMenu.id;
//    var eleIds =[];
//    for(var i=0;i<rows.length;i++){
//        eleIds.push(rows[i].id);
//    }
//    //$.ajax({
//    //        url: baseGroup.baseUrl + "/" + baseGroup.currentItem.id + "/authority/resource/add",
//    //        type: "POST",
//    //        data: {
//    //            menuId: menuId,
//    //            resource: eleIds
//    //        },
//    //        success: function (data) {
//    //            if (!data.rel) {
//    //                layui.use(['form', 'layedit', 'laydate', 'element'], function () {
//    //                    var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
//    //                    layerTips.tips("后端异常！");
//    //                });
//    //            }
//    //        }
//    //    }
//    //)
//    //;
//};
//baseAuthorityResource.onUncheckAll = function (rows) {
//    var menuId = baseGroup.currentAuthorityMenu.id;
//    var eleIds =[];
//    for(var i=0;i<rows.length;i++){
//        eleIds.push(rows[i].id);
//    }
//    //$.ajax({
//    //        url: baseGroup.baseUrl + "/" + baseGroup.currentItem.id + "/authority/resource/remove",
//    //        type: "POST",
//    //        data: {
//    //            menuId: menuId,
//    //            resource: eleIds
//    //        },
//    //        success: function (data) {
//    //            if (!data.rel) {
//    //                layui.use(['form', 'layedit', 'laydate', 'element'], function () {
//    //                    var layerTips = parent.layer === undefined ? layui.layer : parent.layer;
//    //                    layerTips.tips("后端异常！");
//    //                });
//    //            }
//    //        }
//    //    }
//    //)
//    //;
//};