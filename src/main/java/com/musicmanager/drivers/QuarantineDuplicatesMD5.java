package com.musicmanager.drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;

public class QuarantineDuplicatesMD5 {
	public static void main(String[] args) {
		
		int i = 0;

		Long start = System.currentTimeMillis();
		
		File dir = new File("D:\\backup\\music");
		
		if(!new File("D:\\backup\\music\\duplicates").mkdirs()) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String[] extensions = {"mp3"};
		
		ArrayListMultimap<String, File> fileMap = ArrayListMultimap.create();
		System.out.println("Iterating over files...");
		@SuppressWarnings("unchecked")
		Iterator<File> iterator = FileUtils.iterateFiles(dir, extensions, true);
		while(iterator.hasNext()) {
			i++;
			System.out.println(i);
			File file = iterator.next();
			try {
				fileMap.put(MD5Checksum.getMD5Checksum(file.getAbsolutePath()), file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done!");
		System.out.println("Checking files...");
		for(String key : fileMap.keys()) {
			List<File> files = fileMap.get(key);
			if(files.size() > 1) {
				boolean first = true;
				for(File file : files) {
					if(!first) {
						System.out.println("Moving file - " + file.getAbsolutePath() + " - " + file.getName());
						//file.renameTo(new File("D:\\backup\\music\\duplicates\\" + file.getName()));
						first = !first;
					}
						
				}
			}
		}
		System.out.println("Done!");
		Long end = System.currentTimeMillis();
		System.out.println("Took: " + (end - start) + "ms");
		
//		Iterator<File> iterator2 = FileUtils.iterateFiles(dir, extensions, true);
//		while(iterator2.hasNext()) {
//			File file1 = iterator2.next();
//			Iterator<File> iterator3 = FileUtils.iterateFiles(dir, extensions, true);
//			while(iterator3.hasNext()) {
//				try {
//					File file2 = iterator3.next();
//					if(FileUtils.contentEquals(file1, file2) && !file1.getAbsoluteFile().toString().equals(file2.getAbsoluteFile().toString())) {
//						i++;
//						System.out.println("Found duplicate (" + i + ") - " + file1.getName() + " = " + file2.getName());
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}
}