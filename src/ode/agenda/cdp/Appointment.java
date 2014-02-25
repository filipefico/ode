package ode.agenda.cdp;

import java.util.Date;

import javax.persistence.ManyToOne;

import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.event.CalendarsEvent;

public class Appointment implements CalendarEvent {
	
	private String id;
	private String Title;
	private Date BeginDate;
	private Date EndDate;
	private String Content;//assunto
	private String Description;
	private String ContentColor;
	private String HeaderColor;
	private String Zclass;
	private String where;

	private boolean allDay;
	private boolean Locked;
	
	private Calendar calendar;
	private Owner owner;	
	
	private CalendarsEvent event;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}
	
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getContentColor() {
		return ContentColor;
	}
	public void setContentColor(String contentColor) {
		ContentColor = contentColor;
	}
	
	public String getHeaderColor() {
		return HeaderColor;
	}
	public void setHeaderColor(String headerColor) {
		HeaderColor = headerColor;
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getZclass() {
		return Zclass;
	}
	public void setZclass(String zclass) {
		Zclass = zclass;
	}
	
	public boolean isLocked() {
		return Locked;
	}
	public void setLocked(boolean locked) {
		Locked = locked;
	}
	
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	
	@ManyToOne
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public CalendarsEvent getEvent() {
		return event;
	}
	public void setEvent(CalendarsEvent event) {
		this.event = event;
	}
	
	@ManyToOne
	public Owner getOwner(){
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString(){
		return "teste";
	}
}
