// JavaScript Document
window.onload = function(){
	"use strict"; 
	$('.tables > .table > tbody > tr:nth-child(4n+1)').css('background','#f9f9f9');
	$('.glyphicon-menu-down').click(function(){
		$(this).parent().parent().toggleClass('bg-transparent').next().toggle();
		$(this).toggleClass('glyphicon-menu-up');
	});
};