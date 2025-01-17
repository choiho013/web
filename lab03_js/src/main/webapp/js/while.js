/**
 * while.html 파일에 포함.
 */

// 아이디가 list인 ul 요소를 찾음.
const list = document.querySelector('ul#list');
const tableBody = document.querySelector('tbody#tableBody');
// while 반복문을 사용해서 list에 li 요소 5개를 추가.

let html = ''; //list에 추가할 HTML 문자열을 저장하기 위해서.
let x = 1;
while(x < 6){
    html += `<li>Item ${x}</li>`; 
    x++;
}
list.innerHTML = html;


//while 반복문을 사용해서 tr 5개를 추가.
html = '';
x = 1;
while(x <= 5){
    html += `
    <tr>
        <td>${x}</td>
        <td>제목 ${x}</td>
    </tr>`;
    x++;
}
tableBody.innerHTML += html;
