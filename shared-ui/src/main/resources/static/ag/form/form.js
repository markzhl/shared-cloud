var form = {
    baseUrl: "/back/form",
    entity: "form",
    tableId: "formTable",
    toolbarId: "toolbar",
    unique: "id",
    order: "asc",
    currentItem: {}
};
form.columns = function () {
    return [{
        checkbox: true
    }, {
        field: 'formName',
        title: '表单名称'
    }, {
        field: 'formDesc',
        title: '表单描述'
    }];
};
form.queryParams = function (params) {
    if (!params)
        return {
            name: $("#name").val()
        };
    var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit, //页面大小
        offset: (params.offset / params.limit)+1, //页码
        name: $("#name").val()
    };
    return temp;
};

form.init = function () {

	form.table = $('#' + form.tableId).bootstrapTable({
        url: form.baseUrl + '/page', //请求后台的URL（*）
        method: 'get', //请求方式（*）
        toolbar: '#' + form.toolbarId, //工具按钮用哪个容器
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: false, //是否启用排序
        sortOrder: form.order, //排序方式
        queryParams: form.queryParams,//传递参数（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: false, //是否显示所有的列
        showRefresh: true, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true, //是否启用点击选中行
        uniqueId: form.unique, //每一行的唯一标识，一般为主键列
        showToggle: true, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        columns: form.columns()
    });
};
form.select = function (layerTips) {
    var rows = form.table.bootstrapTable('getSelections');
    if (rows.length == 1) {
        form.currentItem = rows[0];
        return true;
    } else {
        layerTips.msg("请选中一行");
        return false;
    }
};

