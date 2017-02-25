package com.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SystemCache {

	private List<Video> videos;
	private List<Endpoint> endpoints;
	private List<Cache> caches;

	public SystemCache(String filename) {
		videos = new ArrayList<Video>();
		endpoints = new ArrayList<Endpoint>();
		caches = new ArrayList<Cache>();
		read(filename);
	}

	private void read(String filename) {
		int line = 0;
		try {
			List<String> lines = Files.readAllLines(Paths.get(filename));

			String[] values = lines.get(line).split(" ");
			int videos = Integer.parseInt(values[0]);
			int endpoints = Integer.parseInt(values[1]);
			int requests = Integer.parseInt(values[2]);
			int caches = Integer.parseInt(values[3]);
			int capacity = Integer.parseInt(values[4]);
			line++;

			// Initialize Cache
			initCaches(caches, capacity);

			// Get videos
			initVideos(videos, lines.get(line));
			line++;

			// Requests
			String[] valuesEndpoint, valuesLatency;
			Endpoint endpoint;
			int latency, endpointCaches;
			for (int i = 0; i < endpoints; i++) {
				valuesEndpoint = lines.get(line).split(" ");
				latency = Integer.parseInt(valuesEndpoint[0]);
				endpointCaches = Integer.parseInt(valuesEndpoint[1]);

				endpoint = new Endpoint(i);
				endpoint.setLatency(latency);
				this.endpoints.add(endpoint);
				for (int c = 0; c < endpointCaches; c++) {
					valuesLatency = lines.get(c + line + 1).split(" ");
					int cacheID = Integer.parseInt(valuesLatency[0]);
					int cacheLatency = Integer.parseInt(valuesLatency[1]);
					endpoint.getCaches().add(this.caches.get(cacheID));
					endpoint.getLatencies().add(cacheLatency);
				}
				line = line + endpointCaches + 1;
			}

			String[] requestDescription;
			Request request;
			int videoId, endpointId, requestsNum;
			for (int i = 0; i < requests; i++) {
				requestDescription = lines.get(line).split(" ");

				videoId = Integer.parseInt(requestDescription[0]);
				endpointId = Integer.parseInt(requestDescription[1]);
				requestsNum = Integer.parseInt(requestDescription[2]);

				request = new Request();
				request.setVideo(this.videos.get(videoId));
				request.setRequests(requestsNum);

				this.endpoints.get(endpointId).getRequests().add(request);
				line++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initCaches(int caches, int capacity){
		Cache cache;
		for (int i = 0; i < caches; i++) {
			cache = new Cache(i);
			cache.setCapacity(capacity);
			this.caches.add(cache);
		}
	}

	private void initVideos(int videos, String line){
		String[] videoSizes = line.split(" ");
		Video video;
		for (int i = 0; i < videos; i++) {
			video = new Video(i);
			video.setSize(Integer.parseInt(videoSizes[i]));
			this.videos.add(video);
		}
	}


	public List<Cache> run() {
		return caches;
	}
}
