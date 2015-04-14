package com.musicmanager.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import fm.last.musicbrainz.data.dao.ReleaseDao;
import fm.last.musicbrainz.data.dao.impl.ReleaseDaoImpl;
import fm.last.musicbrainz.data.model.Release;

public class MusicBrainzTest {

	@Test
	public void test() {
		ReleaseDao releaseDao = new ReleaseDaoImpl();
		UUID musicBrainzId = UUID.fromString("c69b70bc-049e-3e3f-a5e4-5a1b4d62105f");
		Release release = releaseDao.getByGid(musicBrainzId);
	}

}
