package com.example.controller;

import com.example.file.ReadAndWriteFile;
import com.example.models.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Tie;
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
        int count = 0;

        for (int i = 0; i < files.size(); i++) {
            String fileName = (String) files.get(i);
            switch (fileName) {
                case "KhachHang.tsv":
                    count = getRecords(1);
                    break;
                case "TiemCamDo.tsv":
                    count = getRecords(2);
                    break;
                case "ChiTietHoaDon.tsv":
                    count = getRecords(3);
                    break;
                case "HoaDon.tsv":
                    count = getRecords(4);
                    break;
                case "DanhMuc.tsv":
                    count = getRecords(5);
                    break;
                case "TinhThanh.tsv":
                    count = getRecords(6);
                    break;
            }
        }

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /* choose 1 is KhachHang
       - 2 is TiemCamDo
       - 3 is ChiTietHoaDon
       - 4 is HoaDon
       - 5 is Danh Muc
       - 6 Tinh Thanh
      * */
    public Integer getRecords(int choose) {
        /* To store records successfully */
        int counter = 0;

        if (choose == 3) {
            List<ChiTietHoaDon> orderDetailDB = chiTietHoaDonService.findAll();
            List<Object> orderDetailTSV = (List<Object>) readAndWriteFile.readFile(CHI_TIET_HD_PATH, 3);

            /* Chi tiet Hoa Don */
            for (Object o : orderDetailTSV) {
                try {
                    if (!orderDetailDB.isEmpty()) {
                        ChiTietHoaDon chiTietHoaDon = chiTietHoaDonService.findById(((ChiTietHoaDon) o).getChi_tiet_hoa_don_id());
                        if (!orderDetailDB.contains(chiTietHoaDon)) {
                            chiTietHoaDonService.save((ChiTietHoaDon) o);
                        } else {
                            continue;
                        }

                    } else {
                        chiTietHoaDonService.save((ChiTietHoaDon) o);
                    }
                    counter++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return counter;
        } else if (choose == 2) {
            List<TiemCamDo> tiemCamDoDB = tiemCamDoService.findAll();
            List<Object> tiemCamDoTSV = (List<Object>) readAndWriteFile.readFile(TIEM_CAM_DO_PATH, 2);

            /* Chi tiet Hoa Don */
            for (Object o : tiemCamDoTSV) {
                if (!tiemCamDoDB.isEmpty()) {
                    try {
                        TiemCamDo tiemCamDo = tiemCamDoService.findById(((TiemCamDo) o).getMa_tiem());
                        if (!tiemCamDoDB.contains(tiemCamDo)) {
                            counter++;
                            tiemCamDoService.save((TiemCamDo) o);
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        counter = -1;
                        e.printStackTrace();
                    }
                } else {
                    counter++;
                    tiemCamDoService.save((TiemCamDo) o);
                }
            }

            return counter;
        } else if (choose == 1) {
            List<KhachHang> customerDB = khachHangService.findAll();
            List<Object> customerTSV = (List<Object>) readAndWriteFile.readFile(KHACH_HANG_PATH, 1);

            /* Chi tiet Hoa Don */
            for (Object o : customerTSV) {
                if (!customerDB.isEmpty()) {
                    try {
                        KhachHang khachHang = khachHangService.findById(((KhachHang) o).getKhach_hang_id());
                        if (!customerDB.contains(khachHang)) {
                            counter++;
                            khachHangService.save((KhachHang) o);
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        counter = -1;
                        e.printStackTrace();
                    }
                } else {
                    counter++;
                    khachHangService.save((KhachHang) o);
                }
            }

            return counter;
        } else if (choose == 4) {
            List<HoaDon> orderDB = hoaDonService.findAll();
            List<Object> orderTSV = (List<Object>) readAndWriteFile.readFile(HOA_DON_PATH, 4);

            /* Chi tiet Hoa Don */
            for (Object o : orderTSV) {
                if (!orderDB.isEmpty()) {
                    try {
                        HoaDon hoaDon = hoaDonService.findById(((HoaDon) o).getHoa_don_id());
                        if (!orderDB.contains(hoaDon)) {
                            counter++;
                            hoaDonService.save((HoaDon) o);
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        counter = -1;
                        e.printStackTrace();
                    }
                } else {
                    counter++;
                    hoaDonService.save((HoaDon) o);
                }
            }

            return counter;
        } else if (choose == 5) {
            List<DanhMuc> categoriesDB = danhMucService.findAll();
            List<Object> categoriesTSV = (List<Object>) readAndWriteFile.readFile(DANH_MUC_PATH, 5);

            /* Chi tiet Hoa Don */
            for (Object o : categoriesTSV) {
                if (!categoriesDB.isEmpty()) {
                    try {
                        DanhMuc danhMuc = danhMucService.findById(((DanhMuc) o).getMa_danh_muc());
                        if (!categoriesDB.contains(danhMuc)) {
                            counter++;
                            danhMucService.save((DanhMuc) o);
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        counter = -1;
                        e.printStackTrace();
                    }
                } else {
                    counter++;
                    danhMucService.save((DanhMuc) o);
                }
            }

            return counter;
        } else {
            List<TinhThanh> tinhThanhDB = tinhThanhService.findAll();
            List<Object> tinhThanhTSV = (List<Object>) readAndWriteFile.readFile(TINH_THANH_PATH, 6);

            /* Chi tiet Hoa Don */
            for (Object o : tinhThanhTSV) {
                if (!tinhThanhDB.isEmpty()) {
                    try {
                        TinhThanh tinhThanh = tinhThanhService.findById(((TinhThanh) o).getId());
                        if (!tinhThanhDB.contains(tinhThanh)) {
                            counter++;
                            tinhThanhService.save((TinhThanh) o);
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        counter = -1;
                        e.printStackTrace();
                    }
                } else {
                    counter++;
                    tinhThanhService.save((TinhThanh) o);
                }
            }

            return counter;
        }
    }
}
