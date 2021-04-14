
<%@ page contentType="text/html;charset=utf-8" 
         import="java.util.*, jung.uss.domain.Board"%>
<meta charset="utf-8">
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none }
</style>
<center>
	<h1>
		Board List with SpringMVC / 보드의 리스트닭 
	</h1>
	<a href="write.do">입력폼</a>
	<table border='1' cellpadding="7" cellspacing="2" width="50%">
	    <tr>
		    <th>번호</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>제목</th>
			<th>삭제</th>
		</tr>
<%
	List<Board> list = (List<Board>)request.getAttribute("list");
    if(list.size() != 0){
    	for(Board dto: list){
%>
         <tr>
		    <td align='center'><%= dto.getSeq()%></td>
			<td><%= dto.getWriter() %></td>
			<td><%= dto.getEmail() %></td>
			<td><%= dto.getSubject()%></td>
			<td><%= dto.getRdate()%></td>
			<td align='center'><a href='del.do?seq=<%= dto.getSeq()%>'>삭제</a></td>
		 </tr>  
<%
    	}
    }
%>
		
	</table>
</center>