layui.use(['form', 'layedit', 'laydate'], function () {
	form.init();

    var editIndex;
    var layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        formDom = layui.form(),
        layedit = layui.layedit,
        laydate = layui.laydate;
    var addBoxIndex = -1;
    //初始化页面上面的按钮事件
    $('#btn_query').on('click', function () {
        form.table.bootstrapTable('refresh', form.queryParams());
    });

    $('#btn_add').on('click', function () {
        if (addBoxIndex !== -1)
            return;
        //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
        $.get(form.entity + '/edit', null, function (formDom) {
            addBoxIndex = layer.open({
                type: 1,
                title: '添加表单',
                content: formDom,
                btn: ['保存', '取消'],
                shade: false,
                offset: ['20px', '20%'],
                area: ['600px', '400px'],
                maxmin: true,
                yes: function (index) {
                    layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=edit]').click();
                },
                full: function (elem) {
                    var win = window.top === window.self ? window : parent.window;
                    $(win).on('resize', function () {
                        var $this = $(this);
                        elem.width($this.width()).height($this.height()).css({
                            top: 0,
                            left: 0
                        });
                        elem.children('div.layui-layer-content').height($this.height() - 95);
                    });
                },
                success: function (layero, index) {
                    var formDom = layui.form();
                    editIndex = layedit.build('formDesc_editor');
                    formDom.render();
                    formDom.on('submit(edit)', function (data) {
                        $.ajax({
                            url: form.baseUrl,
                            type: 'post',
                            data: data.field,
                            dataType: "json",
                            success: function () {
                                layerTips.msg('保存成功');
                                layerTips.close(index);
                                location.reload();
                            }

                        });
                        //这里可以写ajax方法提交表单
                        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                    });
                    //console.log(layero, index);
                },
                end: function () {
                    addBoxIndex = -1;
                }
            });
        });
    });
    $('#btn_edit').on('click', function () {
        if (form.select(layerTips)) {
            var id = form.currentItem.id;
            $.get(form.baseUrl + '/' + id, null, function (data) {
                var result = data.result;
                $.get(form.entity+'/edit', null, function (formDom) {
                    layer.open({
                        type: 1,
                        title: '编辑表单',
                        content: formDom,
                        btn: ['保存', '取消'],
                        shade: false,
                        offset: ['20px', '20%'],
                        area: ['600px', '400px'],
                        maxmin: true,
                        yes: function (index) {
                            //触发表单的提交事件
                            layedit.sync(editIndex);
                            $('form.layui-form').find('button[lay-filter=edit]').click();
                        },
                        full: function (elem) {
                            var win = window.top === window.self ? window : parent.window;
                            $(win).on('resize', function () {
                                var $this = $(this);
                                elem.width($this.width()).height($this.height()).css({
                                    top: 0,
                                    left: 0
                                });
                                elem.children('div.layui-layer-content').height($this.height() - 95);
                            });
                        },
                        success: function (layero, index) {
                            var formDom = layui.form();
                            setFromValues(layero, result);
                            layero.find('#description_editor').val(result.description);
                            editIndex = layedit.build('description_editor');
                            formDom.render();
                            layero.find(":input[name='formName']").attr("disabled", "");
                            formDom.on('submit(edit)', function (data) {
                                $.ajax({
                                    url: form.baseUrl + "/" + result.id,
                                    type: 'put',
                                    data: data.field,
                                    dataType: "json",
                                    success: function () {
                                        layerTips.msg('更新成功');
                                        layerTips.close(index);
                                        location.reload();
                                    }

                                });
                                //这里可以写ajax方法提交表单
                                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                            });
                        }
                    });
                });
            });
        }
    });
    $('#btn_design').on('click', function () {
        if (form.select(layerTips)) {
            var id = form.currentItem.id;
            $.get(form.baseUrl + '/' + id, null, function (data) {
                var result = data.result;
                $.get(form.entity+'/design', null, function (formDom) {
                    var index = layer.open({
                    	type: 1,
                        title: '设计表单',
                        content: formDom,
                        btn: ['保存', '预览', '取消'],
                        shade: false,
                        offset: ['20px', '20%'],
                        area: ['600px', '400px'],
                        maxmin: true,
                        zIndex: '50',
                        yes: function (index) {
                            //触发表单的提交事件
                            console.log(leipiFormDesign);
                            if(leipiFormDesign && typeof leipiFormDesign.fnCheckForm == "function"){
                            	leipiFormDesign.fnCheckForm("save");
                            }
                        },
                        btn2: function (index) {
                        	if(leipiFormDesign && typeof leipiFormDesign.fnReview == "function"){
                            	leipiFormDesign.fnReview();
                            }
                        },
                        btn3: function (index) {
                        	console.log("click 取消");
                        	UE.delEditor("myFormDesign");
                        },
                        cancel: function(){ 
                        	console.log("click X");
                        	UE.delEditor("myFormDesign");
                        },
                        full: function (elem) {
                            var win = window.top === window.self ? window : parent.window;
                            $(win).on('resize', function () {
                                var $this = $(this);
                                elem.width($this.width()).height($this.height()).css({
                                    top: 0,
                                    left: 0
                                });
                                elem.children('div.layui-layer-content').height($this.height() - 95);
                            });
                        },
                        success: function (layero, index) {
                            var formDom = layui.form();
                            setFromValues(layero, result);
                            debugger
                            layero.find("#myFormDesign").val(result.content);
                            leipiEditor = UE.getEditor('myFormDesign',{
                                //allowDivTransToP: false,//阻止转换div 为p
                                toolleipi:true,//是否显示，设计器的 toolbars
                                textarea: 'design_content',   
                                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                                toolbars:[[
                                'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells', '|']],
                                //focus时自动清空初始化时的内容
                                //autoClearinitialContent:true,
                                //关闭字数统计
                                wordCount:false,
                                //关闭elementPath
                                elementPathEnabled:false,
                                //默认的编辑区域高度
                                initialFrameHeight: 750
                                ,iframeCssUrl:"css/bootstrap.min.css" //引入自身 css使编辑器兼容你网站css
                                //更多其他参数，请参考ueditor.config.js中的配置项
                            });
		                    leipiFormDesign = {
		                        /*执行控件*/
		                        exec : function (method) {
		                            leipiEditor.execCommand(method);
		                        },
		                        /*
		                            Javascript 解析表单
		                            template 表单设计器里的Html内容
		                            fields 字段总数
		                        */
		                       parse_form:function(template,fields)
		                        {
		                            //正则  radios|checkboxs|select 匹配的边界 |--|  因为当使用 {} 时js报错
		                            var preg =  /(\|-<span(((?!<span).)*leipiplugins=\"(radios|checkboxs|select)\".*?)>(.*?)<\/span>-\||<(img|input|textarea|select).*?(<\/select>|<\/textarea>|\/>))/gi,preg_attr =/(\w+)=\"(.?|.+?)\"/gi,preg_group =/<input.*?\/>/gi;
		                            if(!fields) fields = 0;
		
		                            var template_parse = template,template_data = new Array(),add_fields=new Object(),checkboxs=0;
		
		                            var pno = 0;
		                            template.replace(preg, function(plugin,p1,p2,p3,p4,p5,p6){
		                                var parse_attr = new Array(),attr_arr_all = new Object(),name = '', select_dot = '' , is_new=false;
		                                var p0 = plugin;
		                                var tag = p6 ? p6 : p4;
		                                //alert(tag + " \n- t1 - "+p1 +" \n-2- " +p2+" \n-3- " +p3+" \n-4- " +p4+" \n-5- " +p5+" \n-6- " +p6);
		
		                                if(tag == 'radios' || tag == 'checkboxs')
		                                {
		                                    plugin = p2;
		                                }else if(tag == 'select')
		                                {
		                                    plugin = plugin.replace('|-','');
		                                    plugin = plugin.replace('-|','');
		                                }
		                                plugin.replace(preg_attr, function(str0,attr,val) {
		                                        if(attr=='name')
		                                        {
		                                            if(val=='leipiNewField')
		                                            {
		                                                is_new=true;
		                                                fields++;
		                                                val = 'data_'+fields;
		                                            }
		                                            name = val;
		                                        }
		                                        
		                                        if(tag=='select' && attr=='value')
		                                        {
		                                            if(!attr_arr_all[attr]) attr_arr_all[attr] = '';
		                                            attr_arr_all[attr] += select_dot + val;
		                                            select_dot = ',';
		                                        }else
		                                        {
		                                            attr_arr_all[attr] = val;
		                                        }
		                                        var oField = new Object();
		                                        oField[attr] = val;
		                                        parse_attr.push(oField);
		                                }) 
		                                /*alert(JSON.stringify(parse_attr));return;*/
		                                 if(tag =='checkboxs') /*复选组  多个字段 */
		                                 {
		                                    plugin = p0;
		                                    plugin = plugin.replace('|-','');
		                                    plugin = plugin.replace('-|','');
		                                    var name = 'checkboxs_'+checkboxs;
		                                    attr_arr_all['parse_name'] = name;
		                                    attr_arr_all['name'] = '';
		                                    attr_arr_all['value'] = '';
		                                    
		                                    attr_arr_all['content'] = '<span leipiplugins="checkboxs"  title="'+attr_arr_all['title']+'">';
		                                    var dot_name ='', dot_value = '';
		                                    p5.replace(preg_group, function(parse_group) {
		                                        var is_new=false,option = new Object();
		                                        parse_group.replace(preg_attr, function(str0,k,val) {
		                                            if(k=='name')
		                                            {
		                                                if(val=='leipiNewField')
		                                                {
		                                                    is_new=true;
		                                                    fields++;
		                                                    val = 'data_'+fields;
		                                                }
		
		                                                attr_arr_all['name'] += dot_name + val;
		                                                dot_name = ',';
		
		                                            }
		                                            else if(k=='value')
		                                            {
		                                                attr_arr_all['value'] += dot_value + val;
		                                                dot_value = ',';
		
		                                            }
		                                            option[k] = val;    
		                                        });
		                                        
		                                        if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
		                                        attr_arr_all['options'].push(option);
		                                        //if(!option['checked']) option['checked'] = '';
		                                        var checked = option['checked'] !=undefined ? 'checked="checked"' : '';
		                                        attr_arr_all['content'] +='<input type="checkbox" name="'+option['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';
		
		                                        if(is_new)
		                                        {
		                                            var arr = new Object();
		                                            arr['name'] = option['name'];
		                                            arr['leipiplugins'] = attr_arr_all['leipiplugins'];
		                                            add_fields[option['name']] = arr;
		
		                                        }
		
		                                    });
		                                    attr_arr_all['content'] += '</span>';
		
		                                    //parse
		                                    template = template.replace(plugin,attr_arr_all['content']);
		                                    template_parse = template_parse.replace(plugin,'{'+name+'}');
		                                    template_parse = template_parse.replace('{|-','');
		                                    template_parse = template_parse.replace('-|}','');
		                                    template_data[pno] = attr_arr_all;
		                                    checkboxs++;
		
		                                 }else if(name)
		                                {
		                                    if(tag =='radios') /*单选组  一个字段*/
		                                    {
		                                        plugin = p0;
		                                        plugin = plugin.replace('|-','');
		                                        plugin = plugin.replace('-|','');
		                                        attr_arr_all['value'] = '';
		                                        attr_arr_all['content'] = '<span leipiplugins="radios" name="'+attr_arr_all['name']+'" title="'+attr_arr_all['title']+'">';
		                                        var dot='';
		                                        p5.replace(preg_group, function(parse_group) {
		                                            var option = new Object();
		                                            parse_group.replace(preg_attr, function(str0,k,val) {
		                                                if(k=='value')
		                                                {
		                                                    attr_arr_all['value'] += dot + val;
		                                                    dot = ',';
		                                                }
		                                                option[k] = val;    
		                                            });
		                                            option['name'] = attr_arr_all['name'];
		                                            if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
		                                            attr_arr_all['options'].push(option);
		                                            //if(!option['checked']) option['checked'] = '';
		                                            var checked = option['checked'] !=undefined ? 'checked="checked"' : '';
		                                            attr_arr_all['content'] +='<input type="radio" name="'+attr_arr_all['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';
		
		                                        });
		                                        attr_arr_all['content'] += '</span>';
		
		                                    }else
		                                    {
		                                        attr_arr_all['content'] = is_new ? plugin.replace(/leipiNewField/,name) : plugin;
		                                    }
		                                    //attr_arr_all['itemid'] = fields;
		                                    //attr_arr_all['tag'] = tag;
		                                    template = template.replace(plugin,attr_arr_all['content']);
		                                    template_parse = template_parse.replace(plugin,'{'+name+'}');
		                                    template_parse = template_parse.replace('{|-','');
		                                    template_parse = template_parse.replace('-|}','');
		                                    if(is_new)
		                                    {
		                                        var arr = new Object();
		                                        arr['name'] = name;
		                                        arr['leipiplugins'] = attr_arr_all['leipiplugins'];
		                                        add_fields[arr['name']] = arr;
		                                    }
		                                    template_data[pno] = attr_arr_all;
		
		                                   
		                                }
		                                pno++;
		                            })
		                            var parse_form = new Object({
		                                'fields':fields,//总字段数
		                                'template':template,//完整html
		                                'parse':template_parse,//控件替换为{data_1}的html
		                                'data':template_data,//控件属性
		                                'add_fields':add_fields//新增控件
		                            });
		                            return JSON.stringify(parse_form);
		                        },
		                        /*type  =  save 保存设计 versions 保存版本  close关闭 */
		                        fnCheckForm : function ( type ) {
		                            if(leipiEditor.queryCommandState( 'source' ))
		                                leipiEditor.execCommand('source');//切换到编辑模式才提交，否则有bug
		                                
		                            if(leipiEditor.hasContents()){
		                                leipiEditor.sync();/*同步内容*/
		
		                                alert("你点击了保存,这里可以异步提交，请自行处理...."+type);
		                                //--------------以下仅参考-----------------------------------------------------------------------------------------------------
		                                var type_value='',formid=$("#id").val(),fields=$("#fields").val(),formeditor='';
		
		                                if( typeof type!=='undefined' ){
		                                    type_value = type;
		                                }
		                                //获取表单设计器里的内容
		                                formeditor=leipiEditor.getContent();
		                                //解析表单设计器控件
		                                var parse_form = this.parse_form(formeditor,fields);
		                                console.log(parse_form);
		                                 //异步提交数据
		                                 $.ajax({
		                                    type: 'POST',
		                                    url : '/back/form/'+formid+"/design",
		                                    //dataType : 'json',
		                                    data : {'type' : type_value,'parseForm':parse_form},
		                                    success : function(data){
		                                        /* if(confirm('查看js解析后，提交到服务器的数据，请临时允许弹窗'))
		                                        {
		                                            win_parse=window.open('','','width=800,height=600');
		                                            //这里临时查看，所以替换一下，实际情况下不需要替换  
		                                            data  = data.replace(/<\/+textarea/,'&lt;textarea');
		                                            win_parse.document.write('<textarea style="width:100%;height:100%">'+data+'</textarea>');
		                                            win_parse.focus();
		                                        } */
		                                        
		                                        if (data.rel == true) {
		                                            layerTips.msg("保存成功！");
		                                            location.reload();
		                                        } else {
		                                            layerTips.msg("保存失败！");
		                                            location.reload();
		                                        }
		                                    }
		                                });
		                                
		                            } else {
		                                alert('表单内容不能为空！')
		                                $('#submitbtn').button('reset');
		                                return false;
		                            }
		                        } ,
		                        /*预览表单*/
		                        fnReview : function (){
		                            if(leipiEditor.queryCommandState( 'source' ))
		                                leipiEditor.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/
		                                
		                            if(leipiEditor.hasContents()){
		                                leipiEditor.sync();       /*同步内容*/
		                                
		                                 alert("你点击了预览,请自行处理....");
		                                return false;
		                                //--------------以下仅参考-------------------------------------------------------------------
		
		
		                                /*设计form的target 然后提交至一个新的窗口进行预览*/
		                                document.saveform.target="mywin";
		                                window.open('','mywin',"menubar=0,toolbar=0,status=0,resizable=1,left=0,top=0,scrollbars=1,width=" +(screen.availWidth-10) + ",height=" + (screen.availHeight-50) + "\"");
		
		                                document.saveform.action="/index.php?s=/index/preview.html";
		                                document.saveform.submit(); //提交表单
		                            } else {
		                                alert('表单内容不能为空！');
		                                return false;
		                            }
		                        }
		                    };
                        }
                    });
                    layer.full(index);
                });
            });
        }
    });
    $('#btn_del').on('click', function () {
        if (form.select(layerTips)) {
            var id = form.currentItem.id;
            layer.confirm('确定删除数据吗？', null, function (index) {
                $.ajax({
                    url: form.baseUrl + "/" + id,
                    type: "DELETE",
                    success: function (data) {
                        if (data.rel == true) {
                            layerTips.msg("移除成功！");
                            location.reload();
                        } else {
                            layerTips.msg("移除失败！")
                            location.reload();
                        }
                    }
                });
                layer.close(index);
            });
        }
    });
});