<%@ page contentType="text/html; charset=gb2312"%> 
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<%@ page import="util.*"%>
<%@include file="/bookshop/inc/head.inc"%>
<br>
<div align="center">�û���¼</div>
<br>
<form name="form1" method="post" action="<%=request.getContextPath ()%>/login">
	<table width="300" border="0" cellspacing="1" cellpadding="1"
		align="center">
		<br>
		<tr>
			<td align="right">
				�û�����
			</td>
			<td>
				<input type="text" name="username" size="15" maxlength="25" required placeholder="�û���">
			</td>
		</tr>
		<tr>
			<td align="right">
				��&nbsp;&nbsp;&nbsp;�룺
			</td>
			<td>
				<input type="password" name="passwd" size="15" maxlength="20" required placeholder="����" >
			</td>
		</tr>
		<tr> 
			<td colspan="2" align="center">
			   <br>
				<input type="submit" name="Submit" value="��¼">
				<a>&nbsp; &nbsp;</a>
				<input type="reset" name="Submit2" value="ȡ��">
				<br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<p>
					&nbsp;
				</p>
			</td>
		</tr>
		<tr><br>
			<td colspan="2" align=center>
				������Ǳ�վ��Ա�����ڴ�
				<a href="userreg.jsp">ע��</a>��<br><br>
			</td>
		</tr>
	</table>
</form>
<%@include file="/bookshop/inc/authenticate.inc"%>