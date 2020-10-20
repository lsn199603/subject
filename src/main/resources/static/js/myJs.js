//高校名称下拉框
function schoolSelect(){
    layui.use(['form','jquery'],function () {
    var $ = layui.$,
        form = layui.form;

    $('#school').empty();
    $(function () {
        $.ajax({
            type: "POST",
            url: "/schoolinformation/querySchoolName",
            dataType: "json",
            cache: false,
            success: function (data) {
                $('#school').append(new Option());
                $.each(data, function (index, item) {
                        $('#school').append(new Option(item.siName, item.id));

                });
                form.render('select');
            }, error: function () {
                alert("查询失败");
            }
        });
    });

});
}

//高校名称添加联动显示高校代码
function siIdSelect() {
    layui.use(['form','jquery'],function () {
        var $ = layui.$,
            form = layui.form;

        form.on('select(Type_filter)', function(o){
            id = o.value;
            $.get('/schoolinformation/getText/'+id,function (r) {
                $('[name=siId]').val(r.siId);
            },'json')
        });
    });
    }

//高校名称修改联动显示高校代码
function siIdSelectEdit() {
    layui.use(['form','jquery'],function () {
        var $ = layui.$,
            form = layui.form;

        form.on('select(Type_filter)', function(o){
            $('[name=siId]').val(o.value);
        });
    });
}

//学科平台目录树
function spmTree() {
    layui.extend({
        dtree: '../layui/layui_exts/layui_ext/dtree/dtree'
    }).use(['dtree', 'layer', 'jquery'], function () {
        var dtree = layui.dtree,
            layer = layui.layer,
            $ = layui.jquery;

        //学科平台下拉树
        dtree.render({
            elem: "#spmTree",
            isJump: true,
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {message: "msg", statusCode: 0},
            skin: "zdy",
            initLevel: 1,
            iconfont: ["dtreefont", "layui-icon", "iconfont"],
            iconfontStyle: [{
                snode: {
                    leaf: "dtree-icon-xiangmuxiaoxi",
                    node: {
                        open: "icon-appreciate"
                    }
                }
            }],
            url: "/subjectplatformmenu/queryAll"
        });
        $("#spmenu").on("click", function () {
            $(this).toggleClass("layui-form-selected");
            $("#spmtest").toggleClass("layui-show layui-anim layui-anim-upbit");
        });

        dtree.on("node(spmTree)", function (obj) {
            $("#input_spm").val(obj.param.context);
            $("#spmenu").toggleClass("layui-form-selected");
            $("#spmtest").toggleClass("layui-show layui-anim layui-anim-upbit");
            $("#spmId").val(obj.param.nodeId);
        });
    });
}

//学科目录树
function smTree() {
    layui.extend({
        dtree: '../layui/layui_exts/layui_ext/dtree/dtree'
    }).use(['dtree', 'layer', 'jquery'], function () {
        var dtree = layui.dtree,
            layer = layui.layer,
            $ = layui.jquery;
        dtree.render({
            elem: "#smTree",
            isJump: true,
            dataStyle: "layuiStyle",
            dataFormat: "list",
            response: {message: "msg", statusCode: 0},
            skin: "zdy",
            initLevel: 1,
            iconfont: ["dtreefont", "layui-icon", "iconfont"],
            iconfontStyle: [{
                snode: {
                    leaf: "dtree-icon-xiangmuxiaoxi",
                    node: {
                        open: "icon-appreciate"
                    }
                }
            }],
            url: "/subjectmenu/queryAll"
        });
        $("#smenu").on("click", function () {
            $(this).toggleClass("layui-form-selected");
            $("#smtest").toggleClass("layui-show layui-anim layui-anim-upbit");
        });

        dtree.on("node(smTree)", function (obj) {
            $("#input_sm").val(obj.param.context);
            $("#smenu").toggleClass("layui-form-selected");
            $("#smtest").toggleClass("layui-show layui-anim layui-anim-upbit");
            $("#smId").val(obj.param.nodeId);
        });

    });
}

//年选择器
function timeData() {
    layui.use(['laydate','jquery'],function () {
        var
            $ = layui.$,
            laydate = layui.laydate;

        laydate.render({
            elem: '#dTime',
            type: 'year',
            theme: 'molv',
            trigger: 'click',
            format: 'yyyy年'
        });

    });
}

//年范围选择器
function yearData() {
    layui.use(['laydate','jquery'],function () {
        var
            $ = layui.$,
            laydate = layui.laydate;
            laydate.render({
            elem: '#trDate',
            type: 'year',
            range: true,
            theme: 'molv',
            trigger: 'click',
            format: 'yyyy年',
        });

    });
}