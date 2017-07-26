package com.jyoti;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CreateMeetingOutput {

    private DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");

    private BookingScheduler bookingScheduler;

    public CreateMeetingOutput(BookingScheduler bookingScheduler) {
        this.bookingScheduler = bookingScheduler;
    }

    public String print(String meetingRequest){
        BookingMeetings meetingsScheduleBooked = bookingScheduler.schedule(meetingRequest);

        return buildMeetingScheduleString(meetingsScheduleBooked);

    }

    private String buildMeetingScheduleString(BookingMeetings meetingsScheduleBooked) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LocalDate, Set<MeetingBean>> meetingEntry : meetingsScheduleBooked.getMeetings().entrySet()) {
            LocalDate meetingDate = meetingEntry.getKey();
            sb.append(dateFormatter.print(meetingDate)).append("\n");
            Set<MeetingBean> meetings = meetingEntry.getValue();
            for (MeetingBean meeting : meetings) {
                sb.append(timeFormatter.print(meeting.getStartTime())).append(" ");
                sb.append(timeFormatter.print(meeting.getFinishTime())).append(" ");
                sb.append(meeting.getEmployeeId()).append("\n");
            }

        }
        return sb.toString();
    }
}
