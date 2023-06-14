package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.databinding.StudentListItemBinding
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), StudentListInterface {
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        holder.view.txtID.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name
//        holder.view.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
//        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
//        imageView.loadImage(studentList[position].photoUrl, progressBar)

        holder.view.student = studentList[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }


}