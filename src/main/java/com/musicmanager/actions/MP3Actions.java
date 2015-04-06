package com.musicmanager.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;

public class MP3Actions {
	
	FileActions fActioner = new FileActions();
	
	public String getCleansedFile_unmatchedParenthesis(String filename) {
		String newFilename = filename.replace("(", "");
		newFilename = newFilename.replace("[", "");
		File oldFile = new File(filename);
		File newFile = new File(newFilename);
		try {
			FileUtils.copyFile(oldFile, newFile);
			fActioner.moveFile(oldFile, "C:\\git\\Other\\MusicManager\\resources\\backup_music");
			//oldFile.delete();
			return newFile.getAbsolutePath();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return filename;
	}
	
	public MP3File getNewMP3File(String filename) {
		try {
			System.out.println("Filename: " + filename);
			MP3File file = new MP3File(filename);
			return file;
		} catch (IOException e) {
			System.out.println("Hit IOException - " + e.getMessage());
			fActioner.copyAndDelete(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\corrupt_music");
			//fActioner.moveFile(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\corrupt_music");
			return null;
		} catch (TagException e) {
			if(e.getCause().getMessage().contains("Unmatched parenthesis")) {
				try {
					return new MP3File(getCleansedFile_unmatchedParenthesis(filename));
				} catch (IOException e1) {
					e1.printStackTrace();
					fActioner.moveFile(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\corrupt_music");
					return null;
				} catch (TagException e1) {
					e1.printStackTrace();
					fActioner.moveFile(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\corrupt_music");
					return null;
				}
			}
		} catch (UnsupportedOperationException e) {
			fActioner.moveFile(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\corrupt_music");
			return null;
		}
		fActioner.moveFile(new File(filename), "C:\\git\\Other\\MusicManager\\resources\\question");
		return null;
	}
	
	public String getTagStatus(MP3File file) {
		StringBuilder sb = new StringBuilder();
		if(file.getID3v2Tag() != null) {
			AbstractID3v2 tag = file.getID3v2Tag();
			if(tag.getSongTitle().isEmpty() || tag.getSongTitle() == null) {
				sb.append("title,");
			} else if(tag.getLeadArtist().isEmpty() || tag.getLeadArtist() == null) {
				sb.append("artist,");
			} else if(tag.getAlbumTitle().isEmpty() || tag.getAlbumTitle() == null) {
				sb.append("album,");
			} else if(tag.getSongGenre().isEmpty() || tag.getSongGenre() == null) {
				sb.append("genre,");
			} else if(tag.getTrackNumberOnAlbum().isEmpty() || tag.getTrackNumberOnAlbum() == null) {
				sb.append("tracknumber,");
			} else if(tag.getYearReleased().isEmpty() || tag.getYearReleased() == null) {
				sb.append("released,");
			}
		}
		if(!sb.toString().isEmpty()) {
			return "Missing - " + file.getMp3file().getName() +  " - " + sb.toString();
		}
		return "";
	}
	
	public boolean hasFullTags(MP3File file) {
		if(file.getID3v2Tag() != null) {
			AbstractID3v2 tag = file.getID3v2Tag();
			if(tag.getSongTitle().isEmpty() || tag.getSongTitle() == null) {
				return false;
			} else if(tag.getSongTitle().isEmpty() || tag.getSongTitle() == null) {
				return false;
			} else if(tag.getLeadArtist().isEmpty() || tag.getLeadArtist() == null) {
				return false;
			} else if(tag.getAlbumTitle().isEmpty() || tag.getAlbumTitle() == null) {
				return false;
			} else if(tag.getSongGenre().isEmpty() || tag.getSongGenre() == null) {
				return false;
			} else if(tag.getSongGenre().isEmpty() || tag.getSongGenre() == null) {
				return false;
			} else if(tag.getTrackNumberOnAlbum().isEmpty() || tag.getTrackNumberOnAlbum() == null) {
				return false;
			} else if(tag.getYearReleased().isEmpty() || tag.getYearReleased() == null) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public List<String> getFullTagStatus() {
		List<String> files = new ArrayList<String>();
		String[] extensions = {"mp3"};
		Iterator<File> iterator = FileUtils.iterateFiles(new File("D:\\backup\\music"), extensions, true);

		while(iterator.hasNext()) {
			try {
				File f = iterator.next();
				MP3File file = getNewMP3File(f.getAbsolutePath());
				files.add(this.getTagStatus(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return files;
	}
	
	public List<MP3File> getFilesWithoutFullTags() {
		List<MP3File> files = new ArrayList<MP3File>();
		FileActions actions = new FileActions();
		String[] extensions = {"mp3"};
		Iterator<File> iterator = FileUtils.iterateFiles(new File("D:\\backup\\music"), extensions, true);

		while(iterator.hasNext()) {
			try {
				File f = iterator.next();
				MP3File file = getNewMP3File(f.getAbsolutePath());
				if(hasFullTags(file)) {
					files.add(file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return files;
	}
	
	public List<MP3File> getFilesWithoutTags() {
		List<MP3File> files = new ArrayList<MP3File>();
		FileActions actions = new FileActions();
		String[] extensions = {"mp3"};
		Iterator<File> iterator = FileUtils.iterateFiles(new File("D:\\backup\\music"), extensions, true);

		while(iterator.hasNext()) {
			File f = iterator.next();
			MP3File mf = getNewMP3File(f.getAbsolutePath());
			if(mf != null) {
				files.add(mf);
			}
		}
		return files;
	}
}
