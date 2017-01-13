package llyska.events;

public interface DataEvent {
	DataEventGenerator getSource();
	Object getData();
}