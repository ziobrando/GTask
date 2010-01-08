package it.avanscoperta.gtask

import grails.test.*
import org.joda.time.*

class TaskTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

  void test_Task_created_is_not_null() {
	  def task = Task.create(name:"Un task")
	
		assertNotNull task
  }

  void test_Task_creation_date_is_not_null() {
		def task = Task.create(name:"Un Task")
		
		assertNotNull task.creationDate
  }

  void test_Task_created_is_not_overdue() {
		def task = Task.create(name:"un Task")
		
		assertFalse task.isOverdue()
	}
	
	void test_Task_created_is_not_completed() {
		def task = Task.create(name:"Un task qualsiasi")
		
		assertFalse task.completed
	}
	
	void test_Task_completed_is_completed() {
		def task = Task.create(name:"UN task da completare")
		
		task.complete()
		
		assert task.completed
	}
	
	void test_Task_completion_date_is_today() {
		def task = Task.create(name: "Un task da completare")
		
		task.complete()
		
		assertTrue task.completionDate <= new DateTime()
		assertTrue task.completionDate > new DateTime().minusDays(1)
	}

  void test_Task_with_future_due_date_is_not_overdue() {
	  def task = new Task(
			name: "Un task comodo comodo",
			dueDate: new DateTime().plusDays(7)
		)
		
		assertFalse task.overdue
  }
  
  void test_Task_with_past_due_date_is_overdue() {
		def task = Task.create(
			name:"Un task per l'altro ieri",
			dueDate: new DateTime().minusDays(2))
		
		assertTrue task.overdue
  }

  void test_Reopened_task_is_not_completed() {
	  def task = Task.create(name:"Un task travagliato")
	
	  task.complete()
	  task.reopen()
	
	  assertFalse task.completed
  }

  void test_Reopened_task_has_no_completion_date() {
	  def task = Task.create(name:"un task qualsiasi")
	
	  task.complete()
	  task.reopen()
	
	  assertNull task.completionDate
  }
}
