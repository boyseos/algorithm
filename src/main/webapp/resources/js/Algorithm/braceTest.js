(() =>{
	$('#braceTest').remove()
	$('<div>',{
		id: 'braceTest',
		style:`
			width:70%;
			height:100px;
			display: inline-flex;
			margin:auto;`
	}).appendTo('body')
	$('<input></input>').attr('placeholder',"괄호수가 맞는지 순서가 맞는지등을 판단")
	.css("width","500").appendTo('#braceTest')
	.keyup(event=>{
		if(event.key == 'Enter' && event.target.value){
			$.api(`/test/brace`,{msg: event.target.value},d=>{
				$('#braceResult').text(d ? "정상" : "에러")
			})
		}
	})
	$('<h3 id="braceResult">테스트</h3>').appendTo('#braceTest')
})()