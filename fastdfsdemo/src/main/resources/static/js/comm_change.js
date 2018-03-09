// JavaScript Document
(function($){
$(function(){ 
//企业名称弹出联想词
//$('.item_name').click(function(){
//		 $(".sug").toggle()		 
//	});
	$(document).click(function(e){ 
		var target = $(e.target); 
		if(target.closest(".item_name").length == 0){ 
		$(".sug").hide(); 
		} 
	 });
// 返回顶部
$(function(){
	$(window).scroll(function(){
		var scrollt = document.body.scrollTop+document.documentElement.scrollTop;
		if( scrollt >100 ){

			$(".returnTop").show();
		}else{
			$(".returnTop").stop().hide();
		}
	});

	$(".returnTop").click(function(){
		$("html,body").animate({scrollTop:"0px"},300);
	});
});

//政策库市级全选
function checkAll(x,y){
	$( '#' + x ).click(function(){
		$(this).parents("dd").addClass("check_slide");
		$(this).parent().siblings("ul").slideDown();
		$('input[name = ' + y + ' ]').attr("checked",this.checked); 
	});
	var $subBox = $('input[name = ' + y + ' ]');
	$subBox.click(function(){
		$( '#' + x ).attr("checked",$subBox.length == $('input[name = ' + y + ' ]:checked').length ? true : false);
	});
}
checkAll('check_all_city','citySource');
checkAll('check_all_prop','areaSource');
//市级展开收起
function polToggle(x){
	$( '#' + x ).click(function(){
		$(this).siblings("ul").slideToggle();
		$(this).parents("dd").toggleClass("check_slide");
	});
}
polToggle('check_city_clo');	
//区级展开收起
$("#check_all_prop").click(function(){
	$("#check_prop_clo").removeClass("prop_i");
	})
$("#check_prop_clo").click(function(){
	if($(this).hasClass("prop_i")){
		$(this).siblings("ul").slideDown();
		$(this).parents("dd").addClass("check_slide");
		$(this).removeClass("prop_i");
	}else{
		$(this).siblings("ul").slideUp();
		setTimeout( function(){ $("#check_prop_clo").parents("dd").toggleClass("check_slide")},400);
		$(this).addClass("prop_i");
		}
	});

//政策是否在办理时限内
$(".policy_burl_time").click(function(){		
	$(this).parents("dd").siblings().find(".policy_burl_time").attr("checked",false);
	})
	
//问卷调查是否禁用
function QSfalse(x,y,z){
	$('.' + x ).click( function(){
	$(this).parents( y ).siblings( z ).find(".QS_radio_inp").attr("checked",false).attr("disabled","disabled");
	})};
QSfalse('QS_radio_false','h2','dl');
function QSyc(x,y){
	$('.' + x ).click(function(){		  //点击去掉选中
	$('.' + y ).attr("checked",false);
	})};
QSyc('QS_sx_false','QS_radio_all');
QSyc('QS_radio_all','QS_sx_false');
$(".QS_radio_true").click(function(){
	$(this).parents("h2").siblings("dl").find(".QS_radio_inp").removeAttr("disabled");
	})

//平台table切换
    $(".notice_menu li").click(function(){
        $(this).addClass("notice_li_cur").siblings().removeClass("notice_li_cur");
        if($(this).hasClass("myoff")){
            $(".notice_arrow_gray").addClass("notice_arrow_up");
            $(".notice_li_sub").slideToggle();
            $(".notice_arrow_gray").toggleClass("notice_arrow_down");
        } else if( !($(this).hasClass("myoff")) ){
            $(".notice_li_sub").slideUp();
            $(".notice_arrow_gray").removeClass("notice_arrow_down notice_arrow_up");
            $(".notice_li_sub dd").removeClass("notice_li_sub_cur");
        }
    })
    $(".notice_li_sub dd").click(function(){
        $(this).addClass("notice_li_sub_cur").siblings().removeClass("notice_li_sub_cur");
    })
//筛选
$(".screen_type dl dt").click(function(){
	$(this).siblings().removeClass("curr")
	$(this).addClass("curr");
	});
$(".screen_type dl dd").click(function(){
	$(this).siblings(".screen_type dl dt").removeClass("curr")
	if($(this).parent().hasClass("screen_num")){
		$(this).siblings().removeClass("curr")
		}
	$(this).toggleClass("curr");
	if(!$(this).parent().children().hasClass("curr")){
		$(this).siblings(".screen_type dl dt").addClass("curr");
		};
	});
//底部内容根据浏览器去到底部
$(window).resize(function(){
  $(".foot_layout").css("position","static");
  var winH=$(window).height()-245,
	  midH=$("#mid_height").height();
	  if((midH<winH)&&midH){
		$(".foot_layout").css("position","fixed");
		}
   //注册弹窗
   var popH=$(".pop_up_w").height(),
	    popW=$(".pop_up_w").width(),
		windowH=$(window).height(),
		windowW=$(window).width();
	$(".pop_up_w").css({"top":(windowH-popH-60)/2+'px',"left":(windowW-popW)/2+'px'});
	$(".pop_up_clo,.a_btn").click(function(){
		   $("#reg_modal,#pop_modal,#reg_modal2,#pop_modal2").hide();
		})
    }).resize();
	
//排序
$(".sort_bar ul li").click(function(){
	if($(this).hasClass("curr")){
		$(this).children("i").toggleClass("dropUp");
		};
	$(this).addClass("curr");
	$(this).siblings("li").removeClass("curr");
	$(this).siblings("li").children("i").removeClass("dropUp");
	});
	
//平台公告切换
function tabShow(j,l,m,n){
	$('.'+j).click(function(){
		$('.'+l).show();
		$('.'+m).hide();
		$(".notice_con_tit").text(n);
		})
	}
tabShow('notice_li_gg','notice_list_gg','notice_list_zx','平台公告');
tabShow('notice_li_zx','notice_list_zx','notice_list_gg','平台资讯');

//注册时统一社会信用代码下拉选择
$("#cid_selcet").change(function(){
  var checkText=$("#cid_selcet").val(),
  check_no=$("#cid_selcet").find("option:selected").siblings().val();
  $("."+check_no).hide();
  $("."+checkText).show();
});

});
})(jQuery);

