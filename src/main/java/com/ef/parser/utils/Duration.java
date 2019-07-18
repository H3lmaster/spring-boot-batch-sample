package com.ef.parser.utils;

public enum Duration {

	DAILY("daily"), HOURLY("hourly");
	
	private String duration;

	Duration(String duration) {
		this.setDuration(duration);
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public static Duration getByName(String duration) {
		for(Duration dur : Duration.values()) {

            if (dur.duration.equalsIgnoreCase(duration)) return dur;
        }
        throw new IllegalArgumentException("Invalid duration");
	}
}
