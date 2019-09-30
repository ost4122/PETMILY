<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
    <style>
        #btn{
            border-top-left-radius: 5px;
            border-bottom-left-radius: 5px;
            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;    
            margin-left:-3px;
            margin-right:-4px;
            border: 1px solid skyblue;
            background-color: rgba(0,0,0,0);
            color: skyblue;
            padding: 5px;
        }
        #btn:hover{
            color:white;
            background-color: skyblue;
        }
        .buy{
        	margin-left : 500px;
        }
    </style>
<body>
  <script>
    function buy(){
    	
    	if(document.querySelector('input[name="paymentOption"]:checked').value == "KakaoPay"){
    		code = 'imp81171428';
    	}else if(document.querySelector('input[name="paymentOption"]:checked').value == "신용/체크카드"){
    		code = 'imp49323111';
    	}
    	
    	var itemName = document.getElementById("item_name").value
    	var totalPrice = document.getElementById("totalPrice").value
    	
    		
        var IMP = window.IMP; // 생략가능
        IMP.init(code); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
        	pg : "Petmily", // 실제 계약 후에는 실제 상점아이디로 변경
        	merchant_uid : 'merchant_' + new Date().getTime(),
            amount: totalPrice,
            name : itemName,
            buyer_email : 'ost0110@naver.com',
            buyer_name : '오성태',
            buyer_tel : '01082983310',
            buyer_addr : '인천',
            buyer_postcode : '123-456',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + itemPrice;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
                //성공시 이동할 페이지
                Delivery.submit();
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                history.go(-1);
                alert(msg);
            }
        });
    }
    </script>


<%@include file="header-area.jsp" %>
<div class="buy">
<h1>주문/결제</h1><br>

<h3>구매자 정보</h3>
<table>
<tr><td><strong>이름</strong></td><td>${member.name}</td></tr>
<tr><td><strong>이메일</strong></td><td>${member.email}</td></tr>
<tr><td><strong>휴대폰 번호</strong></td><td>${member.phone}</td></tr>
</table>
<br>
<h3>배송지 정보</h3>
<form action="Item_Delivery" method="post" name="Delivery">

<table >
<tr><td><strong>이름</strong></td><td>${member.name}</td></tr>
<tr><td><strong>배송주소</strong></td><td>${member.address}</td></tr>
<tr><td><strong>연락처</strong></td><td>${member.phone}</td></tr>
<tr><td><strong>배송 요청사항</strong></td>
						<td><select name="deliveryRequest">
									<option value="문앞">문앞</option>				
									<option value="직접 받고 부재 시 문 앞">직접 받고 부재 시 문 앞</option>				
									<option value="경비실">경비실</option>				
													</select></td></tr>
</table>
<br>
<h3>결제 정보</h3>
<input type = "hidden" value="${totalPrice}" id="totalPrice"/>

<table >

<tr><td><strong>상품</strong></td><td><img src="${pageContext.request.contextPath}/resources/shopIMG/${item.item_Profile}" width="200px" height="100px"/></td></tr>
<tr><td><strong>가격</strong></td><td><input type="hidden" id="Itemprice" value=${item.item_price}>${item.item_price}원</td></tr>
<tr><td><strong>배송비</strong></td><td><input type="hidden" id="delivery" value=20000>20000원</td></tr>
<tr><td><strong>총결제금액</strong></td><td><strong style="color:blue;">${totalPrice}</strong>원</td></tr>

<tr><td><strong>결제방법</strong></td><td><input type="radio" id="paymentOption" name="paymentOption" value="신용/체크카드" required>신용/체크카드
									   <input type="radio" name="paymentOption" value="KakaoPay" required>카카오페이</td></tr>
</table>

<input type = "hidden" name = "id" value = "${member.id }"/>
<input type = "hidden" name = "name" value = "${member.name }"/>
<input type = "hidden" name = "email" value = "${member.email }"/>
<input type = "hidden" name = "address" value = "${member.address }"/>
<input type = "hidden" name = "phone" value = "${member.phone }"/>
<input type = "hidden" name = "item_name" value="${item.item_name}" id="item_name" />
<input type = "hidden" name = "item_number" value="${item.item_number}"  />
<input type = "hidden" name = "deliveryPrice" value=20000 />
<input type = "hidden" name = "item_price" value="${item.item_price }" />
<input type = "hidden" name = "item_profile" value="${item.item_Profile }" />


</form>

<button class="inputBtn"onclick="buy();">결제하기</button>
</div>

<%@include file="footer-area.jsp" %>
</body>
</html>