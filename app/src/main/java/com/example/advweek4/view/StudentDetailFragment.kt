package com.example.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentDetailBinding
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*


class StudentDetailFragment : Fragment(), StudentDetailInterace {

    private lateinit var viewModel : DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding> (inflater, R.layout.fragment_student_detail, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            dataBinding.listener =this
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            viewModel.getData(studentID)
            observeViewModel()
//            imageView2.loadImage(viewModel.studentLD.value?.photoUrl, progressBar)
//            val txtID = view.findViewById<TextView>(R.id.txtID)
//            val txtName = view.findViewById<TextView>(R.id.txtName)
//            val txtBod = view.findViewById<TextView>(R.id.txtBod)
//            val txtPhone = view.findViewById<TextView>(R.id.txtPhone)
//
//            txtID.text = viewModel.studentLD.value?.id
//            txtName.text = viewModel.studentLD.value?.name
//            txtBod.text = viewModel.studentLD.value?.bod
//            txtPhone.text = viewModel.studentLD.value?.phone
        }
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
        })
    }

    override fun onButtonUpdateClick(v: View) {
        viewModel.studentLD.value = dataBinding.student
        val action = StudentDetailFragmentDirections.actionStudentList()
        Navigation.findNavController(v).navigate(action)
    }


}