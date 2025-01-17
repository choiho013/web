/**
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
    // 결과(result)가 success인 경우 alert 메시지 출력
    if (result === "success") {
        alert(`로그인 성공! 환영합니다, ${signedInUser}님!\n현재 포인트: ${currentPoints}\n지급된 포인트: ${pointAdded}`);
    } else if (result === "failure") {
        alert("로그인 실패: 아이디와 비밀번호를 확인하세요.");
    }
    console.log(result, signedInUser, currentPoints, pointAdded);
});