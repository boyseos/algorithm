var app = app || {}
app = (()=>{
	let ctx
	let init = x =>{
		sessionStorage.setItem("ctx",x)
		ctx = x
		$.api = api
	}
	
	let onCreate = () =>{
		$.getScript('./resources/js/Algorithm/braceTest.js')
		$.getScript('./resources/js/Algorithm/maze.js')
	}

	let run = x =>{
		init(x)
		onCreate()
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

