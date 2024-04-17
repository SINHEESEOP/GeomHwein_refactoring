// 프로필 설정, 계정 설정 토글탭 기능
$(".account_set").click(function () {
    $(".account_menu").css("display", "block")
    $(".profile_menu").css("display", "none")
});

$(".profile_set").click(function () {
    $(".account_menu").css("display", "none")
    $(".profile_menu").css("display", "block")
});

// let a = $(".edit_set_btn")[0];
// console.dir(a);

// 프로필 설정에서, 프로필 수정 가능한 토글 기능.
$(".edit_set_btn").click(function () {
    // console.log("123")
    $(".edit_profile1").toggleClass("noneStyle")
    $(".edit_profile2").toggleClass("noneStyle")

});


$(document).ready(function () {
    // 문서가 준비되면 실행
    var role = $("#serverMessage").text(); // HTML 요소에서 메시지 텍스트 추출
    console.log(role);
    if (role) {
        // 메시지가 있으면 경고창으로 보여줌
        // alert(role);
    }
});


$(document).ready(function () {
    $.ajax({
        url: '/user/pfBring', // 요청을 보낼 URL
        type: 'POST', // HTTP 요청 방식
        contentType: 'application/json', // 서버로 전송할 데이터의 MIME 타입
        dataType: 'json', // 서버에서 반환하는 데이터의 타입
        headers: {
            'Authorization': 'Bearer your_access_token_here' // Bearer 토큰을 사용한 인증 헤더 (필요한 경우)
        },
        success: function (response) {
            console.log('성공:', response);

            $(".userNm").text(response.userNm)
            $(".userEmlAddr").text(response.userEmlAddr)

            if (response.userTelno == null || response.userTelno == "") {
                $(".userTelno").text("데이터가 없습니다.")
            } else {
                $(".userTelno").text(response.userTelno)
            }

            if (response.address == null || response.address == "") {
                $(".address").text("데이터가 없습니다.")
            } else {
                $(".address").text(response.address)
            }

        },
        error: function (xhr, status, error) {
            console.error('에러:', error);
            // 에러 처리 로직
        }
    });
});

