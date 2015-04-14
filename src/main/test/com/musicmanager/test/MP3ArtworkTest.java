package com.musicmanager.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.junit.Test;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.musicmanager.actions.LastFMActions;
import com.musicmanager.actions.MP3Actions;

import de.umass.lastfm.Track;
import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;

public class MP3ArtworkTest {

	@Test
	public void test() {
		
//		MP3Actions actions = new MP3Actions();
//		BufferedImage img = actions.getAlbumArt("C:\\git\\Other\\MusicManager\\resources\\music\\09 Headup.mp3");
//		assertNotNull(img);
//		
//		img = actions.getAlbumArt("C:\\git\\Other\\MusicManager\\resources\\music\\01 Ready For The Floor.mp3");
//		assertNotNull(img);

		LastFMActions actioner = new LastFMActions();

		Track t = actioner.findTrack("Human", "The Killers");
		
		CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();
		UUID mbid = UUID.fromString(t.getArtistMbid());

		CoverArt coverArt = null;
		try {
		  coverArt = client.getByMbid(mbid);
		  if (coverArt != null) {
		    for (CoverArtImage coverArtImage : coverArt.getImages()) {
		      File output = new File(mbid.toString() + "_" + coverArtImage.getId() + ".jpg");
		      FileUtils.copyInputStreamToFile(coverArtImage.getImage(), output);
		    }
		  }
		} catch (Exception e) {
		  System.out.println(e.getStackTrace());
		}
	}

}
