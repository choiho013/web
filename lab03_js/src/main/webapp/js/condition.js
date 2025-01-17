/**
 * condition.html 파일에 포함.
 * 
 * 자바는 if () 구문에서는 조건식만 사용이 가능함!
 * 자바 if (식)의 식을 평가했을 때 treu/false 여야만 함.
 * (예) if(x % 2 == 1) { ... } --> 문법에 맞는 문장.
 * (예) if(x % 2) { ... } --> 문법 오류.
 * 
 * 자바스크립트는 false로 취급하는 값들이 있음.
 * - 숫자 0
 * - 빈 문자열('')
 * - 빈 배열([])
 * - null
 * - undefinde(값이 할당되지 않은 변수). (예) let x;
 * - NaN(Not a Number)
 * (예) if (x % 2) { ... } --> 자바스크립트에서는 허용되는 문법. 결과가 0이면 false 1이면 true
 */

// JS 비교 연산자: ==, !=, ===, !==
// (1) ==, != : 자바스크립트가 변수 타입을 암묵적으로 변환해서 값만 비교.
// (2) ===, !== : 타입과 값을 함께 비교.
console.log(1 =='1'); //-> true
console.log(1 === '1'); //-> false

// 아이디가 numberInput인 HTML input 요소를 찾음.
const numberInput = document.querySelector('input#numberInput');

//const number = numberInput.value; // 지금 상태에서는 비어있는 문자열이 들어가서 함수를 클릭하면 비어 있는 문자열을 
// 출력하기 때문에 메서드 안에서 인풋에 있는 값을 읽어와야 오류도 안뜨고 값도 제대로 나옴
// 버튼이 클릭됬을 그 시점에서 입력된 값을 읽어야 함. 실행되는 시점이 중요

console.log(numberInput);

// 아이디가 result인 div 요소를 찾음.
const result = document.querySelector('div#result');
console.log(result);

function checkEven() {
    // parseInt(string): string을 정수로 변환해서 리턴하는 함수.
    // -> 빈 문자열인 경우 NaN을 리턴.
    const number = parseInt(numberInput.value); // input에 입력된 값.
    console.log('number =', number);
    
    // if(number % 2 == 1) {} 자바스크립트는 같은 의미
    if(isNaN(number)){ //NaN은 비교 연산자(==, !=)를 사용할 수 없음.
        result.innerHTML = '입력값이 정수가 아닙니다.';
    } else if(number % 2) { // number를 2로 나눈 나머지가 있으면 
        result.innerHTML = '홀수';
    } else{
        result.innerHTML = '짝수';
    }
}

function checkPositive() {
    // Number(string): string을 숫자 타입으로 변환해서 리턴하는 함수.
    // -> 빈 문자열인 경우 0을 리턴.
    // parseFloat(string): string을 float 타입(실수)으로 변환해서 리턴하는 함수.
    // -> 빈 문자열인 경우 NaN을 리턴.
    const number = parseFloat(numberInput.value);
    console.log('number =', number);
    
    if(isNaN(number)) {
        result.innerHTML = '입력값은 숫자가 아닙니다.';
    } else if (number > 0){
        result.innerHTML = '양수';
    } else if(number == 0) {
        result.innerHTML = '영';
    } else {
        result.innerHTML = '음수';
    }
}

function checkEven2() {
    const number = numberInput.value;
    result.innerHTML = (number % 2) ? 'odd number(홀수)' : 'even number(짝수)';
}

// 인풋안에 들어가있는 것은 무조건 문자열 숫자가 들어가도 문자열이 들어감



