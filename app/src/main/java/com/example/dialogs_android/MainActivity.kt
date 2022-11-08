package com.example.dialogs_android

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialogs_android.databinding.ActivityMainBinding
import com.example.dialogs_android.databinding.ItemDialogBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.timeDialog.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay $minute", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], false
            )
            timePickerDialog.show()
        }
        binding.dateDialog.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val calendar = Calendar.getInstance()
                val datePickerDialog =
                    DatePickerDialog(
                        this,
                        object : DatePickerDialog.OnDateSetListener {
                            override fun onDateSet(
                                view: DatePicker?,
                                year: Int,
                                month: Int,
                                dayOfMonth: Int
                            ) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "$dayOfMonth $month $year",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH]
                    )
                datePickerDialog.show()
            }
        }
        binding.fragmentDialog.setOnClickListener {
            val fragmentDialog = MyDialogFragment()
            fragmentDialog.show(supportFragmentManager, "dialog")
        }
        binding.btnCustom.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this).create()
            val item = LayoutInflater.from(this).inflate(R.layout.item_dialog, binding.root, false)
            alertDialog.setView(item)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val bind = ItemDialogBinding.bind(item)
            bind.btn.setOnClickListener {
                val name = bind.etName.text.toString()
                val number = bind.etNumber.text.toString()

                Toast.makeText(this, "$name $number", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
        binding.btn.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("number picker")
//            alertDialog.setMessage("Alert dialog")
            // custom alert dialog
            alertDialog.setNegativeButton("No") { dialog, which ->
                Toast.makeText(this@MainActivity, "Negativ", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setPositiveButton("yes") { dialog, which ->
                Toast.makeText(this@MainActivity, "YES", Toast.LENGTH_SHORT).show()
            }
            val listProgramming = arrayOf("Android", "Flutter", "IOS")
            alertDialog.setSingleChoiceItems(
                listProgramming, 0
            ) { dialog, which ->
                Toast.makeText(
                    this@MainActivity,
                    listProgramming[which],
                    Toast.LENGTH_SHORT
                ).show()
            }
//            alertDialog.setMultiChoiceItems(listProgramming,
//                BooleanArray(listProgramming.size),
//                object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        Toast.makeText(
//                            this@MainActivity,
//                           "${listProgramming[which]} $isChecked",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                })
//            alertDialog.setNeutralButton("neturall") { dialog, which ->
//                Toast.makeText(this, "Natural", Toast.LENGTH_SHORT).show()
//            }

            alertDialog.show()
        }
    }
}