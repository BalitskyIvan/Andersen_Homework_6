package balitsky.petproject.andersen_homework_6

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ClickedItemHandler {

    private var frameLayout: FrameLayout? = null
    private var contactListFragment: ContactListFragment? = null
    private var contactsList: ArrayList<Contact>? = null
    private val CONTACTS_SIZE = 100
    private var listNames: List<String>? = null
    private var listSurnames: List<String>? = null

    companion object {
        const val CONTACTS_ITEM_EXTRA = "ITEM_EXTRA"
        const val CONTACTS_EXTRA = "CONTACTS_EXTRA"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout = findViewById(R.id.fragmentContainerView)
        listNames = getNameList()
        listSurnames = getSurnameList()

        if (savedInstanceState == null) {
            contactsList = initContactList()

            supportFragmentManager.apply {
                val transaction = beginTransaction()
                contactListFragment = ContactListFragment.newInstance(contactsList!!)
                transaction.replace(R.id.fragmentContainerView, contactListFragment!!)
                transaction.commit()
            }
        }
    }

    override fun openContactDetails(contact: Contact) {
        supportFragmentManager.apply {
            val transaction = beginTransaction()
            val contactDetailFragment = ContactDetailsFragment.newInstance(contact)
            transaction.replace(R.id.fragmentContainerView, contactDetailFragment)
            transaction.addToBackStack(null);
            transaction.commit()
        }
    }



    override fun updateContact(contact: Contact) {
        contactsList!![contact.id].name = contact.name
        contactsList!![contact.id].surname = contact.surname
        contactsList!![contact.id].phone = contact.phone
    }

    private fun getNameList(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }

    private fun getSurnameList(): List<String> {
        return this.resources.getStringArray(R.array.surnames).toList()
    }

    fun initContactList(): ArrayList<Contact> {
        val contactList = ArrayList<Contact>()
        val random = Random(10)
        for (i in 0..CONTACTS_SIZE)
            contactList.add(Contact(i, random.nextInt(CONTACTS_SIZE) + 1, listNames?.get(random.nextInt(11)), listSurnames?.get(random.nextInt(11)), "+7" + random.nextLong(9999999999).toString()))
        return contactList
    }
}