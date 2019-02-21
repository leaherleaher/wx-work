		var form_data = {'url':window.location.href.split('#')[0]};
		$.ajax({url:"/share",type:"post",
			data:form_data,dataType:"json",success:function(result){
				timestamp = result.timestamp;
				nonceStr = result.nonceStr;
				signature = result.signature;
				wx.config({
					  debug: false,
				      appId:result.appid,
				      timestamp:result.timestamp,
				      nonceStr:result.nonceStr,
				      signature:result.signature,
				      jsApiList: [
				        'checkJsApi',
				        'onMenuShareTimeline',
				        'onMenuShareAppMessage',
				      ]
				  });
				  wx.ready(function(){
					  wx.onMenuShareTimeline({
						    title: '我是马飞，我最帅，戳我、戳我、戳我~~~', // 分享标题
						    desc: '朋友圈都被这个刷屏了，你也来凉一凉吧', // 分享描述
						    link: window.location.href, // 分享链接
						    imgUrl: '/img/b.jpg', // 分享图标
						    success: function () { 
						        // 用户确认分享后执行的回调函数
						        //alert("xxxxxx");
						    	var form_data = {"buttonName":'分享朋友圈',"source":window.location.href};
						    },
						    cancel: function () { 
						        // 用户取消分享后执行的回调函数
						        //alert("-------");
						    }
						});
					  //分享给朋友
					  wx.onMenuShareAppMessage({
						    title: '我是马飞，我最帅，戳我、戳我、戳我~~~', // 分享标题
						    desc: '朋友圈都被这个刷屏了，你也来凉一凉吧', // 分享描述
						    link: window.location.href, // 分享链接
						    imgUrl:'/img/b.jpg', // 分享图标
						    type: '', // 分享类型,music、video或link，不填默认为link
						    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
						    success: function () { 
						        // 用户确认分享后执行的回调函数
						    	var form_data = {"buttonName":'分享朋友',"source":window.location.href};
						    },
						    cancel: function () { 
						        // 用户取消分享后执行的回调函数
						    }
						});
				  });
			}
		});
		