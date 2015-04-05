package com.musicmanager.test;


import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;

public class ID3Test {
	
	MP3Actions actioner;
	LastFMActions lActioner;

	@Before
	public void setUp() throws Exception {
		actioner = new MP3Actions();
		lActioner = new LastFMActions();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			assertNotNull(lActioner.findTrack(new MP3File("D:\\backup\\music\\04 Fix You.mp3")));
			assertNotNull(lActioner.findTrack(new MP3File("D:\\backup\\music\\06 Citizen Erased.mp3")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
