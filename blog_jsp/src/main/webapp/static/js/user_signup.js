/**
 * 회원가입 삽입 기능.
 */

document.addEventListener("DOMContentLoaded", () => {
    const birthdayInput = document.querySelector("input#birthday");
    const birthdayMsg = document.querySelector("div#birthdayMsg");
    const phoneInput = document.querySelector("input#phone");
    const phoneMsg = document.querySelector("div#phoneMsg");

    // 회원가입 정보 요소를 찾음
    const userSignUpForm = document.querySelector('form#userSignUpForm');
    // 유저이름을 찾음
    const inputUsername = document.querySelector('input#username');
    // 비밀번호를 찾음
    const inputPassword = document.querySelector('input#password');
    // 이메일를 찾음
    const inputEmail = document.querySelector('input#email');
    // 이름을 찾음
    const inputName = document.querySelector('input#name');
    // 생일을 찾음
    const inputBirthday = document.querySelector('input#birthday');
    // 핸드폰번호를 찾음
    const inputPhone = document.querySelector('input#phone');
    // 작성완료 버튼을 찾음
    const btnInsert = document.querySelector('button#btnInsert');

    birthdayInput.addEventListener("input", (e) => {
        let value = e.target.value.replace(/\D/g, ""); // 숫자만 남김

        // YYYY.MM.DD 형식으로 변환
        if (value.length > 4) {
            value = value.slice(0, 4) + "." + value.slice(4);
        }
        if (value.length > 7) {
            value = value.slice(0, 7) + "." + value.slice(7);
        }
        e.target.value = value; // 값 업데이트
    });

    // 생년월일 포커스 해제 시 검증
    birthdayInput.addEventListener("blur", () => {
        const value = birthdayInput.value.trim();

        // 형식 검증: YYYY.MM.DD
        if (!/^\d{4}\.\d{2}\.\d{2}$/.test(value)) {
            birthdayMsg.style.display = "block";
            birthdayMsg.textContent = "생년월일: YYYY.MM.DD 형식으로 입력해주세요.";
            birthdayInput.classList.add("error");
            return;
        }

        const parts = value.split(".");
        const year = parseInt(parts[0], 10);
        const month = parseInt(parts[1], 10);
        const day = parseInt(parts[2], 10);

        // 날짜 범위 검증
        const isValidDate = (y, m, d) => {
            const date = new Date(y, m - 1, d);
            return (
                date.getFullYear() === y &&
                date.getMonth() === m - 1 &&
                date.getDate() === d
            );
        };

        // 연도, 월, 일 범위와 유효성 확인
        if (
            year >= 1900 &&
            year <= 2025 &&
            month >= 1 &&
            month <= 12 &&
            day >= 1 &&
            day <= 31 &&
            isValidDate(year, month, day)
        ) {
            birthdayMsg.style.display = "none"; // 에러 메시지 숨김
            birthdayInput.classList.remove("error"); // 빨간 테두리 제거
        } else {
            birthdayMsg.style.display = "block"; // 에러 메시지 표시
            birthdayMsg.textContent = "생년월일: 올바른 날짜를 입력해주세요.";
            birthdayInput.classList.add("error"); // 빨간 테두리 추가
        }
    });

    // 전화번호 입력 실시간 형식화
    phoneInput.addEventListener("input", (e) => {
        let value = e.target.value.replace(/\D/g, ""); // 숫자만 남김

        // 010-XXXX-XXXX 형식으로 변환
        if (value.length > 3) {
            value = value.slice(0, 3) + "-" + value.slice(3);
        }
        if (value.length > 8) {
            value = value.slice(0, 8) + "-" + value.slice(8, 12);
        }

        e.target.value = value; // 값 업데이트
    });

    // 전화번호 포커스 해제 시 검증
    phoneInput.addEventListener("blur", () => {
        const value = phoneInput.value.trim();

        if (/^010-\d{4}-\d{4}$/.test(value)) {
            phoneMsg.style.display = "none"; // 에러 메시지 숨김
            phoneInput.classList.remove("error"); // 빨간 테두리 제거
        } else {
            phoneMsg.style.display = "block"; // 에러 메시지 표시
            phoneMsg.textContent = "휴대전화번호: 010-XXXX-XXXX 형식으로 입력해주세요.";
            phoneInput.classList.add("error"); // 빨간 테두리 추가
        }
    });

    btnInsert.addEventListener('click', (e) => {
        e.preventDefault(); // 폼 새로고침 방지
        const username = inputUsername.value.trim();
        const password = inputPassword.value.trim();
        const email = inputEmail.value.trim();
        const name = inputName.value.trim();
        const birthday = inputBirthday.value.trim();
        const phone = inputPhone.value.trim();
        
        // 개별 필드를 모두 검사
        if (!username || !password || !email || !name || !birthday || !phone) {
            alert('모든 정보를 입력해주세요.');
            return;
        }


        // 생년월일 조건 확인 (YYYY.MM.DD 형식)
        if (!/^\d{4}\.\d{2}\.\d{2}$/.test(birthday)) {
            alert('생년월일은 YYYY.MM.DD 형식으로 입력해주세요 (예: 1999.01.01).');
            birthdayInput.focus();
            return;
        }

        const parts = birthday.split(".");
        const year = parseInt(parts[0], 10);
        const month = parseInt(parts[1], 10);
        const day = parseInt(parts[2], 10);

        // 날짜 범위 및 유효성 검증 함수
        const isValidDate = (y, m, d) => {
            const date = new Date(y, m - 1, d);
            return (
                date.getFullYear() === y &&
                date.getMonth() === m - 1 &&
                date.getDate() === d
            );
        };

        // 연도, 월, 일 범위 확인
        if (year < 1900 || year > 2025) {
            alert('생년월일 연도는 1900~2025 사이여야 합니다.');
            birthdayInput.focus();
            return;
        }

        if (month < 1 || month > 12) {
            alert('생년월일 월은 1~12 사이여야 합니다.');
            birthdayInput.focus();
            return;
        }

        if (day < 1 || day > 31 || !isValidDate(year, month, day)) {
            alert('생년월일이 올바르지 않습니다. 정확한 날짜를 입력해주세요.');
            birthdayInput.focus();
            return;
        }

        // 전화번호 조건 확인 (010-XXXX-XXXX 형식)
        if (!/^010-\d{4}-\d{4}$/.test(phone)) {
            alert('전화번호는 010-XXXX-XXXX 형식으로 입력해주세요.');
            phoneInput.focus();
            return;
        }

        const result = confirm('회원가입 하시겠습니까?');

        if (result) {
            userSignUpForm.method = 'post'; // 요청 방식을 post방식
            userSignUpForm.submit(); // 양식 데이터 제출
        }
    });
});