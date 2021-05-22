package balitsky.petproject.andersen_homework_6

interface ClickedItemHandler {
    fun openContactDetails(contact: Contact)
    fun updateContact(contact: Contact)
}