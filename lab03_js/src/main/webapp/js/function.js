/**
 * function.html 파일에 포함.
 * 
 * JS 함수 선언 방법:
 * function 함수이름([파라미터 선언, ...]){
 *      실행 코드;
 *      [return [값];]
 * }
 * 
 * 
 * 함수 이름 앞에 리턴 타입을 선언하지 않음.
 * 파라미터를 선언할 때는 const, let, var 키워드를 사용하지 않음! 
 * 
 * JS 함수는 객체(object)!!
 * 1. 프로퍼티(property)를 가질 수 있음. (예) arguments
 * 2. 변수에 할당할 수 있음.
 * 3. 함수를 호출할 때 아규먼트로 함수 (객체)를 전달할 수 있음.
 * 4. 함수는 함수 (객체)를 리턴할 수 있음.
 * 5. 함수 내부에서 다른 함수를 선언(정의)할 수 있음.
 * 
 * 자바스크립트는 함수와 프로퍼티를 가질수있다.
 * 자바에서는 메서드 객체가 아닌데 자바스크립트의 함수는 변수에 할당 할 수 있다.
 * 변수의 타입이 동적으로 바뀌고. 함수도 실행할때마다 정수, 문자열을 리턴. 자바스크립트는 리턴타입을 선언ㅎ할 수 없음.
 * 그래서 문제가 생길 수 있어서 타입스크립트라고 따로 만든 프로그램이있다.
 */

// 함수 선언
function add(x,y) {
    console.log(`add 함수 파라미터: x = ${x}, y = ${y}`);
    return x + y;
}

let result = add(1,2); // 함수 호출.
console.log(`result = ${result}`);

// JS 함수는 파라미터 타입을 검사하지 않음.
result = add('hello', '안녕하세요');
console.log(`result = ${result}`);

// JS 함수는 파라미터 개수를 검사하지 않음.
result = add(1,2,3); // 선언된 파라미터 개수보다 더 많은 아규먼트를 전달한 경우.
console.log(`result = ${result}`);

result = add(1); // 선언된 파라미터 개수보다 적은 개수의 아규먼트를 전달한 경우.
console.log(`result = ${result}`);
// x = 1, y = undefind, x + y = 1 + underfined = NaN(Not a Number)

result = add(1,'hello');
console.log(`result = ${result}`);

// 모든 JS 함수는 arguments 속성(property)를 가지고 있음.
// arguments: 함수를 호출하는 곳에서 전달하는 모든 아규먼트들을 저장하고 있는 배열. 
function testArgs() {
    console.log(arguments); // 모든 함수가 가지고 있는 속성.
    for(const arg of arguments){
        console.log(arg);
    }
}

testArgs(); //-> arguments: 아이템 개수가 0인 배열
testArgs('Hello'); //-> arguments: 아이템이 1개인 배열
testArgs(1,'안녕'); //-> arguments: 아이템이 2개를 각즌 배열

// 숫자 2개를 전달받아서 뺄셈 결과를 리턴하는 함수.
function subtract(x,y){
    console.log(`subtract 함수 파라미터: x = ${x}, y = ${y}`);
    return x - y;    
}
result = subtract(2,3);
console.log(`result = ${result}`);

// default parameter: 기본값이 설정된 파라미터.
function multiply(x,y=1){
    console.log(`multiply 함수 파라미터: x = ${x}, y = ${y}`);
    return x * y;
}

result = multiply(2,3); //-> 두번째 아규먼트는 파라미터 y에 저장됨.
console.log(`result = ${result}`);

result = multiply(2); //-> 두번째 아규먼트가 없는 경우에는 파라미터 y는 기본값이 사용됨.
console.log(`result = ${result}`);

// 함수 파라미터 destructuring assignment(구조분해 할당)
function divide(x, y, ...rest) { //rest 연산자
    console.log(`divide 함수 파라미터: x = ${x}, y = ${y}, rest = ${rest}`);
    return x / y;
}
result = divide(1, 2, 3, 4, 5);
console.log(`result = ${result}`);

// 함수를 변수에 할당. 
//const plus = add(); //-> 함수 add()의 리턴 값을 변수 plus에 할당.
const plus = add; //-> 함수 add를 변수 plus에 할당.
console.log(plus); //-> plus: 함수(function)
console.log(plus(12, 34)); //-> plus() 함수의 리턴 값을 로그 출력.

// 이름이 없는 함수(anonymous function):
const minus = function (x, y) { //변수 이름이름이 없고 minus에 할당
    return x - y;
} // 이렇게 되면 minus가 이름이 됨
console.log(minus); // -> minus: 함수
console.log(minus(1,2)); //-> 함수 minus() 호출하고 그 리턴 값을 출력.

// 화살표 함수(arrow function): 익명 함수를 간단히 표현하는 문법. 자바의 람다 표현식과 비슷.
// (파라미터 선언, ...) => { 코드; }
// (파라미터 선언, ...) => 리턴값
const multiplication = (x, y) => x * y;
console.log(multiplication);
console.log(multiplication(2,3));

// 함수 (객체)를 아규먼트로 전달받는 함수:
function calculate(x, y, op) { //세번째 아규먼트는 함수여야 함
    return op(x, y); // 함수 op() 함수의 리턴 값을 리턴.
}

console.log(calculate(1, 2, divide)); // 함수의 객체를 op 에주는거 divide()와 아주 다름
console.log(calculate(1, 2, function (x, y) { return (x + y) * 2;}));
console.log(calculate(1, 2, (x,y) => (x - y) * 2));
//-> 이벤트 리스너(핸들러)를 설정할 때 많이 사용되는 JS 코드 패턴.
// 콜백(call: (나중에 호출하기 위해서) 아규먼트로 전달되는 함수 객체.

// 지역 함수(locat function), 내부 함수: 함수 안에서 선언하는 함수.
function increase(n){
    // 내부 함수 선언
    function addN(x){
        // 내부(지역) 함수는 외부 함수에서 선언된 파라미터, 지역변수들을 사용할 수 있음.
        return x + n;
    }
    
    // 함수 (객체) 리턴.
    return addN; // addN 함수 (객체)를 리턴.
    //return addN(1); //-> 함수 addN()의 리턴 값을 리턴.
}
//addN(10); //-> 지역 함수 이름은 외부에서 호출할 수 없음.

const increaseTwo = increase(2); // increaseTwo는 함수가 들어가는것. n = 2 가 들어간 addN의 리턴값
console.log(increaseTwo); 
console.log(increaseTwo(10)) // addN 의 x = 10 이 들어가고 x + n 을하면 12

const increaseTen = increase(10); //-> (x) => x + 10
console.log(increaseTen(100));

console.log(increase(1)(100));
// increase: 함수 이름(객체)
// increase(1): 함수 increase()를 호출한 리턴 값. 함수 객체.
// increase(1)(100) 함수 increse(1)가 리턴한 함수를 호출.

