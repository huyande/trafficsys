$(document).ready(function() {

   /* $('#dynamic-table').dataTable( {
        "aaSorting": [[ 4, "desc" ]]
    } );*/

    /*
     * 展开关闭图片 
     */
    var nCloneTh = document.createElement( 'th' );
    var nCloneTd = document.createElement( 'td' );
    nCloneTd.innerHTML = '<img src="/img/details_open.png">';
    nCloneTd.className = "center";

    $('#confess-table-info thead tr').each( function () {
        this.insertBefore( nCloneTh, this.childNodes[0] );
    } );

    $('#confess-table-info tbody tr').each( function () {
        this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
    } );

    /*
     * Initialse DataTables, with no sorting on the 'details' column
     */
    //debugger
    var oTable = $('#confess-table-info').dataTable( {
    	//"serverSide": true,  //启用服务器端分页
    	"bProcessing" : false,
    	"aLengthMenu": [   
    				[5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
        "iDisplayLength": 5,
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 0 ] }
        ],
        "aaSorting": [[1, 'asc']],
        "oLanguage": {
                    "sLengthMenu": "_MENU_ 每页显示的条数",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    },
                    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                    "sSearch": "搜索"
                    
        },
		
       "ajax": {
		      "url": "/admin/trafficList",
		      "dataSrc": "data"
		   },
        "aoColumns": [
         	{"data": "id",
         		"render": function(data,type,row,meta){
         			return data = '<img src="/img/details_open.png">'
				}
         	},
         	{"data": "name"},
         	{"data": "licenseid"},
         	{"data": "address"},
         	{"data": "decodes"},
         	{"data": "phonenumber"},
         	{"data": "licenceplate"},
         	{"data":null}
        ],
        "aoColumnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": '<a class="btn btn-success btn-delete_tra" style="margin-left:60px"  href="javascript:;"> 删除 </a>' 
								+'<a class="btn btn-primary btn-export_tra" style="margin-left:60px"  href="javascript:;"> 导出 </a>'
		}]
    });//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象 .api() https://www.cnblogs.com/huim/p/9514337.html

    /* 
     * 点击展开关闭图片
     */
    $(document).on('click','#confess-table-info tbody td img',function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "/img/details_open.png";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "/img/details_close.png";
            oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
        }
    } );
    
    
   /**
    * 点击删除方法
    */
    $('#confess-table-info tbody').on( 'click', '.btn-delete_tra', function () {
    	var nTr = $(this).parents('tr')[0];
    	var aData = oTable.fnGetData( nTr );
    	 $.ajax({
             type: "GET",
             url: "/admin/delete",
             data: {"traId":aData.id},  //传入组装的参数
             dataType: "json",
             success: function (result) {
            	 if(result.data){
            		 $('#confess-table-info').DataTable().ajax.reload()
 				}
             }
         });
    });

    /**
     * 导出 
     */
    $('#confess-table-info tbody').on( 'click', '.btn-export_tra', function () {
		var nTr = $(this).parents('tr')[0];
		var aData = oTable.fnGetData( nTr );
		window.location.href="/admin/export?traId="+aData.id
	});
    
    
} );



function fnFormatDetails ( oTable, nTr )
{
    var aData = oTable.fnGetData( nTr );
    var punishmentways=aData.punishmentways;
    var punishment ="";
    //debugger;
    for(var i=0;i<punishmentways.length;i++){
    	if(punishmentways[i].punishmenttype==1){
    		punishment+="警告"+";"
    	}else if(punishmentways[i].punishmenttype==2){
    		punishment+="罚款"+";"
    	}else {
    		punishment+="暂扣驾驶执照"+";"
    	}
    }
    var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
    sOut += '<tr><td>机动车牌照号:</td><td>'+aData.licenceplate+'</td></tr>';
    sOut += '<tr><td>型号:</td><td>'+aData.modeltype+'</td></tr>';
    sOut += '<tr><td>制造厂:</td><td>'+aData.factoryname+'</td></tr>';
    sOut += '<tr><td>生产日期:</td><td>'+aData.expiringdate+'</td></tr>';
    sOut += '<tr><td>违章日期:</td><td>'+aData.violationdate+'</td></tr>';
    sOut += '<tr><td>时间:</td><td>'+aData.createtime+'</td></tr>';
    sOut += '<tr><td>违章地点：</td><td>'+aData.violationaddress+'</td></tr>';
    sOut += '<tr><td>违章记载：</td><td>'+aData.violationcontent+'</td></tr>';
    sOut += '<tr><td>处罚方式：</td><td>'+punishment+'</td></tr>';
    sOut += '</table>';

    return sOut;
}

