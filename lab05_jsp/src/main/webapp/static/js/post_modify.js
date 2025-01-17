/**
 * 포스트 업데이트, 삭제 기능.
 */

document.addEventListener('DOMContentLoaded', () => {
    // form#modifyForm 요소를 찾음.
    const modifyForm = document.querySelector('form#modifyForm');// CSS 사용하는 selector 문법으로 아규먼트 

    // infput#id 요소(글 번호/아이디 입력 필드)를 찾음.
    const inputId = document.querySelector('input#id');

    // input#title 요소(글 제목 입력 필드)를 찾음.
    const inputTitle = document.querySelector('input#title');

    // textarea#content 요소(글 내용 입력 필드)를 찾음.
    const textareaContent = document.querySelector('textarea#content');

    // 삭제 버튼을 찾음
    const btnDelete = document.querySelector('button#btnDelete');

    // 업데이트 버튼을 찾음
    const btnUpdate = document.querySelector('button#btnUpdate');


    // 삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('정말 삭제할까요?');
        //console.log(`confirm result = ${result}`); //-> 확인=true, 취소=false
        if (result) { //사용자가 [확인]을 했을때
            // 새로운 요청 주소로 GET방식 요청을 보냄.
            location.href = `delete?id=${inputId.value}`;
            // http://localhos:8080/jsp2/post/modify?id=*. URL에서
            // http://localhos:8080/jsp2/post/delete?id=* 로 요청 주소를 변경.
        }
    });

    // 업데이트 버튼에 클릭 이벤트 리스너를 설정
    btnUpdate.addEventListener('click', (e) => {
        // 제목에 입력된 값, 내용에 입력된 값을 읽음.
        // 제목과 내용이 비어 있는 지를 체크 -> 비어 있으면 alert() 함수를 호출 & 함수 종료.
        const title = inputTitle.value;
        const content = textareaContent.value; // value 는 입력된 값을 읽는것.
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return;
        }
        // confirm() 함수를 호출해서 수정된 내용을 저장할 지 확인.
        const result = confirm('변경된 내용을 저장할까요?')
        // 사용자가 [확인]을 선택한 경우 양식 데이터(form date)를 제출(submit).
        if(result) {
            modifyForm.method = 'post'; // 요청 방식을 POST로 설정.
            modifyForm.action = 'update' // 요청 주소를 localhost:8080/jsp2/post/update 로 설정.
            modifyForm.submit(); // 양식 데이터(form data) 제출(서버로 요청을 보냄.)
        }        
    });
});