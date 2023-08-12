package com.example.dialogfragmentexample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener{_, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to which))
        }
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("Simple Dialog")
            .setMessage("This is simple dialog fragment")
            .setPositiveButton("Ok", listener)
            .setNegativeButton("Cancel", listener)
            .setNeutralButton("Ignore", listener)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG, "!!!Dialog dismissed!!!")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Toast.makeText(requireContext(), "dialog cancelled", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "!!!Dialog cancelled!!!")
    }

    companion object {
        val TAG = SimpleDialogFragment::class.java.simpleName
        val REQUEST_KEY = "$TAG:defaultRequestKey"
        val KEY_RESPONSE = "RESPONSE"
    }
}