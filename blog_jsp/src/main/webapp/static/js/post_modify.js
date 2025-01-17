/**
 * 
 */

// modify.jsp의 이벤트 리스너
document.addEventListener('DOMContentLoaded', () => {

    // form 의 modifyForm 요소를 찾음
    const modifyForm = document.querySelector('form#modifyForm');
    
    // input 의 id 요소를 찾음
    const inputId = document.querySelector('input#id');
    
    // input 의 title 요소를 찾음
    const inputTitle = document.querySelector('input#title');
    
    // textarea 의 content 요소를 찾음
    const textareaContent = document.querySelector('textarea#content');
    
    // button 의 delete 요소를 찾음
    const btnDelete = document.querySelector('button#btnDelete');
    
    // button 의 update 요소를 찾음
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    btnDelete.addEventListener('click',(e)=>{
       const result = confirm('정말 삭제할까요?');
       if(result){
        location.href = `delete?id=${inputId.value}`; // 삭제할 아이디의 값을 서버로 제출
       } 
        
    });
    
    // 업데이트 버튼의 이벤트 리스너 설정
    btnUpdate.addEventListener('click',(e)=>{
       const title = inputTitle.value; // 제목의 입력된 값을 할당
       const content = textareaContent.value; // 내용의 입력된 값을 할당
       
       // 제목과 내용이 비어 있는 지 체크. -> 비어있으면 alert() 함수를 호출 및 종료
       if(title === '' || content === ''){
        alert('제목과 내용은 반드시 입력해야 합니다.');
        return;
        
       }
       
       // confirm() 함수를 호출해서 수정된 내용을 저장할 지 확인.
       const result = confirm('변경된 내용을 저장할까요?');
       
       // 사용자 [확인]을 선택한 경우 양식 데이터(form date)를 서버에 제출(submit).
       
       if(result) {
        modifyForm.method = 'post'; // 요청 방식을 post방식
        modifyForm.action = 'update'; // 요청 주소를 localhost:8080/blog/post/update로 설정.
        modifyForm.enctype = 'multipart/form-data'
        modifyForm.submit(); // 양식 데이터 제출
       } 
    });
});