<!DOCTYPE Instrument PUBLIC "-//NASA//Instrument Markup Language 0.2//EN" "http://pioneer.gsfc.nasa.gov/public/iml/iml.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="items" type="java.util.List" required="true"%>
<%@ attribute name="selected" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="onchange" type="java.lang.String" required="false"%>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="disabled" type="java.lang.String" required="false"%>

<select id = "${id}" name="${name}" onchange="${onchange}" ${disabled}>
	<c:forEach var="item" items="${items}">
		<c:choose>
			<c:when test="${selected eq item.name}">
				<option value="${item.id}" selected="selected"><c:out value ="${item.name}" escapeXml="true"/></option>
			</c:when>
			<c:otherwise>
				<option value="${item.id}"><c:out value ="${item.name}" escapeXml="true"/></option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>