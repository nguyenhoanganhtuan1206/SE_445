package com.example.demo.controller;

import com.example.demo.file.ReadFile;
import com.example.demo.file.WriteFile;
import com.example.demo.models.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class MainController {

    private static final String KHACH_HANG_PATH = "D:\\TichHop\\SE_445\\src\\main\\java\\com\\example\\demo\\data\\KhachHang.tsv";
    private static final String TIEM_CAM_DO_PATH = "D:\\TichHop\\SE_445\\src\\main\\java\\com\\example\\demo\\data\\TiemCamDo.tsv";
    private static final String DANH_MUC_PATH = "D:\\TichHop\\SE_445\\src\\main\\java\\com\\example\\demo\\data\\DanhMuc.tsv";
    private static final String TINH_THANH_PATH = "D:\\TichHop\\SE_445\\src\\main\\java\\com\\example\\demo\\data\\TinhThanh.tsv";
    private static final String HOA_DON_PATH = "D:\\TichHop\\SE_445\\src\\main\\java\\com\\example\\demo\\data\\HoaDon.tsv";

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private TiemCamDoService tiemCamDoService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private TinhThanhService tinhThanhService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    /* File service */
    WriteFile writeFile = new WriteFile();

    @Autowired
    ReadFile readFile = new ReadFile();

    /* Maria to tsv */
    @RequestMapping(value = "maria-to-tsv", method = RequestMethod.GET)
    public ResponseEntity<?> mariaToTsv() {
        /* Danh Muc */
        List<Object> danhMucs = (List<Object>) danhMucService.findAll();
        writeFile.writeFile(DANH_MUC_PATH, danhMucs, false);
        /* Tiem cam do */
        List<Object> tiemCamDos = (List<Object>) tiemCamDoService.findAll();
        writeFile.writeFile(TIEM_CAM_DO_PATH, tiemCamDos, false);

        /* Tinh thanh */
        List<Object> tinhThanhs = (List<Object>) tinhThanhService.findAll();
        writeFile.writeFile(TINH_THANH_PATH, tinhThanhs, false);

        /* Khach hang */
        List<Object> khachHangs = (List<Object>) khachHangService.findAll();
        writeFile.writeFile(KHACH_HANG_PATH, khachHangs, false);

        /* Hoa don */
        List<Object> hoaDons = (List<Object>) hoaDonService.findAll();
        writeFile.writeFile(HOA_DON_PATH, hoaDons, false);

        /* Chi tiet hoa don */
        List<ChiTietHoaDon> chiTietHoaDons = (List<Object>) hoaDonService.findAll();

        return new ResponseEntity(HttpStatus.OK);
    }

    /* Maria to tsv */
    @RequestMapping(value = "tsv-to-maria", method = RequestMethod.GET)
    public ResponseEntity<?> tsvToMaria() {
        /* Get list in file tsv */
        List<Object> khachHangTsv = (List<Object>) readFile.readFile(KHACH_HANG_PATH, 1);
        List<Object> tiemCamDosTsv = (List<Object>) readFile.readFile(TIEM_CAM_DO_PATH, 2);
        List<Object> hoaDonTsv = (List<Object>) readFile.readFile(HOA_DON_PATH, 3);
        List<Object> danhMucTsv = (List<Object>) readFile.readFile(DANH_MUC_PATH, 5);
        List<Object> tinhThanhTsv = (List<Object>) readFile.readFile(TINH_THANH_PATH, 6);

        /* Get list from maria */
        List<Object> tiemCamDosDb = (List<Object>) tiemCamDoService.findAll();
        List<Object> danhMucDb = (List<Object>) danhMucService.findAll();
        List<Object> tinhThanhDb = (List<Object>) tinhThanhService.findAll();
        List<Object> khachHangDb = (List<Object>) khachHangService.findAll();
        List<Object> hoaDonDb = (List<Object>) hoaDonService.findAll();

        /* Tiem Cam Do */
        for (Object o : tiemCamDosTsv) {
            if (!tiemCamDosDb.isEmpty()) {
                if (!tiemCamDosDb.contains(o)) {
                    tiemCamDoService.save((TiemCamDo) o);
                }
            } else {
                tiemCamDoService.save((TiemCamDo) o);
            }
        }

        /* Danh Muc */
        for (Object o : danhMucTsv) {
            if (!danhMucDb.isEmpty()) {
                if (!danhMucDb.contains(o)) {
                    danhMucService.save((DanhMuc) o);
                }
            } else {
                danhMucService.save((DanhMuc) o);
            }
        }

        /* Tinh Thanh */
        for (Object o : tinhThanhTsv) {
            if (!tinhThanhDb.isEmpty()) {
                if (!tinhThanhDb.contains(o)) {
                    tinhThanhService.save((TinhThanh) o);
                }
            } else {
                tinhThanhService.save((TinhThanh) o);
            }
        }

        /* Khach Hang */
        for (Object o : khachHangTsv) {
            if (!khachHangDb.isEmpty()) {
                if (!khachHangDb.contains(o)) {
                    khachHangService.save((KhachHang) o);
                }
            } else {
                khachHangService.save((KhachHang) o);
            }
        }
        /* Hoa Don */
        for (Object o : hoaDonTsv) {
            if (!hoaDonDb.isEmpty()) {
                if (!hoaDonDb.contains(o)) {
                    hoaDonService.save((HoaDon) o);
                }
            } else {
                hoaDonService.save((HoaDon) o);
            }
        }

        System.out.println(khachHangService.findById("KH001"));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
