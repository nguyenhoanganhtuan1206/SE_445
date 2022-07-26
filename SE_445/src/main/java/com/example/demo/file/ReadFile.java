package com.example.demo.file;

import com.example.demo.models.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReadFile {

    @Autowired
    private TinhThanhService tinhThanhService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private TiemCamDoService tiemCamDoService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    HoaDonService hoaDonService;

    /* choose 1 is KhachHang
     - 2 is TiemCamDo
     - 3 is ChiTietHoaDon
     - 4 is HoaDon
     - 5 is Danh Muc
     - 6 Tinh Thanh
    * */

    public List<? extends Object> readFile(String filePath, int choose) {
        List<Object> objects = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        String line = null;
        String[] a;

        try {
            if (choose == 1) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    /* Find tinhThanh By Id */
                    Long id = Long.parseLong(a[7]);
                    TinhThanh tinhThanh = tinhThanhService.findById(id);
                    Object khachHang = new KhachHang(a[0], a[1], a[2], a[3], a[4], a[5], a[6], tinhThanh);
                    objects.add(khachHang);
                }
            } else if (choose == 2) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    Object tiemCamDo = new TiemCamDo(a[0], a[1], a[2]);

                    objects.add(tiemCamDo);
                }
            } else if (choose == 3) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    /* Create object with constructor */
                    HoaDon hoaDon = hoaDonService.findById(a[4]);

                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(a[0] , Integer.parseInt(a[1]) , a[2] , Double.parseDouble(a[3]) ,hoaDon);

                    objects.add(chiTietHoaDon);
                }
            } else if (choose == 4) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    /* Create object with constructor */
                    TiemCamDo tiemCamDo = tiemCamDoService.findById(a[6]);
                    KhachHang khachHang = khachHangService.findById(a[7]);
                    DanhMuc danhMuc = danhMucService.findById(a[8]);

                    HoaDon hoaDon = new HoaDon(a[0], a[1], Double.parseDouble(a[2]), Boolean.parseBoolean(a[3]), a[4], a[5], tiemCamDo, khachHang, danhMuc);

                    objects.add(hoaDon);
                }
            } else if (choose == 5) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    DanhMuc danhMuc = new DanhMuc(a[0], a[1]);

                    objects.add(danhMuc);
                }
            } else if (choose == 6) {
                a = null;
                bufferedReader = new BufferedReader(new FileReader(file));
                while ((line = bufferedReader.readLine()) != null) {
                    a = line.split(" , ");

                    TinhThanh tinhThanh = new TinhThanh(Long.parseLong(a[0]), a[1]);

                    objects.add(tinhThanh);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
