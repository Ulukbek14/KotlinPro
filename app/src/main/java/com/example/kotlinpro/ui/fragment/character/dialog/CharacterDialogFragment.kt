package com.example.kotlinpro.ui.fragment.character.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotlinpro.databinding.FragmentCharacterDialogBinding

class CharacterDialogFragment : DialogFragment() {

    private var _binding: FragmentCharacterDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentCharacterDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupArgsDialog()
        return builder
    }

    private fun setupArgsDialog() = with(binding) {
        Glide.with(itemCharacterDialogIv)
            .load(CharacterDialogFragmentArgs.fromBundle(requireArguments()).image)
            .into(itemCharacterDialogIv)

    }
}