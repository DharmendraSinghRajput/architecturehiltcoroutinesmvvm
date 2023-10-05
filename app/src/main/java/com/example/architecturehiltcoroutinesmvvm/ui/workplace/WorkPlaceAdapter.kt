package com.example.architecturehiltcoroutinesmvvm.ui.workplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturehiltcoroutinesmvvm.R
import com.example.architecturehiltcoroutinesmvvm.databinding.ItemAddWorkplaceBinding
import com.example.architecturehiltcoroutinesmvvm.utils.GeneralFunctions

class WorkPlaceAdapter(val onClick: (position: Int, viewId: Int) -> Unit) : RecyclerView.Adapter<WorkPlaceAdapter.WorkViewHolder>() {

    private var allWorkPlaceDataResponse = listOf<AllWorkPlaceDataResponse.Data>()

    inner class WorkViewHolder(val mBinding: ItemAddWorkplaceBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder = WorkViewHolder(ItemAddWorkplaceBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: WorkViewHolder, position: Int) {
        holder.mBinding.apply {
            allWorkPlaceDataResponse[position].apply {
               // GeneralFunctions.loadImage(root.context, teams.vTeamLogo, ivTeam)
                val fromDate = allWorkPlaceDataResponse[position].vStartDay + "-" + allWorkPlaceDataResponse[position].vStartMonth + "-" + allWorkPlaceDataResponse[position].vStartYear
                val endDate = allWorkPlaceDataResponse[position].vEndDay.toString() + "-" + allWorkPlaceDataResponse[position].vEndMonth + "-" + allWorkPlaceDataResponse[position].vEndYear

                tvEducationTitle.text = allWorkPlaceDataResponse[position].vPosition
                tvCompanyName.text = allWorkPlaceDataResponse[position].vCompany
               // ivEducation.setImageResource(R.drawable.ic_about_workplace)
               // tvUniversity.text = GeneralFunctions.changeDateFormatStr(fromDate, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)
                tvDescription.text = allWorkPlaceDataResponse[position].tDescription
                tvCity.text = allWorkPlaceDataResponse[0].vCity
                /*if (allWorkPlaceDataResponse[position].eCurrentlyWorking == "Yes") {
                    tvToDate.text = " To Present"
                } else {
                    tvToDate.text = " To ${GeneralFunctions.changeDateFormatStr(endDate, GeneralFunctions.dateFormatDDMMYYYY, GeneralFunctions.dateFormatMMDDYYYYSlash)}"
                }*/
                imgEdit.setOnClickListener {
                    onClick(position, R.id.imgEdit)
                }

                imgDelete.setOnClickListener {
                    onClick(position, R.id.imgDelete)

                }
            }
        }
    }

    override fun getItemCount(): Int = allWorkPlaceDataResponse.size
    fun setData(chatTeamList: List<AllWorkPlaceDataResponse.Data>) {
        this.allWorkPlaceDataResponse = chatTeamList
        notifyDataSetChanged()
    }


}