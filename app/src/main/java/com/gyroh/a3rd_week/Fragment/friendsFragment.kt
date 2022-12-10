package com.gyroh.a3rd_week.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gyroh.a3rd_week.FriendsParts.FAddFriends
import com.gyroh.a3rd_week.FriendsParts.Profile
import com.gyroh.a3rd_week.FriendsParts.ProfileAdapter
import com.gyroh.a3rd_week.MainActivity
import com.gyroh.a3rd_week.R
import com.gyroh.a3rd_week.databinding.FragmentFriendsBinding

class friendsFragment : Fragment() {

    val binding by lazy { FragmentFriendsBinding.inflate(layoutInflater) }
    var userList = arrayListOf<Profile>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.rlReviseFriends.visibility = View.GONE

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //테스트용
        userList.add(Profile(R.drawable.ic_profile1, "노균욱", "안녕하세요 노균욱입니다~~~~"))
        userList.add(Profile(R.drawable.ic_profile2, "근육", "안드로이드는 어려워"))
        userList.add(Profile(R.drawable.ic_profile_3, "균욱", "오늘도 귀찮은 하루.."))
        for (i in 1..50) { userList.add(Profile(R.drawable.ic_profile1, "노균욱${i}", "노균욱 클론 ${i}입니다.")) }



        binding.ibAddFriend.setOnClickListener {
            val faddFriends = FAddFriends()
            //faddFriends.show(childFragmentManager,faddFriends.tag)
            parentFragmentManager.beginTransaction().replace(R.id.fl_friends, faddFriends).commit()
        }

        // recyclerViewProfile.layoutManager를 연결 recyclerView필수
        // LinearLayoutManager로 import
        binding.recyclerViewProfile.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewProfile.setHasFixedSize(true) // 고정된 크기의 아이템ui사용 -> recyclerview에 대한 성능개선방안

        val adapter = ProfileAdapter(userList)
        //어댑터 연결
        binding.recyclerViewProfile.adapter = adapter


        binding.button5.setOnClickListener {
            adapter.addItem(Profile(R.drawable.ic_profile1,"가나다","가나다라마바사"))

        }

        //testadd()
    }

    fun testadd(){
        val profileAdapter = ProfileAdapter(userList)

        binding.recyclerViewProfile.setOnClickListener{
            profileAdapter.addItem(Profile(R.drawable.ic_profile1,"가나다","가나다라마바사"))
        }

        binding.button5.setOnClickListener {

            Log.d("checking","1")
            binding.rlReviseFriends.visibility = View.VISIBLE

            //무슨 둘다 안되냐ㅡㅡ
            profileAdapter.profileList.add(Profile(R.drawable.ic_profile1,"가나다","가나다라마바사"))

            binding.btnRemove.setOnClickListener {
                binding.rlReviseFriends.visibility = View.GONE
            }
        }

    }


    //음... 다시 생각해보자
    override fun onResume() {
        super.onResume()
        updateData(ProfileAdapter(userList))

    }

    private fun updateData(profileAdapter: ProfileAdapter) {
        //여기서 bundle로 받아온 arguement가 자꾸 null인데 왜 그럴까?
        var flagCheck = arguments?.getInt("flag")
        var receivename: String = arguments?.getString("name").toString()
        var receivemessage: String = arguments?.getString("message").toString()

        Log.d("체킹", "$flagCheck $receivename $receivemessage")
        if (flagCheck == 1) {
            profileAdapter.addItem(Profile(R.drawable.ic_add_profile,receivename,receivemessage))
        }

    }
}