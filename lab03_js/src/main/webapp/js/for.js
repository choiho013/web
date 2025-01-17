/**
 * for.html 파일에 포함.
 */

// 아이디가 result인 div 요소를 찾음.
const result = document.querySelector('div#result');

// 구구단 2단을 result에 출력: result.innerHTML = 문자열;
let html = ''; // result 요소에 출력할 HTML 문자열을 저장하기 위한 변수.

for(let x = 1; x < 10; x++){
    html += `2 x ${x} = ${2 * x} <br />`;
    // `문자열 템플릿 ${변수 또는 계산식}` 문자열은 넣는 패킷 `` 으로 넣는다
    // 2015년 정도에 나온 문법으로 패킷을 `` 사용하고 그 안에 템플릿을 작성.
}
result.innerHTML = html;

result.innerHTML += '<hr />'

// 구구단 3단 ~ 9단까지를 result에 추가.
html = '';
for(let x = 3; x < 10; x++){
    for(let y = 1; y <10; y++){
        html += `${x} x ${y} = ${x * y} <br />`
    }
    html += '<hr />'
}
result.innerHTML += html;

// break를 사용해서 2단은 2x2까지, 3단은 3x3까지, ..., 9단은 9x9까지 result에 추가.
html = '';
for(let x = 2; x < 10; x++){
    for(let y = 1; y < 10; y++){
        html += `${x} x ${y} = ${x * y} <br />`
        if(x==y){
            break;
        }
    }
    html += '<hr />'
}
result.innerHTML += html;


