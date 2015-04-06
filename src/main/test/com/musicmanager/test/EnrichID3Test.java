package com.musicmanager.test;

import static org.junit.Assert.assertNotNull;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;

public class EnrichID3Test {
	
	MP3Actions actioner;
	LastFMActions lActioner;
	ClassLoader classLoader;

	@Before
	public void setUp() throws Exception {
		actioner = new MP3Actions();
		lActioner = new LastFMActions();
		classLoader = getClass().getClassLoader();
	}

	@Test
	public void test() {
		MP3File file = actioner.getNewMP3File("C:\\git\\Other\\MusicManager\\resources\\music\\[U-8993][T-26645][P-832866]17 time is running out (live at earl.mp3");
		assertNotNull(file);
		AbstractID3v2 tag = file.getID3v2Tag();
		
	}

}
