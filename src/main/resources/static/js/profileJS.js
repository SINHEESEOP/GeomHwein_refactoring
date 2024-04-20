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

function fetchUpdatedProfile() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/user/pfBring', // 요청을 보낼 URL
            type: 'POST', // HTTP 요청 방식
            contentType: 'application/json', // 서버로 전송할 데이터의 MIME 타입
            dataType: 'json', // 서버에서 반환하는 데이터의 타입
            headers: {
                'Authorization': 'Bearer your_access_token_here' // 필요한 경우 Bearer 토큰 사용
            },
            success: function (response) {
                console.log('성공:', response);
                $(".userNm").text(response.userNm);
                $(".userEmlAddr").text(response.userEmlAddr);

                if (response.userTelno == null || response.userTelno === "") {
                    $(".userTelno").text("데이터가 없습니다.");
                } else {
                    $(".userTelno").text(response.userTelno);
                }

                if (response.address == null || response.address === "") {
                    $(".address").text("데이터가 없습니다.");
                } else {
                    $(".address").text(response.address);
                }
                resolve(response);
            },
            error: function (xhr, status, error) {
                console.error('에러:', error);
                reject(error);  // 프로미스 거부
            }
        });
    })
}

$(document).ready(fetchUpdatedProfile() )


$(document).ready(function () {
    $('.pfSetBtn').on('click', function (e) {
        e.preventDefault(); // 폼의 기본 제출을 방지
        var formData = $('#userForm').serialize(); // 폼 데이터 직렬화

        $.ajax({
            type: 'POST', // HTTP 요청 방식
            url: '/user/pfUpdate', // 요청을 보낼 URL
            data: formData, // 전송할 데이터
            success: function (response) {
                fetchUpdatedProfile().then(function() {
                    // alert('프로필이 업데이트되었습니다!');
                }).catch(function(error) {
                    console.error('프로필 업데이트 중 오류 발생:', error);
                });
            },
            error: function (xhr, status, error) {
                alert('업데이트 실패: ' + error);
            }
        });
    });
});
