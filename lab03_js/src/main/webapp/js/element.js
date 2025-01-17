/**
 *    241211 - 배열의 메서드, 오브젝트 만들기, 오브젝트의 구조분해할당
 * element.html 파일에 포함.
 * 
 * HTML 요소를 찾는 메서드:
 * - document.getElementById (아이디로 찾기) 아이디는 한개를 
 * - document.getElementsByClassName (클래스들로 찾기) 클래스는 여러개를 찾기 때문에 Elements
 * - document.getElementsByTagName (태그들로 찾기) 태그들도 그렇고 배열과 유사하게 생긴 객체를 리턴.
 * - document.querySelector 아이디로 찾거나 클래스, 태그로 찾는데 중요한 것은 셀렉터를 찾을 수 있는 첫번째 한개만 리턴.
 * - document.querySelectorAll 같은 요소들을 전부 찾겠다라는 의미. 
 */

// button#btn1 요소를 찾음.
const btn1 = document.querySelector('button#btn1');

// btn1 요소에 클릭 이벤트 리스너를 설정.
btn1.addEventListener('click', function () {
    // document.getElementById 메서드를 사용해서 아이디가 div1인 요소를 찾음.
    const div1 = document.getElementById('div1'); // 아이디 앞에 #을 사용하지 않음.
    console.log(div1);
    
    // div1 요소의 바탕색을 변경.
    div1.style.backgroundColor = 'lime';
});

// button#btn2 요소를 찾음.
const btn2 = document.querySelector('button#btn2'); // document.getElementById('btn2');

// btn2 요소에 클릭 이벤트 리스너를 설정.
btn2.addEventListener('click', function () {
    // 클래스 이름이 c1인 모든 요소들을 찾음.
    const divs = document.getElementsByClassName('c1');
    // console.log(divs); //-> HTMLCollection: HTML 요소들을 저장하는 배열.
    for (const e of divs) {
        console.log(e);
        // HTML 요소의 바탕색을 변경.
        e.style.backgroundColor = 'tomato'; 
    }
});

//button#btn3 요소를 찾음.
const btn3 = document.querySelector('button#btn3');

//btn3 요소에 클릭 이벤트 리스너를 설정 -> 모든 div 요소를 찾아서 바탕색을 변경
btn3.addEventListener('click', function (){
    
    const divs = document.getElementsByTagName('div');
    for(const e of divs){
        console.log(e);
        e.style.backgroundColor = 'pink';
    }
});

// button#btn4 요소를 찾음.
const btn4 = document.querySelector('button#btn4');

btn4.addEventListener('click', () => {
   //div#div4 요소를 찾음
   const div4 = document.querySelector('div#div4'); // document.getElement('div4');
   // 커리 겟엘리먼트도 html 위에서부터 아이디가 일치하는지 검사하고 . 쿼리는 태그이름까지 명시가 가능하기때문에
   //태그가 다르면 해당하는 태그의 아이디를 찾을 수 있다
   console.log(div4);
   div4.style.backgroundColor = 'StateBlue'; 
});


    

//button#btn5 요소를 찾음.
const btn5 = document.querySelector('button#btn5');

//btn5 요소에 클릭 이벤트 리스너를 설정 -> 클래스 이름이 c2인 요소(ef0)
btn5.addEventListener('clkic', () => { //엘리먼트 하나하나를 노드라고하고 요소와 노드는 비슷한 의미
    
    const divs = document.querySelectorAll('div.c2'); // 서로 다른 태그에 같은 클래스
//    console.log(divs); //-> NodeList - HTML 요소(노드)들의 배열.
//      for(const d of divs) {
//        d.style.backgrounColr = 'violet';
//      }
    divs.forEach(d => d.style.backgroundColor='violet')
        
});

