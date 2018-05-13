<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Product Inventory</h2>
		<p>제품 재고 현황입니다.</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-info">
					<th>photo Thumb</th>
					<th>상품명</th>
					<th>분류</th>
					<th>제조사</th>
					<th>가격</th>
					<th>재고</th>
					<th>설명</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<!-- 컨트롤러에 있는 키값과 일치해야함 products -->
					<tr>
						<td> <img src="<c:url value="/resources/images/${product.imageFilename}"/>"
								alt="image" style="width:100%"/></td>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.manufacturer}</td>
						<td>${product.price}</td>
						<td>${product.unitInStock}</td>
						<td>${product.description}</td>
						<td>
							<a href="<c:url value="/admin/productInventory/deleteProduct/${product.id}"/>"><i class="fa fa-times"></i></a>
							<a href="<c:url value="/admin/productInventory/updateProduct/${product.id}"/>"><i class="fa fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="<c:url value="/admin/productInventory/addProduct"/>" class="btn btn-primary">>Add Product </a>
	</div>
</div>
