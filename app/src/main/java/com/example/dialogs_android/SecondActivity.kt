package com.example.dialogs_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dialogs_android.adapter.UserAdapter
import com.example.dialogs_android.databinding.ActivitySecondBinding
import com.example.dialogs_android.model.User
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    lateinit var list: ArrayList<User>
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        setData()
        adapter = UserAdapter(list, object : UserAdapter.OnClick {
            override fun onItemLongClickListener(user: User, position: Int) {
                list.remove(user)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, list.size)

                val snackbar = Snackbar.make(
                    binding.rv, "remove chats",
                    Snackbar.LENGTH_LONG
                ).setAction("Undo") {
                    list.add(position, user)
                    adapter.notifyItemInserted(position)
                    adapter.notifyItemRangeChanged(position, list.size)
                }
                snackbar.show()
            }
        })
        binding.rv.adapter = adapter
    }

    private fun setData() {
        for (i in 0 until 20) {
            list.add(User("Name $i", "PhoneNumber $i"))
        }
    }

}