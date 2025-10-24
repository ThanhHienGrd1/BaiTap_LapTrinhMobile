package com.example.baitaptuan4_thuvien.viewmodel

import androidx.lifecycle.ViewModel
import com.example.baitaptuan4_thuvien.model.QuanLySach
import com.example.baitaptuan4_thuvien.model.QuanLySinhVien
import com.example.baitaptuan4_thuvien.model.Sach
import com.example.baitaptuan4_thuvien.model.SinhVien
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val quanLySach = QuanLySach()
    private val quanLySinhVien = QuanLySinhVien()
    private val _danhSachSach = MutableStateFlow<List<Sach>>(emptyList())
    val danhSachSach = _danhSachSach.asStateFlow()

    private val _danhSachSinhVien = MutableStateFlow<List<SinhVien>>(emptyList())
    val danhSachSinhVien = _danhSachSinhVien.asStateFlow()

    private val _sinhVienHienTai = MutableStateFlow<SinhVien?>(null)
    val SinhVienHienTai = _sinhVienHienTai.asStateFlow()

    init {

        quanLySach.them(Sach(1, "Lập trình Kotlin"))
        quanLySach.them(Sach(2, "Cấu trúc dữ liệu"))
        quanLySinhVien.them(SinhVien(1, "Nguyen Van A"))
        quanLySinhVien.them(SinhVien(2, "Nguyen Van C"))


        _danhSachSach.value = quanLySach.getDanhSachSach()
        _danhSachSinhVien.value = quanLySinhVien.getDanhSachSinhVien()
    }

    fun themSach(sach: Sach) {
        quanLySach.them(sach)
        _danhSachSach.value = quanLySach.getDanhSachSach()
    }

    fun xoaSach(sach: Sach) {
        quanLySach.xoa(sach)
        _danhSachSach.value = quanLySach.getDanhSachSach()
    }

    fun themSinhVien(sv: SinhVien) {
        quanLySinhVien.them(sv)
        _danhSachSinhVien.value = quanLySinhVien.getDanhSachSinhVien()
    }


    fun xoaSinhVien(sv: SinhVien) {
        quanLySinhVien.xoa(sv)
        _danhSachSinhVien.value = quanLySinhVien.getDanhSachSinhVien()
    }


    fun chonSinhVienTheoTen(ten: String) {
        _sinhVienHienTai.value = quanLySinhVien.timSinhVienTheoTen(ten)
    }

    fun muonSach(sach: Sach) {
        _sinhVienHienTai.value?.let { sv ->
            if (!sv.dsSachMuon.any { it.id == sach.id }) {
                sv.dsSachMuon.add(sach)
                _sinhVienHienTai.value = sv
            }
        }
    }


    fun traSach(sach: Sach) {
        _sinhVienHienTai.value?.let { sv ->
            if (sv.dsSachMuon.removeIf { it.id == sach.id }) {
                _sinhVienHienTai.value = sv
            }
        }
    }


}