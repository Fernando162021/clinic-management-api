package com.raccoon.clinic_management.services;

import com.raccoon.clinic_management.entity.Contact;

public interface ContactService {
	public Contact saveContact(Contact contact);
	public Contact findById(int id);
	public Contact updateContact(Contact contact);
	public void deleteContact(int id);
}
