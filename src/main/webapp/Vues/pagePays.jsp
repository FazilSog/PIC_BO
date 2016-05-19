<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pays</title>
</head>
<body>
<fieldset class="mwfieldset1">
<h2 align="center">Projet pic</h2>
</fieldset>
<fieldset class="mwfieldset1">
<a href="../PIC_BO/index.jsp" class="btn">Accueil</a>
<H3 align="center" style="margin-top:-20px;">Liste des pays</H3>
</fieldset>
<fieldset style="height:300px; overflow:auto;">
<table class="table table-hover table-bordered table-micro" border="2" height="200px">
<thead>
   <tr class="tr">
      <th>identifiant</th>
      <th>codeISO</th>
   </tr>
</thead>
<tbody>
<c:forEach items="${listCountry}" var="country">
<tr>
	<td>
	<c:out value="${country.idCountry}"></c:out>
	</td>
	<td>
	<c:out value="${country.codeIso}"></c:out>
	</td>
</tr>
</c:forEach>
 
	
</tbody>
</table>
</fieldset>
</body>
<style>
table{ background:#ffcccc;
}
.ss{
background:#red;
}
table {
  max-width: 100%;
  background-color: transparent;
  border-collapse: collapse;
  border-spacing: 0;
}
.table {
  width: 70%;
  margin-bottom: 20px;
}
.table-hover tbody tr:hover > td,
.table-hover tbody tr:hover > th {
  background-color: #f5f5f5;
}
.table-bordered {
  border: 1px solid #dddddd;
  border-collapse: separate;
  *border-collapse: collapse;
  border-left: 0;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
}
.table-micro th,
.table-micro td {
	padding: 0px 5px;
}
.btn {
  display: inline-block;
  *display: inline;
  /* IE7 inline-block hack */

  *zoom: 1;
  padding: 4px 12px;
  margin-bottom: 0;
  font-size: 12px;
  line-height: 20px;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  color: #333333;
  text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
  background-color: #f5f5f5;
  background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
  background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
  background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
  background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
  background-repeat: repeat-x;
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
  border-color: #e6e6e6 #e6e6e6 #bfbfbf;
  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
  *background-color: #e6e6e6;
  /* Darken IE7 buttons by default so they stand out more given they won't have borders */

  filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
  border: 1px solid #cccccc;
  *border: 0;
  border-bottom-color: #b3b3b3;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
  *margin-left: .3em;
  -webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.2), 0 1px 2px rgba(0,0,0,.05);
  -moz-box-shadow: inset 0 1px 0 rgba(255,255,255,.2), 0 1px 2px rgba(0,0,0,.05);
  box-shadow: inset 0 1px 0 rgba(255,255,255,.2), 0 1px 2px rgba(0,0,0,.05);
}
.tr{backgroud:  color: #333333;
}
.mwfieldset1 {
	border: 1px solid #91BACF;
	background-color: #ECEEF5;
	-moz-border-radius: 8px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	padding: 4px;
}

</style>
</html>