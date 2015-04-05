package com.musicmanager.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnsupportedOperationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			MP3File f = new MP3File("D:\\backup\\music\\Lady Gaga\\Lady GaGa - The Fame [2008][CD+SkidVid_XviD+Cov]320Kbps\\02  Lady GaGa - Lovegame.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
