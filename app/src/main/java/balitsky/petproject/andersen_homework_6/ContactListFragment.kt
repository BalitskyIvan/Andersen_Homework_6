package balitsky.petproject.andersen_homework_6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import balitsky.petproject.andersen_homework_6.MainActivity.Companion.CONTACTS_EXTRA
import java.lang.RuntimeException

class ContactListFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private var contactList: ArrayList<Contact>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactList = requireArguments().getParcelableArrayList(CONTACTS_EXTRA)

        if (contactList == null)
            throw RuntimeException("Fragment lifecycle error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        recyclerView?.adapter = RecyclerAdapter(contactList!!)
    }

    companion object {
        fun newInstance(contactList: ArrayList<Contact>): ContactListFragment {
            val contactListFragment = ContactListFragment()
            contactListFragment.arguments = Bundle().also {
                it.putParcelableArrayList(CONTACTS_EXTRA, contactList)
            }
            return contactListFragment
        }
    }
}