package com.journaldev.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("MySessionAttributeListener - attributeAdded " + event.getName());

	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("MySessionAttributeListener - attributeRemoved " + event.getName());

	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("MySessionAttributeListener - attributeReplaced " + event.getName());

	}

}
