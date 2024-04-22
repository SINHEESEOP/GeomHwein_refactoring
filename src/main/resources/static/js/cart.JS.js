$(document).ready(function () {
    // 초기 금액 계산
    calculateTotal();

    $('.course-checkbox').change(function () {
        calculateTotal();
    });

    function calculateTotal() {
        var total = 0;
        $('.course-checkbox:checked').each(function () {
            total += parseInt($(this).val(), 10); // 체크된 체크박스의 value를 더함
        });
        // 금액을 쉼표가 포함된 형식으로 변환
        $('#totalAmount').text(total.toLocaleString() + '원'); // 총 금액 업데이트
    }
});

$(document).ready(function () {
    $('.price-tag').each(function () {
        var priceText = $(this).text(); // 현재 태그의 텍스트를 가져옴
        priceText = priceText.replace('원', '').trim(); // '원' 문자 제거 및 공백 제거

        // 쉼표를 포함한 숫자 형식으로 변환
        var priceNumber = parseInt(priceText.replace(/,/g, ''), 10); // 쉼표 제거 후 정수로 변환
        var formattedPrice = priceNumber.toLocaleString(); // 쉼표 포함 형식으로 변환

        $(this).text(formattedPrice + '원'); // 변환된 텍스트를 다시 태그에 적용
    });
});


$(document).ready(function () {
    $('.delete-btn').click(function () {
        var itemId = $(this).data('id');  // 버튼에서 데이터 ID 추출
        $.ajax({
            type: 'POST',  // POST 메소드 사용 (DELETE 메소드로 변경 가능)
            url: '/user/cart/delete/' + itemId,  // 요청을 보낼 URL
            success: function (response) {
                alert('항목이 삭제되었습니다.');  // 성공 알림
                window.location.reload();  // 페이지 새로고침으로 리스트 업데이트
            },
            error: function (error) {
                alert('삭제에 실패했습니다.');  // 실패 알림
            }
        });
    });
});


$(document).ready(function () {
    // 모달 인스턴스를 생성
    var purchaseModal = new bootstrap.Modal($('#purchaseModal'));

    $('#purchase-btn').click(function () {
        var totalAmount = 0;

        // 체크된 항목의 가격을 합산
        $('.item-checkbox:checked').each(function () {
            totalAmount += parseFloat($(this).val());
        });

        // 총 금액을 모달과 카드에 표시
        $('#totalAmount').text(totalAmount.toLocaleString() + '원');
        $('#totalAmountM').val(totalAmount.toLocaleString() + '원');

        // 모달을 보여줌
        purchaseModal.show();
    });

    $('#confirm-purchase').click(function () {
        var paymentMethod = $('#paymentMethod').val();

        if (!paymentMethod) {
            alert('모든 필드를 채워주세요.');
            return;
        }

        // 여기에 결제 처리 로직을 추가할 수 있습니다.
        // 예: AJAX를 통해 서버에 결제 요청을 보낼 수 있습니다.
        console.log('결제 방식:', paymentMethod, '총 금액:', $('#totalAmount').text());


        // 성공적으로 처리됐다고 가정하고 모달 닫기
        alert('구매가 완료되었습니다.');
        purchaseModal.hide();
    });
});

// #totalAmountM 요소에서 텍스트를 가져옴
var totalAmountText = $("#totalAmount").text(total.toLocaleString());
console.log(totalAmountText)

// '원' 문자와 콤마를 제거
var cleanedText = totalAmountText.replace('원', '').replace(/,/g, '');

// 문자열을 정수로 변환
var totalAmount = parseInt(cleanedText, 10);
console.log("얼마죠?" + totalAmount)

const coupon = document.getElementById("coupon-box");
const button = document.getElementById("payment-button");
const amount = totalAmount;

// 구매자의 고유 아이디를 불러와서 customerKey로 설정하세요.
// 이메일・전화번호와 같이 유추가 가능한 값은 안전하지 않습니다.
const widgetClientKey = "test_ck_ZLKGPx4M3M14Q21XBp7q3BaWypv1";
const customerKey = "ZkvJaNgMXqknjvzJZgnBI";
const paymentWidget = PaymentWidget(widgetClientKey, customerKey); // 회원 결제
// const paymentWidget = PaymentWidget(widgetClientKey, PaymentWidget.ANONYMOUS) // 비회원 결제

const paymentMethodWidget = paymentWidget.renderPaymentMethods(
    "#payment-method",
    {value: amount},
    {variantKey: "DEFAULT"}
);

paymentWidget.renderAgreement(
    "#agreement",
    {variantKey: "AGREEMENT"}
);

coupon.addEventListener("change", function () {
    if (coupon.checked) {
        paymentMethodWidget.updateAmount(totalAmountMtotalAmountM - 5000);
    } else {
        paymentMethodWidget.updateAmount(amount);
    }
});

button.addEventListener("click", function () {
    // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
    // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
    paymentWidget.requestPayment({
        orderId: "1W_pCfO4rzG9szJEcThKe",
        orderName: "토스 티셔츠 외 2건",
        successUrl: window.location.origin + "/success",
        failUrl: window.location.origin + "/fail",
        customerEmail: "customer123@gmail.com",
        customerName: "김토스",
        customerMobilePhone: "01012341234",
    });
});


