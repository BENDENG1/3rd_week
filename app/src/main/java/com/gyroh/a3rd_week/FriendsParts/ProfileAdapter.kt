package com.gyroh.a3rd_week.FriendsParts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gyroh.a3rd_week.R
import com.gyroh.a3rd_week.databinding.ItemProfileBinding


class ProfileAdapter(var profileList : ArrayList<Profile>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {
    //class PofileAdapter로 생성자로들어갈게 val profileList라는게 ArrayList의 타입 Profile을 불러와서 리스트와 시킴
    // : -> 상속개념 상속을 받아오는데 RecyclerView.Adapter속성을 걸어오고 <ProfileAdapter.별도의 이름을 짓는 뷰홀더 클래스를 만듬>()



    // onCreateViewHolder -> 액티비티가 xml에 연결하는하는 것처럼 listitem의 화면을 붙히는 작업
    //context -> 액티비티에서 담고있는 모든 정보
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CustomViewHolder(binding).also { holder ->
            //여기 이어서 구현해야함
            holder.binding.root.setOnClickListener {
                Log.d("디버깅","이름 : ${profileList[holder.adapterPosition].name},\n상테 메세지 : ${profileList[holder.adapterPosition].message}")
            }
        }
    }

    //실질적으로 oncreateviewholder로 만들어진것을 연결해주는 역할
    //뷰에 대해서 안정적으로 모든 데이터를 매치시켜주는 곳
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(profileList[position])
    }

    override fun getItemCount(): Int { //리스트에 대한 총 갯수
        return profileList.size
    }

    // viewHolder어탭터의 외부 혹은 내부에
    // CustomViewHolder -> 음료수 처럼 잡아주는 홀더처럼 view를 잡아주는 개념
    // 생성자를 만드는데 View타입을 가진 itemView를 만드는데
    //  : -> 상속  / 미리 만들어진 RecyclerView의 뷰홀더를 가져온다. 안에는 itemView같이 활용
    class CustomViewHolder(val binding : ItemProfileBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(profile: Profile) {
            binding.imageViewProfile.setImageResource(profile.picture)
            binding.textprofilename.text = profile.name
            binding.textprofilemessage.text = profile.message
        }
    }

    fun addItem(profile: Profile){
        profileList.add(profile)
        notifyDataSetChanged()
    }

    fun deleteItme(position: Int){
        if(position > 0){
            profileList.removeAt(position)
            notifyDataSetChanged()
        }
    }

}