package com.model;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {

	private int id;
	private int latency;
	private List<Cache> caches;
	private List<Integer> latencies;
	private List<Request> requests;

	public Endpoint(int id) {
		this.id = id;
		this.caches = new ArrayList<Cache>();
		this.latencies = new ArrayList<Integer>();
		this.requests = new ArrayList<Request>();
	}

	public int getId() {
		return id;
	}

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}

	public List<Cache> getCaches() {
		return caches;
	}

	public List<Integer> getLatencies() {
		return latencies;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public boolean hasCaches() {
		return caches.size() > 0;
	}

	public int getRequestNum() {
		return this.requests.size();
	}

}
