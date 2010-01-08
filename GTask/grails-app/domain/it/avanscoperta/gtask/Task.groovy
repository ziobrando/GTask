package it.avanscoperta.gtask

import org.joda.time.*

class Task {

  String name
	String description
  DateTime dueDate
  DateTime creationDate
	DateTime completionDate
  boolean completed = false

  static constraints = {
  }

  static transients = ["overdue"]

  public static Task create(Map map) {
    Task task = new Task(map)
    task.creationDate = new DateTime()
    task
  }

  boolean isOverdue() {
	  if (dueDate) {
			new DateTime() >= dueDate && !completed  
	  } else { false }
  }

  // Business methods -------------------------

  Task complete() {
	  this.completed = true
	  this.completionDate = new DateTime()
	  this
  }

  Task reopen() {
	  this.completed = false
	  this
  }

}
