<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="sidebar">
  <ul class="side-nav accordion_mnu collapsible">
    <li><a href="index.html"><span class="white-icons computer_imac"></span> Dashboard</a></li>
    <li><a href="#"><span class="white-icons list"></span> 服务管理</a>
      <ul class="acitem">
        <li><a href="tablegzgl.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>故障管理</a></li>
        <li><a href="extendable-forms.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>服务请求</a></li>
        <li><a href="form-stepy.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>问题管理</a></li>
        <li><a href="form-validation.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>变更管理</a></li>
		<li><a href="form-validation.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>发布管理</a></li>
      </ul>
    </li>
    <li><a href="#"><span class="white-icons cup"></span> 运维管理</a>
      <ul class="acitem">
        <li><a href="typography.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>排班管理</a></li>
        <li><a href="widgets.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>巡检管理</a></li>
        <li><a href="grid.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>作业管理</a></li>
        <li><a href="button-icons.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>事故管理</a></li>
        <li><a href="ui-elements.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>绩效管理</a></li>
      </ul>
    </li>
    <li><a href="#"><span class="white-icons shuffle"></span> CMDB</a>
      <ul class="acitem">
        <li><a href="table.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Data Table</a></li>
        <li><a href="chart.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Chart and Graph</a></li>
        <li><a href="file-explorer.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>File Manager</a></li>
        <li><a href="calendar.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Full Calendar</a></li>
        <li><a href="colorbox.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Colorbox</a></li>
      </ul>
    </li>
    <li><a href="#"><span class="white-icons documents"></span> 知识库</a>
      <ul class="acitem">
        <li><a href="inbox.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Message Box</a></li>
        <li><a href="content.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Content Post</a></li>
        <li><a href="login.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Login Page</a></li>
        <li><a href="login2.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Another Login Page</a></li>
        <li><a href="forgot-pass.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Forgot Password Page</a></li>
        <li><a href="error.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Error Page</a></li>
        <li><a href="another-error-page.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>Another Error Page</a></li>
      </ul>
    </li>
	<li><a href="#"><span class="white-icons documents"></span> 系统管理</a>
      <ul class="acitem">
        <li><a href="inbox.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>客户管理</a></li>
        <li><a href="content.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>合同管理</a></li>
        <li><a href="login.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>供应商管理</a></li>
        <li><a href="login2.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>合作伙伴管理</a></li>
        <li><a href="forgot-pass.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>组织机构</a></li>
        <li><a href="error.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>用户管理</a></li>
        <li><a href="another-error-page.html"><span class="sidenav-icon"><span class="sidenav-link-color"></span></span>授权管理</a></li>
      </ul>
    </li>
  </ul>
  <div id="side-accordion">
    <div class="accordion-group">
      <div class="accordion-header"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#side-accordion" href="#collapseOne"><i class="nav-icon month_calendar"></i> Today's event</a> </div>
      <div id="collapseOne" class="collapse in">
        <div class="accordion-content">
          <ul class="event-list">
            <li>
              <div class="evnt-date"> 31<span>July</span> </div>
              <div class="event-info"> <span><i class="icon-time"></i> 12:25 PM</span>
                <p> Anim pariatur cliche repreh enderit, enim eiusmod high life </p>
              </div>
            </li>
            <li>
              <div class="evnt-date"> 31<span>July</span> </div>
              <div class="event-info"> <span><i class="icon-time"></i> 2:35 PM</span>
                <p> Anim pariatur cliche repreh enderit. </p>
              </div>
            </li>
          </ul>
          <table class="cal">
            <caption>
            <span class="prev"><a href="index.html">&laquo;</a></span><span class="next"><a href="index.html">&raquo;</a></span> July 2012
            </caption>
            <thead>
              <tr>
                <th> Mon </th>
                <th> Tue </th>
                <th> Web </th>
                <th> Thu </th>
                <th> Fri </th>
                <th> Sat </th>
                <th> Sun </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="off"><a href="index.html">26</a></td>
                <td class="off"><a href="index.html">27</a></td>
                <td class="off"><a href="index.html">28</a></td>
                <td class="off"><a href="index.html">29</a></td>
                <td class="off"><a href="index.html">30</a></td>
                <td class="off"><a href="index.html">31</a></td>
                <td><a href="index.html">1</a></td>
              </tr>
              <tr>
                <td><a href="index.html">2</a></td>
                <td><a href="index.html">3</a></td>
                <td><a href="index.html">4</a></td>
                <td><a href="index.html">5</a></td>
                <td><a href="index.html">6</a></td>
                <td><a href="index.html">7</a></td>
                <td><a href="index.html">8</a></td>
              </tr>
              <tr>
                <td><a href="index.html">9</a></td>
                <td><a href="index.html">10</a></td>
                <td><a href="index.html">11</a></td>
                <td><a href="index.html">12</a></td>
                <td><a href="index.html">13</a></td>
                <td><a href="index.html">14</a></td>
                <td><a href="index.html">15</a></td>
              </tr>
              <tr>
                <td><a href="index.html">16</a></td>
                <td><a href="index.html">17</a></td>
                <td><a href="index.html">18</a></td>
                <td><a href="index.html">19</a></td>
                <td><a href="index.html">20</a></td>
                <td><a href="index.html">21</a></td>
                <td><a href="index.html">22</a></td>
              </tr>
              <tr>
                <td><a href="index.html">23</a></td>
                <td><a href="index.html">24</a></td>
                <td><a href="index.html">25</a></td>
                <td><a href="index.html">26</a></td>
                <td><a href="index.html">27</a></td>
                <td><a href="index.html">28</a></td>
                <td><a href="index.html">29</a></td>
              </tr>
              <tr>
                <td><a href="index.html">30</a></td>
                <td class="active"><a href="index.html">31</a></td>
                <td class="off"><a href="index.html">1</a></td>
                <td class="off"><a href="index.html">2</a></td>
                <td class="off"><a href="index.html">3</a></td>
                <td class="off"><a href="index.html">4</a></td>
                <td class="off"><a href="index.html">5</a></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="accordion-group">
      <div class="accordion-header"> <a class="accordion-toggle" data-toggle="collapse" data-parent="#side-accordion" href="#collapseTwo"><i class=" nav-icon graph"></i> Site Statistics</a> </div>
      <div id="collapseTwo" class="collapse">
        <div class="accordion-content">
          <div class="site-stat">
            <h5><i class="icon-signal"></i> Visit Rates</h5>
            <ul>
              <li>Avarage Traffic<span class="up">35K</span></li>
              <li>Visitors<span class="down">5%</span></li>
              <li>Conversation Rate<span class="up">10m</span></li>
            </ul>
            <h5><i class="icon-align-left"></i> Unique Visit</h5>
            <ul>
              <li>Visit Rate<span class="up">14K </span></li>
              <li>Bounce Rate<span class="up">10K </span></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="new-update">
    <h2><i class="icon-list-alt"></i> Recent Update</h2>
    <div class="side-news">
      <h5><a href="#">Released Ziown Admin</a></h5>
      <p> ZiOwn is a powerful and clean admin panel template for web entrepreneurs, app developers and site owners as this can be used for the admin part of any web application, web based software's or custom CMS admin panels. It is very easy to use and extremely easy to integrate ... </p>
    </div>
    <div class="side-news">
      <h5><a href="#">Released Bingo Admin</a></h5>
      <p> Bingo is very powerful high end admin/backend user interface template. You can use it ... </p>
    </div>
  </div>
</div>
