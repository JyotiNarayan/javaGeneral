package com.jyoti;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

import static java.lang.Integer.parseInt;

public class BookingScheduler {

	private DateTimeFormatter dateFormatter = DateTimeFormat
			.forPattern("yyyy-MM-dd");
	private DateTimeFormatter separatedTimeFormatter = DateTimeFormat
			.forPattern("HH:mm");

	public BookingMeetings schedule(String meetingRequest) {
		try {
			if (meetingRequest == null || meetingRequest.isEmpty()) {
				System.err
						.println(" Employee has requested for booking is not a valid input");
				return null;
			}

			String[] requestLines = meetingRequest.split("\n");

			String[] officeHoursTokens = requestLines[0].split(" ");
			LocalTime officeStartTime = new LocalTime(
					parseInt(officeHoursTokens[0].substring(0, 2)),
					parseInt(officeHoursTokens[0].substring(2, 4)));
			LocalTime officeFinishTime = new LocalTime(
					parseInt(officeHoursTokens[1].substring(0, 2)),
					parseInt(officeHoursTokens[1].substring(2, 4)));

			Map<LocalDate, Set<MeetingBean>> meetings = new HashMap<LocalDate, Set<MeetingBean>>();

			for (int i = 1; i < requestLines.length; i = i + 2) {

				String[] meetingSlotRequest = requestLines[i + 1].split(" ");
				LocalDate meetingDate = dateFormatter
						.parseLocalDate(meetingSlotRequest[0]);

				MeetingBean meeting = extractMeeting(requestLines[i],
						officeStartTime, officeFinishTime, meetingSlotRequest);
				if (meeting != null) {
					if (meetings.containsKey(meetingDate)) {
						meetings.get(meetingDate).remove(meeting);
						meetings.get(meetingDate).add(meeting);
					} else {
						Set<MeetingBean> meetingsForDay = new TreeSet<MeetingBean>();
						meetingsForDay.add(meeting);
						meetings.put(meetingDate, meetingsForDay);
					}
				}
			}

			return new BookingMeetings(officeStartTime, officeFinishTime,
					meetings);
		} catch (Exception e) {
			return null;
		}

	}

	private MeetingBean extractMeeting(String requestLine,
			LocalTime officeStartTime, LocalTime officeFinishTime,
			String[] meetingSlotRequest) {
		String[] employeeRequest = requestLine.split(" ");
		String employeeId = employeeRequest[2];

		LocalTime meetingStartTime = separatedTimeFormatter
				.parseLocalTime(meetingSlotRequest[1]);
		LocalTime meetingFinishTime = new LocalTime(
				meetingStartTime.getHourOfDay(),
				meetingStartTime.getMinuteOfHour())
				.plusHours(parseInt(meetingSlotRequest[2]));

		if (meetingTimeOutsideOfficeHours(officeStartTime, officeFinishTime,
				meetingStartTime, meetingFinishTime)) {
			System.err.println("EmployeeId:: " + employeeId
					+ " has requested booking which is outside office hour.");
			return null;
		} else {
			return new MeetingBean(employeeId, meetingStartTime, meetingFinishTime);

		}
	}

	private boolean meetingTimeOutsideOfficeHours(LocalTime officeStartTime,
			LocalTime officeFinishTime, LocalTime meetingStartTime,
			LocalTime meetingFinishTime) {
		return meetingStartTime.isBefore(officeStartTime)
				|| meetingStartTime.isAfter(officeFinishTime)
				|| meetingFinishTime.isAfter(officeFinishTime)
				|| meetingFinishTime.isBefore(officeStartTime);
	}
}
