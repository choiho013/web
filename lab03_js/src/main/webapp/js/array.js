/**
 * array.html 파일에 포함.
 * 
 * JS 배열: 여러 개의 원소(아이템)들을 하나의 변수 이름으로 저장하기 위한 데이터 타입.
 * Java 배열: "한가지" 타입의 값 여러 개를 저장하기 위한 타입.
 *   - int[], double[], String[], Object[], ...
 * JS 배열은 다른 타입의 값들을 저장할 수 있음.
 * (예) const array = [1, 'abc', new Date()];
 */

// 아이디가 output인 div 요소를 찾음.
const output = document.querySelector('div#output');

// 배열 선언, 초기화
const numbers = [1, 2, -3, -4, 5, 0]; //int[] number = {1, 2, 3, 4}; <- 자바

// 배열의 아이템들을 output 영역에 출력:
let html = '';
for(let i = 0; i < numbers.length; i++){
    html += `${numbers[i]}, `;
}
output.innerHTML = html + '<br />';

// Java 향상된 for 문장: for (변수 선언 : 배열) { ... }

// JS for-of 문장: 배열의 아이템들을 반복(iteration).
html = '';
for(const value of numbers){
    html += `${value}, `;
}
output.innerHTML += html + '<br />';

// JS for-in 문장: 배열의 인덱스를 반복(iteration)
html ='';
for(const i in numbers){ // 배열의 인덱스를 뽑는것
    html += `${i} ${numbers[i]}, :`;
}
output.innerHTML += html + '<br />';

// 배열 numbers의 원소들의 합과 평균을 output 영역에 출력.
html = '';
let total =  0;
for(const value of numbers){
    total += value;
    html = `${total}`
}

const mean = total / numbers.length; // 나누기 연산자(/)는 소수점 이하 계산을 수행.

output.innerHTML += `합계 = ${total}, 평균 = ${mean} <br />`

const movies = ['1등', '소방관', '위키드'];
// 아이디가 list인 ul에 movies의 아이템들을 li로 추가.
const list = document.querySelector('ul#list');

html = ''
for(const item of movies){
    html += `<li>${item}</li>`;
}
list.innerHTML += html;

//-------------------------------------------------------------------------------
// destructuring assignment(구조분해 할당)
const array = [1, 2, 3];
const [x, y, z] = array;
// 첫번째 두번째 세번째 각각 x = 1, y= 2 , z=3 할당하는것
output.innerHTML += `x = ${x}, y= ${y}, z= ${z} <br />`;

// 배열의 아이템 개수보다 적은 desturcturing assignment
const [a, b] = array;
output.innerHTML += `a = ${a}, b= ${b} <br />`;

// 배열의 아이템 개수보다 많은 desturcturing assignment
const [c, d, e, f] = array;
output.innerHTML += `c = ${c}, d = ${d}, e = ${e}, f = ${f} <br />`;

// rest 연산자(...)를 사용한 desturcturing assignment
const[g, ...h] = array;
output.innerHTML += `g = ${g}, h = ${h} <br />`
// ->g = 1, h의 나머지 값들은 배열이 들어감. 출력은 2,3으로 나오지만 h = [2, 3]
