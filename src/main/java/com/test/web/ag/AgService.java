package com.test.web.ag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

@Service
public class AgService {
	public boolean braceTest(String temp) {
		List<String> list = new ArrayList<String>();
		for(String x : temp.split("")) {
			if(Arrays.asList("[","{","(").contains(x)) {
				list.add(x);
			}else if(Arrays.asList("]","}",")").contains(x)) {
				if(list.size()==0) return false;
				if(list.get(list.size()-1).equals(this.braceReverse(x))) {
					list.remove(list.size()-1);
				}else return false;
			}
		}	
		return list.size() == 0;
	}
	
	public String braceReverse(String x) {
		String temp = "";
		switch (x) {
			case "]": temp = "["; break;
			case "}": temp = "{"; break;
			case ")": temp = "("; break;
		}
		return temp;
	}
	
	public Maze getMaze() {
		return new Maze();
	}
	
	public class Maze {
		private int now;
		private int maxSize;
		private int size;
		
		public List<Integer> path;
		public int total;
		public List<Integer> maze;
		
		public Maze() {
			path = new ArrayList<Integer>();
			maze = new ArrayList<Integer>();
		}
		
		public void mazeCreate() {
			now = 0;
			maxSize = size*size;
			for(int i=0;i<maxSize;i++) {
				maze.add(mazeRan());
			}
			move(4);
		}
		
		public void mazeRun(){
			
		}
		
		public void setSize(int size) {
			this.size = size;
		}
		
		private boolean move(int move) {
			boolean test = false;
			switch (move) {
				case 1: move = -size; break;	//up
				case 2: move = size; break;		//down
				case 3: move = -1; break;		//left
				case 4: move = +1; break;		//right
			}
			if(test = moveCheck(move)) {
				now += move;
				path.add(now);
				total += maze.get(now-1);
			}
			return test;
		}
		
		private boolean moveCheck(int num) {
			int z = now + num;	
			return 0 < z && z <= maxSize;
		}
		
		private int mazeRan() {return (int) ((Math.random()-0.5)*200);}
	}
}
