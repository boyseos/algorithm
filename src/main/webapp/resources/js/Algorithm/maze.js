(()=>{
	$('#maze').remove()
	$('<div>',{
		id: 'maze',
		style:`
			width:70%;
			height:100px;
			display: inline-flex;
			margin:auto;`
	}).appendTo('body')
	$('<input></input>').attr('placeholder',"미로테스트: 3~10까지의 숫자 입력")
	.css("width","500").appendTo('#maze')
	.keyup(event=>{
		if(event.key == 'Enter' && event.target.value){
			$.getJSON(`${sessionStorage.getItem("ctx")}/test/maze/${event.target.value}`, d=> {
				console.log(d)
				createMaze(event.target.value,d)
			}) 
		}
	})
	
	let createMaze = (num,maze) => {
		let z = maze.totalList.reduce((a,b)=>Math.max(a,b))
		
		let y = maze.totalList.indexOf(z)
		let x = maze.pathList[y]
		console.log(x,y,z)
		$('#mazeResult').remove()
		$('<div>').attr('id','mazeResult').css({
			'display': 'grid',
	    	'width': `${num}00px`,
	    	'grid-template': `repeat(${num},1fr)/repeat(${num},1fr)`,
	    	'height': `${num}00px`,	
		}).appendTo('#maze')
		$.each(maze.maze,(i, elt) => {
			$(`<div>${elt}</div>`)
			.css('background-color',x.includes(i+1) ? 'aqua':'aquamarine')
			.appendTo('#mazeResult')
		})
	}
})()