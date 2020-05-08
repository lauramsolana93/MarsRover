package com.kotlin.seat.seatmars.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kotlin.seat.seatmars.R

class BottomSheet : BottomSheetDialogFragment() {

    private lateinit var message: TextView
    private lateinit var imageView: ImageView
    private lateinit var close: ImageView
    private var messageText = ""
    var isClosed: ((Boolean) -> Unit)? = null

    companion object {
        fun newInstance() : BottomSheet {
            val fragment = BottomSheet()
            return fragment
        }
    }

    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        bindView(view)
        setListeners()
        setMessage()
        return view
    }

    private fun bindView(view: View){
        message = view.findViewById(R.id.message)
        imageView = view.findViewById(R.id.rover_image)
        close = view.findViewById(R.id.close_image)
    }

    private fun setListeners() {
        close.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun setMessage() {
        message.text = messageText
    }



    fun show(manager: FragmentManager, tag: String, message: String) {
        super.show(manager, tag)
        this.messageText = message
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        isClosed?.invoke(true)
    }
}