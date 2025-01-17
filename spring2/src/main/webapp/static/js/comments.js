/**
 * 댓글 보기/감추기, 댓글 CRUD 요청/응답 처리.
 * post/details.jsp 파일에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    // btnToggleComment 요소를 찾음.
    const btnToggleComment = document.querySelector('button#btnToggleComment');
    
    // div.collapseComments 요소를 부트스트랩의 Collapse 객체로 생성.
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle : false});
    // 부트스트랩 컬립스 객체를 생성한다 접히고 펼치고 하는 역할
    
    // btnToggleComment 버턴에 클릭 이벤트 리스너를 설정.
    btnToggleComment.addEventListener('click', ()=>{
       bsCollapse.toggle(); 
       // 생성이 되있는 컬립스 객체에서 클릭했을때 이벤트
       // 접히고 펼치고 하는 역할
       
       if(btnToggleComment.innerHTML === '댓글 보기'){
        btnToggleComment.innerHTML ='댓글 감추기';
        
        // 댓글 목록 가져오기 요청을 보냄. 에이작스에서 댓글 목록을 가져오는 부분.
        getAllComments();
        
       } else {
        btnToggleComment.innerHTML = '댓글 보기'; // 감춰진 상태
       }
    });

    
    // button#btnRegisterComment 요소를 찾음.
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    
    // btnRegisterComment 버튼에 클릭 이벤트 리스너를 설정.
    btnRegisterComment.addEventListener('click', registerComment); 
    // 클릭했을때 함수의 이름. 클릭했을때 불러 올 함수(콜백)
    // 코드가 많이 엄청길 때 주로 사용된다.
    
    // 부트스트랩 모달 객체를 생성.
    const commentModal = new bootstrap.Modal('div#commentModal', { backdrop: true}); 
    // 백그라운드를 검게 만들어주는 기능
    
    // 모달의 [저장] 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnUpdateCmnt = document.querySelector('button#btnUpdateCmnt');
    btnUpdateCmnt.addEventListener('click', updateComment);

    /* ---------------------- (콜백) 함수 선언 ------------------------- */
    /* 콜백 함수 선언 */
    
    // btnRegisterComment 버튼의 클릭 이벤트 리스너(콜백). 댓글 등록.
    // 클릭됬을때 호출되는 함수(콜백)
    function registerComment(){
        // input#id 요소의 값을 읽음 -> 댓글을 등록할 포스트의 아이디
        // CommentCreateDto의 id와 같은 변수이름
        const postId = document.querySelector('input#id').value; 
        // input#username 요소의 값을 읽음 -> 댓글 작성자 아이디
        const username = document.querySelector('input#username').value;
        // textara#ctext 요소의 값을 읽음 -> 댓글 내용
        const ctext = document.querySelector('textarea#ctext').value;
        
        // 댓글 내용이 비어 있는 지를 체크.
        if(ctext === ''){
            alert('댓글 내용을 입력하세요.'); // 내용이 비어 있으면 안되니까 창을 띄우고
            return; // 더 이상 진행하면 안되기 때문에 리턴.
        }
        
        // Ajax 요청으로 보낼 데이터 객체
//        const data = { postId:postId, username:username, ctext:ctext };
        const data = {postId, username, ctext} // 자바스크립트 객체를 만듦.
        console.log(data);
                 // 필드이름 : 지역변수, 프로퍼티이름 : 값
                 // 자바스크립트는 중괄호는 객체, 대괄호는 배열
                 // 객체와지역변수 이름을 같이하면 간단하게 지역 변수이름 하나만 사용하면 됨.
                 // 지역변수의 값을 프로퍼티에 들어가게 됨.
                 // 객체를 만드는 방법.
                 
        // 서버로 POST 방식의 Ajax 요청을 보내고 응답을 처리. 라이브러리 사용. axios (JSP에 jsDelivr CDN 코드 넣기 )
        axios.post('../api/comment', data) // 첫번째는 요청을 보내는 주소(URI), 요청을 보내는 데이터, 패킷에 넣어야할 데이터를 두번째 아규먼트
                .then((response) => { // 이벤트리스너를 등록하는 코드 
                    //console.log(response);
                    if(response.data === 1){
                        alert('1개 댓글 등록 성공'); // 성공 확인 창
                        document.querySelector('textarea#ctext').value = ''; // 댓글 등록 후 텍스트에어리어 비우기
                        
                        // 댓글 목록을 다시 불러옴. 댓글 목록을 새로 만들어서 갱신
                        getAllComments(); 
                        
                    } // 프로퍼티를 찾는 방법은 로그를 출력하는 방법 밖에 없다. 프로퍼티를 쓰려면 . 
                    
                }) // 서버에서 HTTP 200 응답이 왔을 경우 실행할 콜백을 등록해주는 부분
                .catch((error) => { // 에러코드로 왔을 때 이벤트 리스너를 등록하는 코드
                    console.log(error);
                }); // 에러 응답이 왔을 경우(400, 500) 처리해야될 이벤트 콜백을 등록해주는 부분
                
                // http8080... / spring2/post/details? 
                // 현재위치의 폴더 = post ./ , spring2 = ../ spring2는 컨택스트 루트 . 값을 바꿀수 있기 때문에 상대경로로
                // 찾아가야지 안전하다. 자바 스크립트는 브라우저이기 때문에 브라우저는 지금 위치를 알고있고 ../ 두개로 
                
                // axios.post('../api/comment', data) 이 한줄로 요청 url을 서버로 보내는 것이고
                // 네트워크 페이로드에 소스보기는 클라이언트에서 서버로 보내는 실제 문자열. JSON 문자열
                // 요청을 보내고 나서 응답이 왔고 그 응답이 왔을때 리스펀스 로그를 출력을 해준것. 등록이 됬다는 것을 확인
    }
    
    // 포스트에 달려 있는 댓글 목록 가져오기
    function getAllComments(){
        // 댓글 목록을 요청하기 위한 포스트 아이디(글 번호)
        const postId = document.querySelector('input#id').value;
        
        // 댓글 목록을 요청하기 위한 REST API(요청 URI)
        const uri = `../api/comment/all/${postId}`; // 요청보낼 uri를 만든다. spring/api/comment/all/(id)
        
        
        // Ajax 요청을 보냄.
        axios
        .get(uri) // 모든 메서드는 요청을 보낸 uri
        .then((response) => { 
            console.log(response); // -> response.data 속성에 서버가 보낸 댓글 목록이 있음.
            // divComments 영역에 댓글 목록을 출력.
            makeCommentElements(response.data); //요청을 보내고    
        })
        .catch((error) => {
            console.log(error);
        });
    }
    // 댓글 목록을 그려주는 함수.
    // 댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달받아서 div에 출력할 html을 작성.
    function makeCommentElements(data){ //자바 스크립는 아규먼트는 타입을 쓰지 않는다
        // 댓글 목록을 출력할 div 영역
        const divComments = document.querySelector('div#divComments');
        
        // 댓글 객체 하나하나 만들어주는 html 작성!
        // div에 출력할 html 코드를 저장 문자열 변수
        let html = '<ul class="list-group list-group-flush">'; 
        for (const comment of data) { // 배열의 인덱스는 in, 원소는 of으로 꺼내기
            // timestamp를 날짜/시간 포맷 문자열로 변환
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
            
            
            html += `
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div>
                    <div class="text-secondary" style="font-size: 0.825rem;">
                        <span>${comment.username}</span>
                        <span>${modifiedTime}</span>
                    </div>
                    <div>
                        ${comment.ctext}
                    </div>
                </div>
            `;
            
            // 로그인 사용자와 댓글 작성자가 같은 경우에만 삭제/수정 버튼을 추가.
            if(signedInUser === comment.username) {
                html +=`
                <div>
                    <button class="btnDeleteComment btn btn-outline-danger btn-sm"
                        data-id="${comment.id}">삭제</button>
                    <button class="btnUpdateComment btn btn-outline-primary btn-sm"
                        data-id="${comment.id}">수정</button>
                </div>
                `;
            }
            html += '</li>';
        }
        html += '</ul>';
        
        // 작성된 html을 div에 삽입.
        divComments.innerHTML = html;
        
        // html 코드가 div에 삽입된 후에 (삭제/수정) 버튼들을 찾을 수 있음. 결론은 만들어 져야함.
        // -> 이벤트 리스너를 설정할 수 있음.
        
        //---------------------------- 댓글 마다 수정 삭제 이벤트 ----------------------------
        // 모든 댓글 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnDeletes = document.querySelectorAll('button.btnDeleteComment');
        for (const btn of btnDeletes){
            btn.addEventListener('click', deleteComment);
        }
        
        // 모든 댓글 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnModifies = document.querySelectorAll('button.btnUpdateComment');
        for (const btn of btnModifies){
            btn.addEventListener('click', showCommentModal);
        }
    }
        
    // 댓글 삭제 버튼의 클릭 이벤트 리스너(콜백)
    function deleteComment(event) {
        console.log(event.target);
        //-> 모든 이벤트 리스너(콜백)은 envet 객체를 아규먼트로 전달받음.
        // -> event 객체는 targaet 속성(이벤트가 발생한 HTML 요소)을 가지고 있음.
        
        // 댓글 삭제 여부를 확인
        const result = confirm('댓글을 정말 삭제할까요?');
        if (!result){ // 사용자가 [취소]를 클릭했을 때
            return; // 함수 종료
        }
        
        // HTML 요소의 속성(attribute)의 값을 찾음:
        const commentId = event.target.getAttribute('data-id');
        
        
        // Ajax 댓글 삭제 요청 REST API(요청 URI)
        const uri = `../api/comment/${commentId}`;
        
        // Ajax 요청을 보냄.
        axios
        .delete(uri) // 요청을 보낸다
        .then((response) => {
            //console.log(response);
            alert('댓글이 삭제됐습니다.')
            getAllComments(); // 댓글 목록 갱신 
        }) //응답이 오면 이런일을 할거다 - 콜백
        .catch((error) => {
            console.log(error);
        }); // 에러가 왔을때 할일 - 콜백
    }
    
    // 함수를 어디에서 실행될 것인지
    
    function showCommentModal(event){
        // 이벤트가 발생한 타겟(HTML 요소)에서 data-id 속성 값을 찾음.
        const commentId = event.target.getAttribute('data-id');
        //commentModal.show(); // bootstrap.Modal 객체의 show() 메서드 호출
        
        
        
            const uri = `../api/comment/${commentId}`;
            axios
            .get(uri)
            .then((response) => {
                console.log(response);
                
                // 모달의 input에 댓글 아이디를 value 속성으로 저장.
                document.querySelector('input#modalCommentId').value = response.data.id;
                // 모달의 textarea에 댓글 내용을 value 속성으로 저장.
                document.querySelector('textarea#modalCommentText').value = response.data.ctext;
                
                commentModal.show(); // bootstrap.Modal 객체의 show() 메서드 호출 - 모달 보여주기
            })
            .catch((error) => {
                console.log(error);
            });
            
        // -> 성공 콜백에서 모달(commentModal)의 input과 textarea를 채움.
        // -> 모달 보여주기
    }
    
    // 모달의 [저장] 버튼 클릭 리스너(콜백) 작성
    // -> 댓글 업데이트 Ajax 요청을 보내고, 성공/실패 콜백 작성. update
    function updateComment() {
        // 업데이트할 댓글 아이디(번호)
        const commentId = document.querySelector('input#modalCommentId').value;
        
        // 업데이트할 댓글 내용
        const ctext = document.querySelector('textarea#modalCommentText').value;
        if (ctext == '') {
            alert('댓글 내용을 입력하세요.');
            return;
        }
        
        // 댓글 업데이트 REST API(요청 URI)
        const uri = `../api/comment/${commentId}`;
        
        // Ajax 요청을 보냄.
        axios
        .put(uri,{ctext:ctext}) // 앞에있는 ctext 프로퍼티, 뒤쪽은 지역변수 ctext 간단히 {ctext} 로 사용가능.
        .then((response) => {
            console.log(response);
            commentModal.hide(); // 댓글 업데이트 모달을 닫음.
            getAllComments(); // 댓글 목록을 갱신.
        })
        .catch((error) => {});
    }
});