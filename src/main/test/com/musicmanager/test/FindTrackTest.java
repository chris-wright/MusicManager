package com.musicmanager.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.musicmanager.actions.LastFMActions;

import de.umass.lastfm.Track;

public class FindTrackTest {
	
	LastFMActions actioner;

	@Before
	public void setUp() throws Exception {
		actioner = new LastFMActions();
	}

	@Test
	public void test() {
		Track t = actioner.findTrack("Human", "The Killers");
		assertNotNull(t);
//		assertNotNull(actioner.findTrack("Uprising", "Muse"));
//		assertNull(actioner.findTrack("Not a Song", "Muse"));
//		assertNotNull(actioner.findTrack("New Born", "Muse"));
	}

}
