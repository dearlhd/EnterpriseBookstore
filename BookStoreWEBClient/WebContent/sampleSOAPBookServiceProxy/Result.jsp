<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleSOAPBookServiceProxyid" scope="session" class="webService.soap.SOAPBookServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleSOAPBookServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleSOAPBookServiceProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleSOAPBookServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        webService.soap.SOAPBookService getSOAPBookService10mtemp = sampleSOAPBookServiceProxyid.getSOAPBookService();
if(getSOAPBookService10mtemp == null){
%>
<%=getSOAPBookService10mtemp %>
<%
}else{
        if(getSOAPBookService10mtemp!= null){
        String tempreturnp11 = getSOAPBookService10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String title_1id=  request.getParameter("title26");
            java.lang.String title_1idTemp = null;
        if(!title_1id.equals("")){
         title_1idTemp  = title_1id;
        }
        entityBean.Book getBook13mtemp = sampleSOAPBookServiceProxyid.getBook(title_1idTemp);
if(getBook13mtemp == null){
%>
<%=getBook13mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">author:</TD>
<TD>
<%
if(getBook13mtemp != null){
java.lang.String typeauthor16 = getBook13mtemp.getAuthor();
        String tempResultauthor16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeauthor16));
        %>
        <%= tempResultauthor16 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">price:</TD>
<TD>
<%
if(getBook13mtemp != null){
%>
<%=getBook13mtemp.getPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">count:</TD>
<TD>
<%
if(getBook13mtemp != null){
%>
<%=getBook13mtemp.getCount()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">bookId:</TD>
<TD>
<%
if(getBook13mtemp != null){
%>
<%=getBook13mtemp.getBookId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">title:</TD>
<TD>
<%
if(getBook13mtemp != null){
java.lang.String typetitle24 = getBook13mtemp.getTitle();
        String tempResulttitle24 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typetitle24));
        %>
        <%= tempResulttitle24 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>