<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h3>상품 상세</h3>
		<div>상품에 대한 상세정보를 확인하세요!</div>
		<br>

		<div class="row">
			<div class="col-md-6">
				<%-- <c:set var="imageFilename"
				value="/resources/images/${product.id}.jpg" /> --%>
				<img
					src="<c:url value="/resources/images/${product.imageFilename}" />"
					alt="image" style="width: 60%" />
			</div>
			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>제조사 : ${product.manufacturer}</p>
				<p>분류 : ${product.category}</p>
				<h5>${product.price}</h5>
			</div>
		</div>
	</div>
</div>