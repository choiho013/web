/**
 * username 중복체크, email 중복체크
 * user/signup.jsp 파일에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    // username 중복 체크 결과를 저장할 변수.
    // true이면 회원가입이 가능한 username. false이면 [작성완료] 버튼은 비활성화.
    // 값이 ture일때 버튼을 활성화 또는 비활성화.
    // false 는 항상 비활성화.
    let isUsernameChecked = false;
    
    // password를 입력했는 지 여부를 저장할 변수. false이면 [작성완료] 버튼은 비활성화.
    let isPasswordChecked = false;
    
    // email 중복 체크 결과를 저장할 변수.
    // true이면 회원가입이 가능한 email. false이면 [작성완료] 버튼은 비활성화.
    let isEmailChecked = false;
    
    
    // 유저이름
    const inputUsername = document.querySelector('input#username');
    // 유저이름 검사
    const checkUsernameResult = document.querySelector('div#checkUsernameResult');
    // 비밀번호
    const inputPassword = document.querySelector('input#password');
    // 비밀번호 검사
    const checkPasswordResult = document.querySelector('div#checkPasswordResult');
    // 이메일
    const inputEmail = document.querySelector('input#email');
    // 이메일 검사
    const checkEmailResult = document.querySelector('div#checkEmailResult');
    // 버튼 
    const btnSignUp = document.querySelector('button#btnSignUp');
    
    // inputUsername 요소에 'change' 이벤트 리스너를 설정
    inputUsername.addEventListener('change', checkUsername);
    
    // inputPassword 요소에 'change' 이벤트 리스너를 설정.
    inputPassword.addEventListener('change', checkPassword);
    
    // inputEmail 요소에 'change' 이벤트 리스너를 설정.
    inputEmail.addEventListener('change', checkEmail);
    
    /* ----------------------- 함수 선언 ----------------------- */
    function changeButtonState() {
        if(isUsernameChecked && isPasswordChecked && isEmailChecked){ // 하나라도 만족 못할 경우 버튼을 활성화 시킬 수 없음. 
            // 버튼 활성화 - class 속성들 중에서 'disabled'를 제거.
            btnSignUp.classList.remove('disabled'); // 모든 html에는 classList 속성이 있음
            // 클래스값에는 한개의 값이 아닌 여러개의 값을 설정을 할 수 있으며, 모든 엘리먼트에는 classList라는 속성이 있음.
        } else {
            // 버튼 비활성화 - class 속성에 'disabled'를 추가.
//            btnSignUp.classList.add('disabled');
            
            // 삭제는 remove, 추가는 add
        }
            
    }
    
    function checkUsername() {
        const username = inputUsername.value;
        if(username === ''){
            checkUsernameResult.innerHTML = '사용자 아이디는 필수 입력 항목입니다.';
            
            // JSP에서 usrname 중복체크 부분의 클래스 속성을 추가하고 삭제.
            checkUsernameResult.classList.add('text-danger'); 
            checkUsernameResult.classList.remove('text-success'); // 없는 속성값을 지운다고해도 에러를 리턴하지 않음. 상관없다
            isUsernameChecked = false; //사용할 수 없는 경우 
            changeButtonState() // isUsernameChecked 값에 따라 버튼 여부를 확인
            return;
        } 
        
        // 아이디 중복 체크 REST API(요청 URI) 요청주소도 대소문자를 구분하기 때문에 controller의 요청 주소와 일치해야함.
        const uri = `./checkusername?username=${username}`;
        
        // Ajox 요청 보내기
        axios
        .get(uri)
        .then(handleCheckUsernameResp)
        .catch((error) => console.log(error));
//        alert('change');
    }
    
    
    function handleCheckUsernameResp({data}) { //아규먼트가 response
        
//        const {data} = response; //-> 구조분해 할당(destructuring assignment)
        // 배열과 오브젝트에서 사용할 수 있으며, 배열에서 사용하는 경우에는 
//        const arr = [1,2,3,4,5];
        //const x = arr[0];
        //const y = arr[1];
//        const [x,y] = arr; 
//        const [x,y, ...others] = arr; // ...others 는 나머지 연산자
//        console.log(x,y, others);
//        
//        const obj = {name:'홍길동', age:16 , phone: '1234-5678', email:'hgd'};
//        const {name, email, ...rest} = obj; // rest 는 프로퍼티 2개를 갖는 객체가 됨
//        console(name, email);
//        console(rest);
 
//        const obj = {name: '오쌤', age: 16};
////        const name = obj.name;
////        const age = obj.age;
//        const {name, age} = obj; // 구조분해 할당 지역변수 프로퍼티 이름과 같이 적어주면됨.
        console.log(data);

        if(data ==='Y'){
            checkUsernameResult.innerHTML = '멋진 아이디입니다.'
            checkUsernameResult.classList.add('text-success');
            checkUsernameResult.classList.remove('text-danger');
            isUsernameChecked = true; // 사용 가능한 아이디는 버튼을 활성화
        } else {
            checkUsernameResult.innerHTML = '사용할 수 없는 아이디입니다.'
            checkUsernameResult.classList.add('text-danger');
            checkUsernameResult.classList.remove('text-success');
            isUsernameChecked = false; // 사용 불가능한 아이디는 버튼을 비활성화
        }
        changeButtonState(); // 위의 상태에 따라 버튼의 상태를 바꿔야함.
        
    }
        // 정규표현식. 알아보기 
    
    function checkPassword(){
        if(inputPassword.value === ''){
            checkPasswordResult.innerHTML = '비밀번호는 필수입력 항목입니다.'
            checkPasswordResult.classList.add('text-danger');
            checkPasswordResult.classList.remove('text-success');
            isPasswordChecked = false;
        } else {
            checkPasswordResult.innerHTML = '사용할 수 있는 비밀번호입니다.'
            checkPasswordResult.classList.add('text-success');
            checkPasswordResult.classList.remove('text-danger');
            isPasswordChecked = true;
        }
        changeButtonState();
    }    
    
    function checkEmail(){
        //inputEmail에 입력된 값이 있는 지를 체크
        
        if(inputEmail.value === ''){
            checkEmailResult.innerHTML = '이메일은 필수입력 항목입니다.'
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            isEmailChecked = false;
            changeButtonState();
            return;
        } 
        
        //이메일 중복 체크 REST API(요청 URI)
        // 문자열 @는 그냥 넣면 안되고 encodeURIComponet 인코딩해서 넣어야함.
        const uri = `./checkemail?email=${encodeURIComponent(inputEmail.value)}`;
        // 또는 ./checkemail?email= + encodeURIComponent(inputEmail.value);
        
        // Ajax 요청을 보냄.
        axios
        .get(uri)
        .then(handleCheckEmailResp)
        .catch((error) => console.log(error));
    }
    
    
    function handleCheckEmailResp({data}){
        console.log(data);
        if(data ==='Y'){ // 회원 가입 가능한 이메일
            checkEmailResult.innerHTML = '사용가능한 이메일입니다.'
            checkEmailResult.classList.add('text-success');
            checkEmailResult.classList.remove('text-danger');
            isEmailChecked = true; // 사용 가능한 이메일는 버튼을 활성화
        } else { // 중복된 이메일
            checkEmailResult.innerHTML = '사용할 수 없는 이메일입니다.'
            checkEmailResult.classList.add('text-danger');
            checkEmailResult.classList.remove('text-success');
            isEmailChecked = false; // 사용 불가능한 이메일는 버튼을 비활성화
        }
        changeButtonState(); // 위의 상태에 따라 버튼의 상태를 바꿔야함.
    }
        
});