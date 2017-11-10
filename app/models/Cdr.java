package models;

import java.util.Date;

import dtos.CdrDto;

public class Cdr {

	public Date calldate;
	public String clid;
	public String src;
	public String dst;
	public String dcontext;
	public String channel;
	public String dstchannel;
	public String lastapp;
	public String lastdata;
	public Integer duration;
	public Integer billsec;
	public String disposition;
	public Integer amaflags;
	public String accountcode;
	public String uniqueid;
	public String userfield;
	public Integer sequence;
	public String linkeid;
	public Date start;
	public Date answer;
	public Date end;
	
	public CdrDto createDto(Cdr c){
		CdrDto dto = new CdrDto();
		
		dto.calldate = c.calldate;
		dto.src = c.src;
		dto.dst = c.dst;
		dto.clid = c.clid;
		dto.channel = c.channel;
		dto.dstchannel = c.dstchannel;
		dto.billsec = c.billsec;
		dto.disposition = c.disposition;
		dto.answer = c.answer;
		dto.end = c.end;
		dto.incall = false;
		
		return dto;
	}
}
