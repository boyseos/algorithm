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
		
		public List<Integer> scoreList;
		public List<Integer> totalList;
		public List<Integer> maze;
		
		public Maze() {
			path = new ArrayList<Integer>();
			pathList = new ArrayList<List<Integer>>();
			maze = new ArrayList<Integer>();
			totalList = new ArrayList<Integer>();
			scoreList = new ArrayList<Integer>();
		}
		
		public void mazeCreate() {
			now = 0;
			maxSize = size*size;
			for(int i=0;i<maxSize;i++) {
				maze.add(mazeRan());
			}
		}
		
		public void mazeRun(){
			System.out.println("실행");
			move(4);
			move();
		}
		
		public void setSize(int size) {
			this.size = size;
		}
		
		private void move() {
			Arrays.asList(1,-size,size,-1,maxSize+1).forEach(x->{
				if(moveCheck(x)) {
					now += x;
					path.add(now);
					scoreList.add(maze.get(now-1));
					System.out.println("현재  : "+path+" score : "+scoreList);
					move();
				}else if(x==maxSize+1) {
					pathList.add(path);
					totalList.add(scoreList.size() != 1 ? scoreList.stream().reduce(0,(a,b)->a+b) : scoreList.get(0));
					scoreList.remove(scoreList.size()-1);
					path = new ArrayList<>(path);
					path.remove(path.size()-1);
					now = path.size() != 0 ? path.get(path.size()-1) : 1;
					//System.out.println("빽하고 : "+path+" score : "+scoreList+"패스리스트 : "+pathList);
				}
			});
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
				scoreList.add(maze.get(now-1));
				//System.out.println("시작  : "+path+" score : "+scoreList);
			}
			return test;
		}
		
		private boolean moveCheck(int num) {	//이동 가능자리인지 체크
			int z = now + num;
			boolean sero = 0 < z && z <= maxSize,
					rightToright = !(now%size==0 && num==1),
					leftToleft = !(now%size==1 && num==-1),
					pathTest = !path.contains(z),
					pathListTest = !pathList.contains(path);
			//System.out.printf("이동체크 %d\t 세로 : %s 우측 : %s 좌측 : %s 경로 : %s 경로목록 : %s\n"
			//		,z,sero,rightToright,leftToleft,pathTest,pathListTest);
			return sero && rightToright && leftToleft && pathTest && pathListTest || (now==0&&num==1);
		}
		
		private int mazeRan() {return (int) ((Math.random()-0.5)*200);}	//미로생성시 값 랜덤생성
	}
}
