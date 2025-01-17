/**
 * 
 */

// 이벤트는 항상 이렇게 작성하고 모든 코드는 이 바디안에 발생하면 그때 펑션을 실행
// 서버에 html을 받아서 도큐먼트 바디 나브 링ㅋ 이런 객체로 화면에 그려주고 
// 모든 html 요소를 개체로 다 만들고 나면 그때 발생하는 이벤트가 DOMContentLoaded 그때 브라우저가 이 애들 실행
// 만약 만들어지기 전에 실행이 된다하면 이 이벤트가 요소들을 찾을 수 없기 때문에 그때 NaN 이나 undefinde 에러
// html 도큐먼트가 만들어지고 실행되는 콜백안에서 작성하는게 안전

// DOM(Document Object Model) : 문서 객체 모델
// document 객체에 이벤트 리스너를 설정.
// DOMContentLoaded 이벤트: 브라우저가 HTML 문서의 모든 요소들을 생성하면 발생하는 이벤트.
document.addEventListener('DOMContentLoaded', function () {
    // HTML 문서의 모든 요소들이 생성된 이후에 실행될 코드.
    
    
    // button#btnInput 요소를 찾음.
    const btnInput = document.querySelector('button#btnInput');
    
    // btnInput에 클릭 이벤트 리스너를 설정.
    btnInput.addEventListener('click', function(e) {
        //console.log(e); //-> PointerEvent 객체
        //e.target 프로퍼티: 이벤트가 발생한 HTML 요소.
        
        //input#itemInput 요소를 찾음
        const itemInput = document.querySelector('input#itemInput');
        //ul#itemList 요소를 찾음
        const itemList = document.querySelector('ul#itemList');
        //itemList에 입력된 문자열을 itemList의 <li>로 추가(append).
        itemList.innerHTML += `<li> ${itemInput.value} </li>`
        //itemInput에 입력된 내용을 지움.
        itemInput.value = '';
    }); 
        
    //input#itemInput2 요소를 찾음.
    const itemInput2 = document.querySelector('input#itemInput2');
    
    //itemInput2 요소에 'keydown' 이벤트 리스너를 설정.
    itemInput2.addEventListener('keydown', (e) => {
        //console.log(e); //-> keyboardEvent
        // e.key 프로퍼티: 어떤 키보드가 down이 됐는 지를 알려주는 프로퍼티.
        // 'Enter'가 입력이 됐을 때 ul#itemList2
        // 입력된 문자열을 ul#itemList2에 <li>로 추가(append)
         if(e.key === 'Enter'){
            const itemList2 = document.querySelector('ul#itemList2');
            itemList2.innerHTML += `<li> ${itemInput2.value} </li>`
            itemInput2.value = '';
        }
    });
    
    // input#userId 요소를 찾음.
    const userId = document.querySelector('input#userId');
    
    // userId에 'change' 이벤트 리스너를 설정.
    userId.addEventListener('change', (e) => {
       //console.log(e); //-> Event
       //userId에 입력된 값을 div#result에 출력.
       const result = document.querySelector('div#result');
       result.innerHTML = `<span style="color: red;">${userId.value}</span>`;
    });
    //무언갈 입력창에 입력하고 마우스 포커스를 잃었을때 그때 이벤트가 발생한다.
    
    // img#bulb 요소를 찾음.    
    const bulb = document.querySelector('img#bulb');
    // bulb에 'mouseenter' 이벤트 리스너를 설정 -> 이미지를 bulb_on.gif로 교체.
    bulb.addEventListener('mouseenter', () => {
        bulb.src= 'images/bulb_on.gif';
        bulb.alt= 'bulb_on';
    });
    // bulb에 'mouseleave' 이벤트 리스너를 설정 -> 이미지를 bulb_off.gif로 교체.
    bulb.addEventListener('mouseleave', () => {
       bulb.src= 'images/bulb_off.gif';
       bulb.alt= 'bulb_off'; 
       //하나의 이미지에 대해서 여러번 써도 됨, 각각 여러번 클릭,리브,체인지, 여러가지를 다 섞어도 된다는 말,.
    });
});