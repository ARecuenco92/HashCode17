package com.model;

public class Saving implements Comparable<Saving>{

	private Cache cache;
	private Video video;
	private long saving;

	public Saving(Cache cache, Video video) {
		this.cache = cache;
		this.video = video;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public long getSaving() {
		return saving;
	}

	public void setSaving(long saving) {
		this.saving = saving;
	}
	
	public String getKey(){
		return "C"+cache.getId()+"v"+video.getId();
	}

	@Override
	public int compareTo(Saving o) {
		return (int) (o.getSaving() - saving );
	}

}
