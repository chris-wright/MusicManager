package com.musicmanager.actions;

import java.util.Collection;

import org.farng.mp3.MP3File;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;

public class LastFMActions {
	
	String key = "082af20fbd8d96480496d231e906da2a"; //this is the key used in the Last.fm API examples
	String user = "chrisanwright";
	
	public Track findTrack(MP3File mp3File) {
		if(mp3File.getID3v2Tag() == null) {
			return null;
		} else {
			String title = mp3File.getID3v2Tag().getSongTitle();
			String author = mp3File.getID3v2Tag().getLeadArtist();
			return findTrack(title, author);
		}
	}
	
	public Collection<Album> findAlbums(String title, String artist) {
		Collection<Track> albums = Artist.
		for(Album album : albums) {
			if(album.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + artist);
			}
		}
		return tracks;
	}
	
	public Collection<Track> findTracks(String title, String artist) {
		Collection<Track> tracks = Artist.getTopTracks(artist, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + artist);
			}
		}
		return tracks;
	}
	
	public Collection<Track> findTracks(String title) {
		Collection<Track> tracks = Track.search(title, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + track.getArtist());
			}
		}
		return tracks;
	}
	
	public Track findTrack(String title, String artist) {
		Collection<Track> tracks = Artist.getTopTracks(artist, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + artist);
				return track;
			}
		}
		System.out.println("No track found - " + title + " by " + artist);
		return null;
	}
	
	public Track findTrack(String title) {
		Collection<Track> tracks = Track.search(title, key);
		for(Track track : tracks) {
			if(track.getName().equals(title)) {
				System.out.println("Found - " + title + " by " + track.getArtist());
				return track;
			}
		}
		System.out.println("No track found - " + title);
		return null;
	}
}
