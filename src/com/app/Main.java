package com.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.model.Cache;
import com.model.SystemCache;

public class Main {

	private static void store(List<Cache> caches, String file){
		List<String> lines = new ArrayList<String>();
		lines.add(Integer.toString(caches.size()));
		
		for(Cache cache : caches){
			lines.add(cache.toString());
		}
		
		try {
			Files.createDirectories(Paths.get("out"));
			Files.write(Paths.get("out/"+file+".out"), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		List<String> files = new ArrayList<String>();
		files.add("kittens");
		files.add("me_at_the_zoo");
		files.add("trending_today");
		files.add("videos_worth_spreading");
		
		SystemCache system;
		long score, total = 0;
		for(String file : files){
			system = new SystemCache("files/"+file+".in");
			
			store(system.run(), file);
			score = system.getScore();
			System.out.println("Score "+file+": "+score);
			total += score;
		}
		System.out.println("\nTotal score: "+total);
	}
}
