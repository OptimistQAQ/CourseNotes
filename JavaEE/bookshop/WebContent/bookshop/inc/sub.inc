<%@ page contentType="text/html; charset=gb2312"%>
<tr>
	<td>
		Hello&nbsp;<%=(String)session.getAttribute("username")%> |
		<!--SubButton1.Link-->
		<a href="booklist.jsp"> <!--SubButton1--> ���߹��� </a>
		<!--SubButton.Back-->
		|
		<!--SubButton1.Content.End-->
		<!--SubButton2.Link-->
		<a href="shopcar.jsp"> <!--SubButton2--> �ҵĹ��ﳵ </a> |
		<!--SubButton2.Content.End-->
		<!--SubButton3.Link-->
		<a href="orderinfo.jsp"> <!--SubButton3--> ������Ϣ </a> |
		<!--SubButton3.Content.End-->
		<!--SubButton4.Link-->
		<a href="login.jsp"> <!--SubButton4--> �˳� </a> |
		&nbsp;&nbsp;
	</td>
</tr>
<tr>
	<td bgcolor="#000000">
		<img src="images/Spacer.gif" width="1" height="1">
	</td>
</tr>