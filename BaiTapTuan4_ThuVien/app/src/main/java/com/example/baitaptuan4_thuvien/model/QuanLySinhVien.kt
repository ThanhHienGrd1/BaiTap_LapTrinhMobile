package com.example.baitaptuan4_thuvien.model

import androidx.compose.runtime.mutableStateListOf

class QuanLySinhVien : QuanLy<SinhVien> {
   private val _danhSachSinhVien = mutableStateListOf<SinhVien>()


    fun getDanhSachSinhVien() = _danhSachSinhVien

    override fun them (item : SinhVien){
        _danhSachSinhVien.add(item)
    }

    override fun xoa (item: SinhVien){
        _danhSachSinhVien.remove(item)
    }
    fun timSinhVienTheoTen(ten: String): SinhVien? {
        return _danhSachSinhVien.find { it.ten.equals(ten, ignoreCase = true) }
    }

}