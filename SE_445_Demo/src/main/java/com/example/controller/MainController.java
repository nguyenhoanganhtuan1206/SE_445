package com.example.controller;

import com.example.file.ReadAndWriteFile;
import com.example.models.ChiTietHoaDon;
import com.example.models.DanhMuc;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class MainController {

    private static final String KHACH_HANG_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\KhachHang.tsv";
    private static final String TIEM_CAM_DO_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\TiemCamDo.tsv";
    private static final String DANH_MUC_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\DanhMuc.tsv";
    private static final String TINH_THANH_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\TinhThanh.tsv";
    private static final String HOA_DON_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\HoaDon.tsv";
    private static final String CHI_TIET_HD_PATH = "D:\\TichHop\\SE_445_Demo\\src\\main\\java\\com\\example\\data\\ChiTietHoaDon.tsv";

    @Autowired
    ReadAndWriteFile readAndWriteFile;

    @Autowired
    private TinhThanhService tinhThanhService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private TiemCamDoService tiemCamDoService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @RequestMapping(value = "tsv-to-maria/{file}", method = RequestMethod.POST)
    public ResponseEntity<?> mariaToTsv(@PathVariable("file") List<?> files) {
        /* To store records successfully */
        List<Integer> recordSuccess = new ArrayList<>();
//        List<String , Integer> map = new HashMap<>();

        int count = 0;

        for(int i = 0 ; i < files.size() ; i++) {
            String fileName = (String) files.get(i);
            switch (fileName) {
                case "ChiTietHoaDon.tsv":
                    count = getRecords(1);
//                    recordSuccess.add(getRecords(1));
//                    map.put(fileName, getRecords(1));
                    break;
                case "DanhMuc.tsv":
                    count = getRecords(2);
//                    recordSuccess.add(getRecords(2));
//                    map.put(fileName, getRecords(2));
                    break;
                case "KhachHang.tsv": break;
                case "HoaDon.tsv": break;
                case "TiemCamDo.tsv": break;
                case "TinhThanh.tsv": break;
            }
        }

        return new ResponseEntity<>(count , HttpStatus.OK);
    }

    /* Chi tiet hoa don */
    public Integer getRecords(int choose) {
        /* To store records successfully */
        int counter = 0;

        if(choose == 1) {
            List<ChiTietHoaDon> orderDetailDB = chiTietHoaDonService.findAll();
            List<Object> orderDetailTSV = (List<Object>) readAndWriteFile.readFileOrderDetail(CHI_TIET_HD_PATH);

            /* Chi tiet Hoa Don */
            for (Object o : orderDetailTSV) {
                if (!orderDetailDB.isEmpty()) {
                    ChiTietHoaDon chiTietHoaDon = chiTietHoaDonService.findById(((ChiTietHoaDon)o).getChi_tiet_hoa_don_id());
                    if (!orderDetailDB.contains(chiTietHoaDon)) {
                        counter++;
                        chiTietHoaDonService.save((ChiTietHoaDon) o);
                    } else {
                        continue;
                    }
                } else {
                    counter++;
                    chiTietHoaDonService.save((ChiTietHoaDon) o);
                }
            }

            return counter;
        } else {
            List<DanhMuc> categoriesDB = danhMucService.findAll();
            List<Object> categoriesTSV = (List<Object>) readAndWriteFile.readFile(DANH_MUC_PATH ,5);

            /* Chi tiet Hoa Don */
            for (Object o : categoriesTSV) {
                if (!categoriesDB.isEmpty()) {
                    DanhMuc danhMuc = danhMucService.findById(((DanhMuc)o).getMa_danh_muc());
                    if (!categoriesDB.contains(danhMuc)) {
                        counter++;
                        danhMucService.save((DanhMuc) o);
                    } else {
                        continue;
                    }
                } else {
                    counter++;
                    danhMucService.save((DanhMuc) o);
                }
            }

            return counter;
        }
    }
}
