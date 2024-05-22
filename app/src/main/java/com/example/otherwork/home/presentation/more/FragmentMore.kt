package com.example.otherwork.home.presentation.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.otherwork.R
import com.example.otherwork.databinding.FragmentMoreBinding

class FragmentMore : Fragment() {

    lateinit var binding : FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListenerOnView()
    }

    private fun addListenerOnView() {
        binding.layoutChangeLanguage.setOnClickListener {
            showLanguageDialog()
        }
    }

    private fun showLanguageDialog() {
        val languages = arrayOf(
            getString(R.string.english),
            getString(R.string.arabic),
            getString(R.string.cancel)
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, languages)

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.change_language))
            .setAdapter(adapter) { dialog, which ->
                val selectedLanguage = languages[which]
                if (selectedLanguage == getString(R.string.cancel)) {
                    dialog.dismiss()
                } else {
                    //set local language
                    dialog.dismiss()
                }
            }
            .setCancelable(false)
            .show()
    }

}