var jdp = {
		getContextPath : function (){ 
		    var pathName = document.location.pathname; 
		    var index = pathName.substring(1).indexOf("/"); 
		    var result = pathName.substring(0,index+1); 
		    return result; 
		} ,
		openValidate: function(beforeOpen){
			if(beforeOpen){
				if(!$.isFunction(beforeOpen)){
					//fixme
					return false;
				}
				if(!beforeOpen()){
					return false;
				} 
			}
			return true;
		},
		select: function(control,url,callback,beforeOpen){

			control.click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='select-fancybox-iframe' name='select-fancybox-iframe' height='100%' width='100%' " +
									"src='"+url+"' />",
					fitToView	: false,
					width		: '30%',
					height		: '50%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["select-fancybox-iframe"].getValue)){
							var data = 
								window.frames["select-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
			
		},
		selectSingleUser: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					href		: jdp.getContextPath() + "/admin/party/org/org/single",
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSingleUser-fancybox-iframe' name='selectSingleUser-fancybox-iframe' height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/sec/user/user/single' />",
					fitToView	: false,
					width		: '30%',
					height		: '50%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSingleUser-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSingleUser-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
			
		},

		selectMultiUsers: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' frameborder='0'" +
									"id='selectMultiUsers-fancybox-iframe' name='selectMultiUsers-fancybox-iframe' height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/sec/user/user/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectMultiUsers-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiUsers-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
						
					}
				});
			});
		}	,
		
		selectSingleOrg: function(divClass,callback,beforeOpen){
			
			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					href		: jdp.getContextPath() + "/admin/party/org/org/single",
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSingleOrg-fancybox-iframe' name='selectSingleOrg-fancybox-iframe' height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/org/org/single' />",
					fitToView	: false,
					width		: '30%',
					height		: '50%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSingleOrg-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSingleOrg-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
			
		},

		selectMultiOrgs: function(divClass,callback,beforeOpen){
			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' frameborder='0'" +
									"id='selectMultiOrgs-fancybox-iframe' name='selectMultiOrgs-fancybox-iframe' height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/org/org/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectMultiOrgs-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiOrgs-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
						
					}
				});
			});
			
		}	,
		
		selectSinglePost: function(divClass,callback,beforeOpen){
			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSinglePost-fancybox-iframe' name='selectSinglePost-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/post/post/single' />",
					fitToView	: false,
					width		: '30%',
					height		: '50%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSinglePost-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSinglePost-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
						
					}
				});
			});
			
		},

		selectMultiPosts: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectMultiPosts-fancybox-iframe' height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/post/post/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						if($.isFunction(window.frames["selectMultiPosts-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiPosts-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
		},	
		selectSinglePostOfOrg: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSinglePostOfOrg-fancybox-iframe' name='selectSinglePostOfOrg-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/post/org/post/single' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSinglePostOfOrg-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSinglePostOfOrg-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
		},

		selectMultiPostsOfOrg: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectMultiPostsOfOrg-fancybox-iframe' name='selectMultiPostsOfOrg-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/post/org/post/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectMultiPostsOfOrg-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiPostsOfOrg-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
						
					}
				});
			});
		}	,
		
		selectSinglePerson: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSinglePerson-fancybox-iframe' name='selectSinglePerson-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/person/person/single' />",
					fitToView	: false,
					width		: '30%',
					height		: '50%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSinglePerson-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSinglePerson-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
						
					}
				});
			});
		},

		selectMultiPersons: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectMultiPersons-fancybox-iframe' name='selectMultiPersons-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/person/person/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						if($.isFunction(window.frames["selectMultiPersons-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiPersons-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
		},
		selectSingleDict: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSingleDict-fancybox-iframe' name='selectSingleDict-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/preference/def/single' />",
					fitToView	: false,
					width		: '40%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						if($.isFunction(window.frames["selectSingleDict-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSingleDict-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
		},	
		selectSinglePersonOfOrg: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectSinglePersonOfOrg-fancybox-iframe' name='selectSinglePersonOfOrg-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/person/org/person/single' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectSinglePersonOfOrg-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectSinglePersonOfOrg-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
						}
					}
				});
			});
		},

		selectMultiPersonsOfOrg: function(divClass,callback,beforeOpen){

			$("."+divClass).click(function(){
				if(!jdp.openValidate(beforeOpen)){
					return;
				}
				$.fancybox.open({
					type		: "html",
					content 	: "<iframe scrolling='auto' class='fancybox-iframe' " +
									"id='selectMultiPersonsOfOrg-fancybox-iframe' name='selectMultiPersonsOfOrg-fancybox-iframe' " +
									"height='100%' width='100%' " +
									"src='"+jdp.getContextPath()+"/admin/party/person/org/person/multi' />",
					fitToView	: false,
					width		: '80%',
					height		: '80%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'none',
					closeEffect	: 'none',
					beforeClose  : function(){
						//firefox中使用iframe的name获得iframe，而ie、chrome根据id获得iframe
						if($.isFunction(window.frames["selectMultiPersonsOfOrg-fancybox-iframe"].getValue)){
							var data = 
								window.frames["selectMultiPersonsOfOrg-fancybox-iframe"].getValue();
							if(data){
								if($.isFunction(callback)){
									callback(data);
								}
							}
							
						}
						
					}
				});
			});
		}
};

