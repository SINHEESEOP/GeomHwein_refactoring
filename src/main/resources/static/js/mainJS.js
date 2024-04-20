$(document).ready(function () {
    // 문서가 준비되면 실행
    var role2 = $("#serverMessage2").text(); // HTML 요소에서 메시지 텍스트 추출
    if (role2) {
        // 메시지가 있으면 경고창으로 보여줌
        // alert(role2);
    }
});

// 메인 컨텐츠
$(document).ready(function () {

    var pageNum = 1;
    var amount = 4;

    function loadData(pageNum) {
        $.ajax({
            url: '/mainContent',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                pageNum: pageNum,
                amount: amount
            }),
            success: function (response) {
                console.log(response);

                var userNm = response.userNm;
                var educationGroups = response.educationGroups;
                console.log(userNm)
                console.log(educationGroups)

                var tbody = $('.tbody1');
                $('.data-template').siblings().remove();  // 기존 데이터 행 삭제, 템플릿은 유지

                if (userNm != null) {
                    $('.interests').text(userNm + " 님께 추천하는 강의");
                    $('.loginMypage').text(userNm + " 님의 페이지")
                }

                educationGroups.forEach(function (item) {
                    var tr = $('.data-template').clone().removeClass('data-template').show();
                    tr.find('.course-name').text(item.contsNm);
                    tr.find('.recommended-age').text("권장연령 : " + item.recAge + " 세");
                    tr.find('.instructor-name').text(item.userAuthVO.userNm);
                    tr.find('.instructor-rating').text(item.userAuthVO.userDetailsVO.userRating || '데이터 없음');
                    tr.find('.price').text(item.price || '데이터 없음');
                    tr.find('.rating').text(item.rating || '5.0 (5점 만점)');
                    tbody.append(tr);
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('There has been a problem with your fetch operation:', errorThrown);
            }
        });
    }

    loadData(pageNum);

    // '다음' 버튼 이벤트 핸들러
    $('#next-button').click(function () {
        if (pageNum >= 3) return
        pageNum++; // 페이지 번호 증가
        loadData(pageNum); // 데이터 로드
    });

    // '이전' 버튼 이벤트 핸들러
    $('#prev-button').click(function () {
        if (pageNum > 1) { // 첫 페이지가 아닐 때만 작동
            pageNum--; // 페이지 번호 감소
            loadData(pageNum); // 데이터 로드
        }
    });

});

// 메인 컨텐츠2
$(document).ready(function () {

    var pageNum = 1;
    var amount = 5;

    function loadData(pageNum) {
        $.ajax({
            url: '/mainContent2',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                pageNum: pageNum,
                amount: amount
            }),
            success: function (response) {
                console.log(response);

                var educationGroups = response.educationGroups;
                console.log(educationGroups)

                var tbody2 = $('.tbody2');
                $('.data-template2').siblings().remove();  // 기존 데이터 행 삭제, 템플릿은 유지

                educationGroups.forEach(function (item) {
                    var tr = $('.data-template2').clone().removeClass('data-template2').show();
                    tr.find('.course-name2').text(item.contsNm);
                    tr.find('.recommended-age2').text("권장연령 : " + item.recAge + " 세");
                    tr.find('.instructor-name2').text(item.userAuthVO.userNm);
                    tr.find('.instructor-rating2').text(item.userAuthVO.userDetailsVO.userRating || '데이터 없음');
                    tr.find('.price2').text(item.price || '데이터 없음');
                    tr.find('.rating2').text(item.rating || '5.0 (5점 만점)');
                    tbody2.append(tr);
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('There has been a problem with your fetch operation:', errorThrown);
            }
        });
    }

    loadData(pageNum);

    // '다음' 버튼 이벤트 핸들러
    $('#next-button2').click(function () {
        if (pageNum >= 3) return
        pageNum++; // 페이지 번호 증가
        loadData(pageNum); // 데이터 로드
    });

    // '이전' 버튼 이벤트 핸들러
    $('#prev-button2').click(function () {
        if (pageNum > 1) { // 첫 페이지가 아닐 때만 작동
            pageNum--; // 페이지 번호 감소
            loadData(pageNum); // 데이터 로드
        }
    });

});
