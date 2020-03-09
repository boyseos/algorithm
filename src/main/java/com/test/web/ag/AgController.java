package com.test.web.ag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class AgController {
	@Autowired AgService service;
	
	@GetMapping("/brace/{string}")
	public boolean braceTest(@PathVariable String string) {
		return service.braceTest(string);
	}
	
	@PostMapping("/brace")
	public boolean braceTest(@RequestBody Map<String, String> param) {
		return service.braceTest(param.get("msg"));
	}
	
	@GetMapping("/maze/{size}")
	public AgService.Maze maze(@PathVariable int size) {
		System.out.println("메이즈 받은값 : " + size);
		AgService.Maze maze = service.getMaze();
		maze.setSize(size);
		maze.mazeCreate();
		return maze;
	}

}
