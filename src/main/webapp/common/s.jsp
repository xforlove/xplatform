<%@ page contentType="text/html;charset=UTF-8"%>

<!-- Bootstrap 核心 CSS 文件 -->
<link href="${ctx }/s/bootstrap3/css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- Bootstrap 可选 Theme 文件 -->
<link href="${ctx }/s/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">

<!-- jQuery DataTables 核心 CSS 文件 -->
<link href="${ctx }/s/dataTables/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">

<!-- jQuery DataTables 与 Bootstrap3 集成文件 -->
<link href="${ctx }/s/dataTables/css/dataTables.bootstrap.css" rel="stylesheet" media="screen">

<!-- Bootstrap Multiselect 核心 CSS 文件 -->
<link href="${ctx }/s/multiselect/css/bootstrap-multiselect.css" rel="stylesheet" media="screen">
<link href="${ctx }/s/multiselect/css/prettify.css" rel="stylesheet" media="screen">

<!-- jQuery Uploadify 核心 CSS 文件 -->
<link href="${ctx }/s/uploadify/uploadify.css" rel="stylesheet" media="screen">

<!-- Customer 布局文件 -->
<link href="${ctx }/s/customer/css/layout.css" rel="stylesheet" media="screen">

<!-- Customer 全局样式 -->
<link href="${ctx }/s/customer/css/rock.css" rel="stylesheet" media="screen">

<!-- jQuery文件, 请务必在 bootstrap.min.js 之前引入 -->
<script src="${ctx }/s/jquery/jquery-1.9.1.min.js"></script>

<!-- Bootstrap 核心 JavaScript 文件 -->
<script src="${ctx }/s/bootstrap3/js/bootstrap.min.js"></script>

<!-- jQuery DataTables 核心 JavaScript 文件 -->
<script src="${ctx }/s/dataTables/js/jquery.dataTables.min.js"></script>

<!-- jQuery DataTables 与 Bootstrap3 集成文件 -->
<script src="${ctx }/s/dataTables/js/dataTables.bootstrap.js"></script>

<!-- Bootstrap Multiselect 核心 JavaScript 文件 -->
<script src="${ctx }/s/multiselect/js/bootstrap-multiselect.js"></script>
<script src="${ctx }/s/multiselect/js/prettify.js"></script>

<!-- jQuery Sliding Message 核心 JavaScript 文件 -->
<script src="${ctx }/s/slidingMessage/jquery.slidingmessage.min.js"></script>

<!-- jQuery Uploadify 核心 JavaScript 文件-->
<script src="${ctx }/s/uploadify/jquery.uploadify.v2.1.4.js"></script>
<script src="${ctx }/s/uploadify/swfobject.js"></script>

<!-- Customer 脚本 -->
<script src="${ctx }/s/customer/js/chkbox.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->    


<script type="text/javascript">
<!--
$(document).ready(function() {
	
	$.showMessage($('#m-success-message').html(), {
		position: 'bottom',
        size: '50',
        fontSize: '20px'
    });	
	 
	$("#ROCK_DT").dataTable({
		"dom" : "<'searchPanel'><'space'><fl<t>ip>",
		"pagingType": "simple_numbers", /* simple_numbers, full_numbers */
		"language": {
			"search": "搜索",
            "lengthMenu": "每页显示 _MENU_ 条记录",
            "zeroRecords": "抱歉，查询结果为空",
            "paginate": {
            	"first": "首页",
            	"previous": "上一页",
            	"next": "下一页",
            	"last": "末页"
            },
            "info": "当前显示第 _PAGE_ / _PAGES_ 页",
            "infoEmpty": "无数据",
            "infoFiltered": "(filtered from _MAX_ total records)"
        }
	});
	
	$("div.searchPanel").html($("#ROCK_DT_SEARCH").innerHTML);
	$("div.space").html("<br />");
	
	$("#ROCK_DT_NFNP").dataTable({
		"dom" : "<'searchPanel'><'space'><<t>>",
		"pagingType": "simple_numbers", /* simple_numbers, full_numbers */
		"pageLength": 1000,
		"language": {
			"search": "搜索",
            "lengthMenu": "每页显示 _MENU_ 条记录",
            "zeroRecords": "抱歉，查询结果为空",
            "paginate": {
            	"first": "首页",
            	"previous": "上一页",
            	"next": "下一页",
            	"last": "末页"
            },
            "info": "当前显示第 _PAGE_ / _PAGES_ 页",
            "infoEmpty": "无数据",
            "infoFiltered": "(filtered from _MAX_ total records)"
        }
	});
	
	$("div.searchPanel").html($("#ROCK_DT_SEARCH").innerHTML);
	$("div.space").html("<br />");
	
	$("#ROCK_SDT").dataTable({
		"dom" : "<t>",
		"pagingType": "simple_numbers", /* simple_numbers, full_numbers */
		"pageLength": 1000,
		"language": {
			"search": "搜索",
            "lengthMenu": "每页显示 _MENU_ 条记录",
            "zeroRecords": "抱歉，查询结果为空",
            "paginate": {
            	"first": "首页",
            	"previous": "上一页",
            	"next": "下一页",
            	"last": "末页"
            },
            "info": "当前显示第 _PAGE_ / _PAGES_ 页",
            "infoEmpty": "无数据",
            "infoFiltered": "(filtered from _MAX_ total records)"
        }
	});
	
	window.prettyPrint() && prettyPrint();
	
	$('.multiselect').multiselect({
		includeSelectAllOption: false,
		enableFiltering: true,
		numberDisplayed: 3,
		maxHeight: 280,
		buttonText: function(options, select) {
            if (options.length == 0) {
                return this.nonSelectedText + ' <b class="caret"></b>';
            } else {
                if (options.length > this.numberDisplayed) {
                    return options.length + ' ' + this.nSelectedText + ' <b class="caret"></b>';
                } else {
                    var selected = '';
                    options.each(function() {
                        var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).html();
                        selected += label + ', ';
                    });
                    return selected.substr(0, selected.length - 2) + ' <b class="caret"></b>';
                }
            }
        }
		
	});
	
	$('.singleselect').multiselect({
		enableFiltering: true,
		maxHeight: 200,
    	onChange: function(option, checked) {
	        var values = [];
	        $('.singleselect option').each(function() {
	            if ($(this).val() !== option.val()) {
	            values.push($(this).val());
	            }
	        });
	 
	        $('.singleselect').multiselect('deselect', values);
	    }
	});
});
//-->
</script>
