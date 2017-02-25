package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.Video;

public class Cache {

	private int id;
	private int capacity;
	private int remain;
	private List<Video> videos;

	public Cache(int id) {
		this.id = id;
		this.videos = new ArrayList<Video>();
	}

	public int getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public void addVideo(Video video) {
		int index = this.hasVideo(video);
		if (index < 0 && video.getSize() <= remain) {
			remain = remain - video.getSize();
			videos.add(video);
		}
	}

	public void deleteVideo(Video video) {
		int index = this.hasVideo(video);
		if (index > -1) {
			remain = remain + video.getSize();
			videos.remove(index);
		}
	}

	public int hasVideo(Video video) {
		for (int i = 0; i < videos.size(); i++) {
			if (videos.get(i).getId() == video.getId()) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		String line = Integer.toString(this.getId());
		for (Video video : videos) {
			line += " " + video.getId();
		}

		return line;
	}
}
