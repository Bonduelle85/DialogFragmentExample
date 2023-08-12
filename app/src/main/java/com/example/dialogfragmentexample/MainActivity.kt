package com.example.dialogfragmentexample

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentResultListener
import com.example.dialogfragmentexample.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var volume by Delegates.notNull<Int>()
    private var color by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Simple Dialog
        binding.buttonSimpleDialog.setOnClickListener { showSimpleDialogFragment() }
        setupSimpleDialogFragmentListener()

        // Custom SingleChoice Dialog
        binding.buttonSingleChoiceDialog.setOnClickListener { showSingleChoiceAlertDialog() }
        setupSingleChoiceDialogFragmentListener()

        // Single Choice Dialog With Confirmation
        binding.buttonSingleChoiceConfirm.setOnClickListener { showSingleChoiceDialogConfirm() }
        setupSingleChoiceDialogConfirm()

        // Multiple Choice Dialog
        binding.buttonMultipleChoiceDialog.setOnClickListener { showMultipleChoiceDialogFragment() }
        setupMultipleChoiceDialogFragmentListener()

        // Multiple Choice Dialog with Confirmation
        binding.buttonMultiChoiceDialogConfirm.setOnClickListener { showMultipleChoiceConfirmationDialogFragment() }
        setupMultipleChoiceConfirmationDialogFragmentListener()

        // Custom Alert Dialog Bar
        binding.buttonCustomDialog.setOnClickListener { showCustomDialogFragment() }
        setupCustomDialogFragmentListener()

        // Custom Dialog  Single Choice
        binding.buttonCustomSingleChoiceAlertDialog.setOnClickListener { showCustomSingleChoiceDialog() }
        setupCustomSingleChoiceDialogFragmentListener()

        // restoring state updating UI
        volume = savedInstanceState?.getInt(KEY_VOLUME) ?: 5
        color = savedInstanceState?.getInt(KEY_COLOR) ?: Color.RED
        binding.numberView.text = volume.toString()
        binding.colorView.setBackgroundColor(color)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_VOLUME, volume)
        outState.putInt(KEY_COLOR, color)
    }


    private fun showSimpleDialogFragment() {
        val dialogFragment = SimpleDialogFragment()
        dialogFragment.show(supportFragmentManager, SimpleDialogFragment.TAG)
    }
    private fun setupSimpleDialogFragmentListener() {
        supportFragmentManager.setFragmentResultListener(SimpleDialogFragment.REQUEST_KEY, this, FragmentResultListener { _, result ->
            val which = result.getInt(SimpleDialogFragment.KEY_RESPONSE)
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(this, "Ok clicked", Toast.LENGTH_SHORT).show()
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show()
                DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(this, "Ignore clicked", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun showSingleChoiceAlertDialog() {
        SingleChoiceDialogFragment.show(supportFragmentManager, volume)
    }
    private fun setupSingleChoiceDialogFragmentListener() {
        SingleChoiceDialogFragment.setupListener(supportFragmentManager, this) {
            this.volume = it
            binding.numberView.text = volume.toString()
            binding.colorView.setBackgroundColor(color)
        }
    }


    private fun showSingleChoiceDialogConfirm(){
        SingleChoiceDialogFragmentWithConfirmation.show(supportFragmentManager, volume)
    }
    private fun setupSingleChoiceDialogConfirm(){
        SingleChoiceDialogFragmentWithConfirmation.setupListener(supportFragmentManager, this) {
            this.volume = it
            binding.numberView.text = volume.toString()
            binding.colorView.setBackgroundColor(color)
        }
    }


    private fun showMultipleChoiceDialogFragment(){
        MultipleChoiceDialogFragment.show(supportFragmentManager, this.color)
    }
    private fun setupMultipleChoiceDialogFragmentListener(){
        MultipleChoiceDialogFragment.setupListener(supportFragmentManager, this) {
            this.color = it
            binding.numberView.text = volume.toString()
            binding.colorView.setBackgroundColor(color)
        }
    }


    private fun showMultipleChoiceConfirmationDialogFragment() {
        MultipleChoiceConfirmationDialogFragment.show(supportFragmentManager, this.color)
    }
    private fun setupMultipleChoiceConfirmationDialogFragmentListener() {
        MultipleChoiceConfirmationDialogFragment.setupListener(supportFragmentManager, this) {
            this.color = it
            binding.numberView.text = volume.toString()
            binding.colorView.setBackgroundColor(color)
        }
    }


    private fun showCustomDialogFragment(){
        CustomDialogFragment.show(supportFragmentManager, volume)
    }
    private fun setupCustomDialogFragmentListener(){
        CustomDialogFragment.setupListener(supportFragmentManager, this) {
            this.volume = it
            binding.numberView.text = volume.toString()
        }
    }


    private fun showCustomSingleChoiceDialog(){
        CustomSingleChoiceDialogFragment.show(supportFragmentManager, volume)
    }
    private fun setupCustomSingleChoiceDialogFragmentListener(){
        CustomSingleChoiceDialogFragment.setupListener(supportFragmentManager, this) {
            this.volume = it
            binding.numberView.text = volume.toString()
        }
    }

    companion object {
        @JvmStatic private val TAG = MainActivity::class.java.simpleName
        @JvmStatic private val KEY_VOLUME = "KEY_VOLUME"
        @JvmStatic private val KEY_COLOR = "KEY_COLOR"
    }
}