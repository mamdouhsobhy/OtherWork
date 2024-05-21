package com.example.otherwork.home.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.otherwork.R
import com.example.otherwork.constant.Nav
import com.example.otherwork.databinding.FragmentHomeBinding
import com.example.otherwork.extention.setViewBackground
import com.example.otherwork.home.presentation.home.adapter.AdapterGasoline
import com.example.otherwork.home.presentation.home.adapter.AdapterPayment
import com.example.otherwork.home.presentation.home.model.GasolineModel


class FragmentHome : Fragment() {

    lateinit var binding :FragmentHomeBinding

    var type = Nav.CheckInvoice.amount

    private val adapterGasoline by lazy { AdapterGasoline(itemSelected = {

    }) }

    private val adapterPayment by lazy { AdapterPayment(itemSelected = {

    }) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListenerOnView()
        setUpGasoline()
        setUpPayment()

    }

    private fun setUpGasoline() {
        val list = ArrayList<GasolineModel>()
        list.add(GasolineModel("95","#429F46",true))
        list.add(GasolineModel("91","#F44336"))
        list.add(GasolineModel("95","#3F51B5"))
        list.add(GasolineModel("Diesel","#FF5722"))
        adapterGasoline.submitList(list)
        binding.rvGasolineKind.adapter = adapterGasoline
    }

    private fun setUpPayment() {
        val list = ArrayList<GasolineModel>()
        list.add(GasolineModel("Mada", selected = true))
        list.add(GasolineModel("Master"))
        list.add(GasolineModel("Visa"))
        list.add(GasolineModel("Cash"))
        adapterPayment.submitList(list)
        binding.rvPaymentMethod.adapter = adapterPayment
    }

    private fun addListenerOnView() {

        binding.checkToggle.setOnClickListener {
            handleCheckToogleStatus()
        }

    }

    private fun handleCheckToogleStatus() {
        if(type == Nav.CheckInvoice.amount){
            type = Nav.CheckInvoice.liter
            binding.checkAmount.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.checkLiter.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green_4))
            binding.edAmount.setViewBackground(R.drawable.drawable_corner_offwhite)
            binding.edLiter.setViewBackground(R.drawable.drawable_corner_edittext)
        }else{
            type = Nav.CheckInvoice.amount
            binding.checkAmount.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green_4))
            binding.checkLiter.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.edAmount.setViewBackground(R.drawable.drawable_corner_edittext)
            binding.edLiter.setViewBackground(R.drawable.drawable_corner_offwhite)
        }
    }

}