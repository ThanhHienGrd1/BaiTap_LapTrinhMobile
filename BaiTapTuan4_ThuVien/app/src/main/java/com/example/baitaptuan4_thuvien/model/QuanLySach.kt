package com.example.baitaptuan4_thuvien.model

import androidx.compose.runtime.mutableStateListOf

class QuanLySach : QuanLy<Sach>{
    private val _danhSachSach = mutableStateListOf<Sach>()

    fun getDanhSachSach() = _danhSachSach

    override fun them(item: Sach){
        _danhSachSach.add(item)
    }

    override fun xoa (item: Sach){
        _danhSachSach.remove(item)
    }
}
