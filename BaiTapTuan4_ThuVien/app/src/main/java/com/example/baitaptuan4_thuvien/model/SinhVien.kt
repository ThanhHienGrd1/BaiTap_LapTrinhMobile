package com.example.baitaptuan4_thuvien.model

data class SinhVien(
    val id : Int,
    val ten :String,
    val dsSachMuon: MutableList<Sach> = mutableListOf()
)