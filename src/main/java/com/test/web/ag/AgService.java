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
		public List<List<Integer>> pathList;
		
		public int total;
		public List<Integer> totalList;
		public List<Integer> maze;
		
		public Maze() {
			path = new ArrayList<Integer>();
			pathList = new ArrayList<List<Integer>>();
			maze = new ArrayList<Integer>();
			totalList = new ArrayList<Integer>();
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
			System.out.println("실행");
			boolean run = true;
			int i = 1;
			while(run) {
				System.out.printf("반복 : %d now: %d\n",i++,now);
				if(moveCheck(-size)) move(1);
				else if(moveCheck(size)) move(2);
				else if(moveCheck(-1)) move(3);
				else if(moveCheck(1)) move(4);
				else if(!pathList.contains(path)){
					pathList.add(path);
					path.clear();
					totalList.add(total);
					total = 0;
					now = 0;
					move(4);
				}else run = false;
			}
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
		
		
		
		private boolean moveCheck(int num) {	//이동 가능자리인지 체크
			int z = now + num;
			return 0 < z && z <= maxSize  	//세로축 이동가능 확인
				&& !path.contains(z)		// 지나왔던 경로확인
				&& !(now%size==0 && num==1)	// 우측끝에서 우측이동
				&& !(now%size==1 && num==-1)// 좌측끝에서 좌측이동
				|| (now == 0 && num == 1);
		}
		
		private int mazeRan() {return (int) ((Math.random()-0.5)*200);}	//미로생성시 값 랜덤생성
	}
}
