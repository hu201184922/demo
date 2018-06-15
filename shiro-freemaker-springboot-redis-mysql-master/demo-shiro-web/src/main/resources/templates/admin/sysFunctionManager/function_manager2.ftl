<!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--><html lang="en"> <!--<![endif]--><!-- BEGIN HEAD --><head>    <meta charset="utf-8"/>    <title></title>    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7;IE=edge,chrome=1">    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>    <meta content="" name="description"/>    <meta content="" name="author"/>    <!-- BEGIN GLOBAL MANDATORY STYLES -->    <link href="${request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/style.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>    <link href="${request.contextPath}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>    <link href="${request.contextPath}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>    <!-- END GLOBAL MANDATORY STYLES -->    <!-- BEGIN PAGE LEVEL STYLES -->    <link rel="stylesheet" type="text/css" href="${request.contextPath}/media/css/select2_metro.css"/>    <link rel="stylesheet" href="${request.contextPath}/media/css/DT_bootstrap.css"/>    <!-- END PAGE LEVEL STYLES -->    <link rel="shortcut icon" href="${request.contextPath}/media/image/favicon.ico"/></head><!-- END HEAD --><!-- BEGIN BODY --><body class="page-header-fixed"><!-- BEGIN HEADER --><#include "${request.contextPath}/common/header.ftl"/><!-- END HEADER --><!-- BEGIN CONTAINER --><div class="page-container row-fluid">    <!-- BEGIN SIDEBAR --><#include "${request.contextPath}/common/sidebar.ftl"/>    <!-- END SIDEBAR -->    <!-- BEGIN PAGE -->    <div class="page-content">        <!-- BEGIN PAGE CONTAINER-->        <div class="container-fluid">            <!-- BEGIN PAGE HEADER-->                           <div class="row-fluid">                <div class="span12">                    <!-- BEGIN EXAMPLE TABLE PORTLET-->                    <div class="portlet box light-grey">                        <div class="portlet-body">                            <div class="clearfix">                                <div class="btn-group pull-right">                                <button id="searchButton" class="btn green">											查询<i class="icon-search"></i>                                        </button>                                    <button id="addUser" class="btn green">                                        添加 <i class="icon-plus"></i>                                    </button>                                    <button id="editUser" class="btn green">                                        修改 <i class="icon-edit"></i>                                    </button>                                    <button id="delUser" class="btn green">                                        删除 <i class="icon-remove"></i>                                    </button>                                                                     </div>                            </div>                                                        <div id="tree">111</div>                            <table class="table table-striped table-bordered table-hover" id="sample_1">                                <thead>                                <tr>                                    <th class="hidden-480" width="20px;"><input id="selectall" value="xx" onclick="selectAll()"                                                                  name="selectall" type="checkbox"/></th>                                    <th class="hidden-480">功能名称</th>                                    <th class="hidden-480">功能编码</th>                                    <th class="hidden-480">创建时间</th>                                </tr>                                </thead>                            </table>                        </div>                    </div>                    <!-- END EXAMPLE TABLE PORTLET-->                </div>            </div>            <!-- END PAGE CONTENT-->        </div>        <!-- END PAGE CONTAINER-->    </div>    <!-- END PAGE --></div><!-- END CONTAINER --><!-- BEGIN FOOTER --><#include "${request.contextPath}/common/footer.ftl"/><!-- END FOOTER --><!-- BEGIN ADDUSER PAGE --><#include "${request.contextPath}/admin/addUser.ftl"/><!-- END ADDUSER PAGE --><!-- BEGIN EDIGUSER PAGE --><#include "${request.contextPath}/admin/editUser.ftl"/><!-- END EDIGUSER PAGE --><script src="${request.contextPath}/media/admin/sysFunctionManager/function-manager.js"></script><script src="${request.contextPath}/media/js/bootstrap-tree.js"></script> <script>    jQuery(document).ready(function () {        App.init();        FunctionManaged.init();    });        var tree = [  {    text: "Parent 1",    nodes: [      {        text: "Child 1",        nodes: [          {            text: "Grandchild 1"          },          {            text: "Grandchild 2"          }        ]      },      {        text: "Child 2"      }    ]  },  {    text: "Parent 2"  },  {    text: "Parent 3"  },  {    text: "Parent 4"  },  {    text: "Parent 5"  }];   function getTree() {    // Some logic to retrieve, or generate tree structure    alert(tree);    return tree;}      $('#tree').treeview({data: getTree()});     </script><!-- END BODY --></html>