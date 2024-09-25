package com.raccoon.clinic_management.services_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raccoon.clinic_management.entity.Contact;
import com.raccoon.clinic_management.repository.ContactRepository;
import com.raccoon.clinic_management.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactRepository contactRepository;
	
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public Contact findById(int id) {
		Optional<Contact> contact = contactRepository.findById(id);

		if (contact.isEmpty()) {
			throw new RuntimeException("contact information not found");
		}

		return contact.get();
	}

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}
	
	public Contact updateContact(Contact contact) {
		Optional<Contact> dbcontact = contactRepository.findById(contact.getId());

		if (dbcontact.isEmpty()) {
			throw new RuntimeException("contact information not found");
		}

		Contact existingContact = dbcontact.get();
		existingContact.setEmail(contact.getEmail());
        existingContact.setPhone(contact.getPhone());
        existingContact.setBirthDate(contact.getBirthDate());

        return contactRepository.save(existingContact);
	}

	public void deleteContact(int id) {
		Optional<Contact> dbcontact = contactRepository.findById(id);

		if (dbcontact.isEmpty()) {
			throw new RuntimeException("contact information not found");
		}

		contactRepository.delete(dbcontact.get());
	}
}
