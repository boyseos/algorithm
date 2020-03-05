var app = app || {}
app = (()=>{
	let init = x =>{
		sessionStorage.setItem("ctx",x)
		$.api = api
	}
	
	let onCreate = () =>{
		braceTest()
	}

	let braceTest = () =>{
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
		.keyup(e=>{
			if(e.key == 'Enter' && e.target.value){
				$.api(`/test/brace`,{msg: e.target.value},d=>{
					$('#braceResult').text(d ? "정상" : "에러")
				})
			}
		})
		$('<h3 id="braceResult">테스트</h3>').appendTo('#braceTest')
	}
	
	
	let run = x =>{
		init(x)
		setTimeout(() => {
			onCreate()
		},10)
	}
	
	let api = (url,data,success) =>{
		$.ajax({
			url : sessionStorage.getItem("ctx")+url,
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(data),
			contentType : 'application/json',
			success : success,
			error : e => {
				alert(`${url} ${e} 실패`)
			}
		})
	}
	
	return {run}
})()

