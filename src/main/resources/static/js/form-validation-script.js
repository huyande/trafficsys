var Script = function () {

    $.validator.setDefaults({
        submitHandler: function() { 
        	//获取表单数据 JSON.stringify($("#trafficFrom").serializeJson())
        	console.info($("#trafficFrom").serializeJson());
        	$.ajax({
        		url:'/admin/save',
        		type:'post',
        		data:JSON.stringify($("#trafficFrom").serializeJson()),
        		contentType:'application/json',
        		dataType:'json',
        		success:function(result){
        			if(result.data){
        				 $('#confess-table-info').DataTable().ajax.reload()
        				 //清空表单
        				 $("#trafficFrom")[0].reset()
        				 //隐藏弹框
        				 $('#myModal2').modal('hide')
        			}else{
        				alert("未知错误 新增shibai")
        			}
        		}
        		
        	});
        }
    });

    $().ready(function() {

        // validate signup form on keyup and submit
        $("#trafficFrom").validate({
            rules: {
            	name: "required",
            	licenseid: "required",
            	address: {
                    required: true,
                    minlength: 2
                },
                decodes: {
                    required: true,
                    minlength: 6,
                    digits:true,
                    rangelength:[6,6]
                },
                phonenumber: {
                    required: true,
                    digits:true,
                    rangelength:[11,11]
                },
                licenceplate: {
                    required: true
                },
                modeltype: {
                    required: true
                },
                factoryname: "required",
                violationaddress: {
                    required: true,
                    minlength: 2
                },
                violationcontent: {
                    required: true
                },
                punishmentways:{
                	  required: true,
                	  rangelength:[2,4]
                }
            },
            messages: {
            	name: "输入违章人姓名",
            	licenseid: "输入驾驶证号",
            	address: {
                    required: "输入地址",
                    minlength: "长度大于2"
                },
                decodes: {
                    required: "输入邮编",
                    minlength: "长度是6",
                    digits:"必须是整数",
                    rangelength:"必须是6位"
                },
                phonenumber: {
                    required: "输入手机号",
                    digits:"必须是整数",
                    rangelength:"长度是11位"
                    
                },
                licenceplate: "输入车牌号",
                modeltype: {
                    required: "输入车型号"
                },
                factoryname :"输入制造商",
                violationaddress:{
                	required: "输入违章地点"
                },
                violationcontent: {
                    required: "输入违章记载"
                },
                punishmentways:{
                	  required: "必须选一个",
                	  rangelength:"必须选一个"
                }
                
            }
        });

    });

}();