package com.jyoti;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookingMeetings {

	private LocalTime officeStartTime;

	private LocalTime officeFinishTime;

	private Map<LocalDate, Set<MeetingBean>> meetings;

	public BookingMeetings(LocalTime officeStartTime,
			LocalTime officeFinishTime, Map<LocalDate, Set<MeetingBean>> meetings) {
		this.officeStartTime = officeStartTime;
		this.officeFinishTime = officeFinishTime;
		this.meetings = meetings;
	}

	public LocalTime getOfficeStartTime() {
		return officeStartTime;
	}

	public LocalTime getOfficeFinishTime() {
		return officeFinishTime;
	}

	public Map<LocalDate, Set<MeetingBean>> getMeetings() {
		return meetings;
	}

}
