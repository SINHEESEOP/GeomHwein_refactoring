import './style.css';
import { loadPaymentWidget, ANONYMOUS } from "@tosspayments/payment-widget-sdk";

const main = async () => {
    const paymentWidget = await loadPaymentWidget(
        "test_ck_ZLKGPx4M3M14Q21XBp7q3BaWypv1",
        // 비회원 customerKey
        ANONYMOUS
    );

    /**
     * 결제창을 렌더링합니다.
     * @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods%EC%84%A0%ED%83%9D%EC%9E%90-%EA%B2%B0%EC%A0%9C-%EA%B8%88%EC%95%A1
     */
    paymentWidget.renderPaymentMethods('#payment-method', { value: 1000 }, { variantKey: 'DEFAULT' });

    /**
     * 약관을 렌더링합니다.
     * @docs https://docs.tosspayments.com/reference/widget-sdk#renderagreement%EC%84%A0%ED%83%9D%EC%9E%90-%EC%98%B5%EC%85%98
     */
    paymentWidget.renderAgreement('#agreement', { variantKey: 'DEFAULT' });

    const paymentRequestButton = document.getElementById('payment-request-button');

    paymentRequestButton.addEventListener('click', () => {
        try {
            /**
             * 결제 요청
             * @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment%EA%B2%B0%EC%A0%9C-%EC%A0%95%EB%B3%B4
             */
            paymentWidget.requestPayment({
                orderId: generateRandomString(),
                orderName: '토스 티셔츠 외 2건',
                successUrl: window.location.origin + '/sandbox/success',
                failUrl: window.location.origin + '/sandbox/fail',
            });
        } catch (err) {
            // TODO: 에러 처리
        }
    });
}

main();

const generateRandomString = () => window.btoa(Math.random()).slice(0, 20);