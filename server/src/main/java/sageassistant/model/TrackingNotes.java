package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class TrackingNotes extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String TrackCode;
	private String Note;
	private String NoteBy;
	private Date NoteDate;

	public String getTrackCode() {
		return TrackCode;
	}

	public void setTrackCode(String trackCode) {
		TrackCode = trackCode;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getNoteBy() {
		return NoteBy;
	}

	public void setNoteBy(String noteBy) {
		NoteBy = noteBy;
	}

	public Date getNoteDate() {
		return NoteDate;
	}

	public void setNoteDate(Date noteDate) {
		NoteDate = noteDate;
	}
}
