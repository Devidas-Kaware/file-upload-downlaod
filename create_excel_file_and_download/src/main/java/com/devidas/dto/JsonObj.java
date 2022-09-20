package com.devidas.dto;

import java.util.List;

public class JsonObj {
	private List<Object> footballTeam;

	public List<Object> getFootballTeam() {
		return footballTeam;
	}

	public void setFootballTeam(List<Object> footballTeam) {
		this.footballTeam = footballTeam;
	}

	@Override
	public String toString() {
		return "JsonObj [footballTeam=" + footballTeam + "]";
	}

}
