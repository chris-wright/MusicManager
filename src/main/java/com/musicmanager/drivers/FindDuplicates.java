package com.musicmanager.drivers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;

public class FindDuplicates {
	public static void main(String[] args) {
		
		int i = 0;
		Long start = System.currentTimeMillis();
		System.out.println();
		File dir = new File("D:\\backup\\music");
		String[] extensions = {"mp3"};
		
		ArrayListMultimap<String, File> fileMap = ArrayListMultimap.create();
		System.out.println("Iterating over files...");
		
		Iterator<File> iterator2 = FileUtils.iterateFiles(dir, extensions, true);
		while(iterator2.hasNext()) {
			File file1 = iterator2.next();
			Iterator<File> iterator3 = FileUtils.iterateFiles(dir, extensions, true);
			while(iterator3.hasNext()) {
				try {
					File file2 = iterator3.next();
					if(FileUtils.contentEquals(file1, file2) && !file1.getAbsoluteFile().toString().equals(file2.getAbsoluteFile().toString())) {
						i++;
						System.out.println("Found duplicate (" + i + ") - " + file1.getName() + " = " + file2.getName());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("Took: " + (end - start) + "ms");
	}
}
