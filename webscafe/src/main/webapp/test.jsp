<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="resources/customer.js"></script>
<title>SelectDesk</title>
<script>
const menuWrap = document.querySelector('.menu-wrap');

function select(ulEl, liEl){
    Array.from(ulEl.children).forEach(
        v => v.classList.remove('selected')
    )
    if(liEl) liEl.classList.add('selected');
}

menuWrap.addEventListener('click', e => {
    const selected = e.target;
    select(menuWrap, selected);
})


</script>
</head>
<style>
     .menu-wrap li{
            display: inline-block;
            background: #dcb8b8;
            border-radius: 10px;
            padding: 10px;}
            .menu-wrap li.selected{
                background-color: #e26262;
                color:#fff;
                font-weight: 600;
            }



</style>
<body>
    
   <ul class="menu-wrap">
        <li>menu01</li>
        <li>menu02</li>
        <li>menu03</li>
        <li>menu04</li>
    </ul>



    
  </body>
</html>