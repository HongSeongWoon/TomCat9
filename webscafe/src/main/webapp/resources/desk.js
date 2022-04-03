/**
 좌석예약* 
 */
const tit = document.querySelector('#title');
const bg = document.querySelector('.body_container');

const CLASS_CLICKED = "clicked";



function handleClick() {
  const hasClass = bg.classList.contains(CLASS_CLICKED);
  
  if(hasClass) {
    bg.classList.remove(CLASS_CLICKED);
  } else {
    bg.classList.add(CLASS_CLICKED);
  }
}



function init() {
  tit.addEventListener('click', handleClick);  
}

init();