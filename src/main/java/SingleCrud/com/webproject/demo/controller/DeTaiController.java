package SingleCrud.com.webproject.demo.controller;

import SingleCrud.com.webproject.demo.model.*;
import SingleCrud.com.webproject.demo.service.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DeTaiController {
    private final DeTaiService deTaiService;
    private final UserService userService;
    private final ChuyenMonService chuyenmonService;
    private final LinhVucService linhvucService;
    private final NguoiThucHienService nguoiThucHienService;
    private final DeTaiDangThucHienService deTaiDangThucHienService;
    private final HoiDongChamService hoiDongChamService;
    private final BaiBaoService baiBaoService;
    private final DeTaiHoanThanhService deTaiHoanThanhService;

    @Autowired
    public DeTaiController(DeTaiService deTaiService, UserService userService, ChuyenMonService chuyenmonService, LinhVucService linhvucService, NguoiThucHienService nguoiThucHienService, DeTaiDangThucHienService deTaiDangThucHienService, HoiDongChamService hoiDongChamService, BaiBaoService baiBaoService, DeTaiHoanThanhService deTaiHoanThanhService) {
        this.deTaiService = deTaiService;
        this.userService = userService;
        this.chuyenmonService = chuyenmonService;
        this.linhvucService = linhvucService;
        this.nguoiThucHienService = nguoiThucHienService;
        this.deTaiDangThucHienService = deTaiDangThucHienService;
        this.hoiDongChamService = hoiDongChamService;
        this.baiBaoService = baiBaoService;
        this.deTaiHoanThanhService = deTaiHoanThanhService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<BaiBao> baiBaoList = baiBaoService.findAll();
        List< Pair<DeTai, BaiBao> > list = new ArrayList< Pair<DeTai, BaiBao> >() ;
        for (int i = 0; i < baiBaoList.size(); i++) {
            list.add(Pair.of(deTaiService.findById(baiBaoList.get(i).getIDDeTai()), baiBaoList.get(i)));
        }
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/listDeTai/{accountView}")
    public String getListDeTai(Model model,  @PathVariable("accountView") String accountView) {
        User viewer = userService.findByName(accountView);
        String status = "";
        List<DeTai> detaiList = deTaiService.findAll();
        List<Pair<DeTai, User>> list = new ArrayList<Pair<DeTai, User>>();
        for (int i = 0; i < detaiList.size(); i++) {
            if (detaiList.get(i).getIDNguoihuongdan()!=null) {
                Pair pair = Pair.of(detaiList.get(i), userService.findById(detaiList.get(i).getIDNguoihuongdan()));
                list.add(pair);
            } else {
                Pair pair = Pair.of(detaiList.get(i), new User());
                list.add(pair);
            }
        }
        String pageOld = "listDeTai";
        model.addAttribute("pageold", pageOld);
        model.addAttribute("viewer", viewer);
        model.addAttribute("list",  list);
        model.addAttribute("status", status);
        return "listDeTai";
    }

    @PostMapping("/listDeTai/{accountView}")
    public String postListDeTai(Model model, @PathVariable("accountView") String accountView, @ModelAttribute("status") String status) {
//        System.out.println(status);
        User viewer = userService.findByName(accountView);
        model.addAttribute("viewer", viewer);
        List<DeTai> statusList = deTaiService.findByStatus(status);
        List<Pair<DeTai, User>> list = new ArrayList<Pair<DeTai, User>>();
        for (int i = 0; i < statusList.size(); i++) {
            if (statusList.get(i).getIDNguoihuongdan()!=null) {
                Pair pair = Pair.of(statusList.get(i), userService.findById(statusList.get(i).getIDNguoihuongdan()));
                list.add(pair);
            } else {
                Pair pair = Pair.of(statusList.get(i), new User());
                list.add(pair);
            }
        }
        String pageOld = "listDeTai";
        model.addAttribute("pageold", pageOld);
        model.addAttribute("list", list);
        return "listDeTai";
    }

    @GetMapping("/chiTietDeTai/{pageold}/{idDeTai}/{accountView}")
    public String getChiTiet(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer, @PathVariable("pageold") String pageold, Model model) {
        DeTai detai = deTaiService.findById(idDeTai);
        List<ChuyenMon> chuyenMonList = chuyenmonService.findAll();
        List<LinhVuc> linhvucList = linhvucService.findAll();
        List<LinhVuc> linhvucCuaDeTai = new ArrayList<LinhVuc>();
        List<DeTaiDangThucHien> deTaiDangThucHiens = deTaiDangThucHienService.findAll();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        String plane = "";
        String status = "";
        String newTienDo = "";

        User nguoithuchien = new User();
        User viewer = userService.findByName(accViewer);
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            if (nguoiThucHienList.get(i).getIDDeTai().equals(idDeTai)) {
                nguoithuchien = userService.findById(nguoiThucHienList.get(i).getIDNguoiThucHien());
            }
        }
        for (int  i = 0; i < deTaiDangThucHiens.size(); i++) {
            if (deTaiDangThucHiens.get(i).getIDDeTai().equals(idDeTai)) {
                plane = deTaiDangThucHiens.get(i).getTienDo();
                break;
            }
        }

        for(int i = 0; i < chuyenMonList.size(); i++) {
            String idLinhvuc = "";
            if(chuyenMonList.get(i).getIDDeTai().equals(idDeTai)) {
                idLinhvuc = chuyenMonList.get(i).getIDLinhVuc();
            }
            for(int j = 0; j < linhvucList.size(); j++) {
                if (linhvucList.get(j).getIDLinhVuc().equals(idLinhvuc)) {
                    linhvucCuaDeTai.add(linhvucList.get(j));
                }
            }
        }
        if (linhvucCuaDeTai.size()==0) {
            linhvucCuaDeTai.add(new LinhVuc("chua co linh vuc"));
        }
        User nguoiHuongDan = new User();
        if (detai.getIDNguoihuongdan() !=null) {
            nguoiHuongDan = userService.findById(detai.getIDNguoihuongdan());
        }
        model.addAttribute("nguoithuchien", nguoithuchien);
        model.addAttribute("pageold", pageold);
        model.addAttribute("nguoiHuongDan", nguoiHuongDan);
        model.addAttribute("detai", detai);
        model.addAttribute("linhVucCuaDeTai", linhvucCuaDeTai);
        model.addAttribute("viewer", viewer);
        model.addAttribute("status", status);
        model.addAttribute("newTienDo", newTienDo);
        model.addAttribute("plane",  plane);
        if (pageold.equals("listDeTaiCham") && viewer.getRole().equals("3")) {
            String diem = hoiDongChamService.findByDeTaiAndUser(idDeTai, userService.findByName(accViewer).getID()).getDiem();
            model.addAttribute("diem", diem);
        }
        return "chiTietDeTai";
    }

    @PostMapping("/chiTietDeTai/{pageold}/{idDeTai}/{accountView}")
    public String postUpdate(Model model, @PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer, @PathVariable("pageold") String pageold, @ModelAttribute("status") String status, @ModelAttribute("newTienDo") String newTiendo, @ModelAttribute("diem") String diem) {
        if (!status.equals("")) {
            deTaiService.changeStatus(idDeTai, status);
        } else if (!newTiendo.equals("")) {
            DeTaiDangThucHien deTaiDangThucHien =  deTaiDangThucHienService.findByIdDeTai(idDeTai);
            deTaiDangThucHienService.updateTienDo(deTaiDangThucHien, idDeTai , newTiendo);
        } else if (!diem.equals("")) {
            User user = userService.findByName(accViewer);
            HoiDongCham hoiDongCham = hoiDongChamService.findByDeTaiAndUser(idDeTai, user.getID());
            hoiDongChamService.updateDiem(hoiDongCham, diem);
        }
        return getChiTiet(idDeTai, accViewer, pageold, model);
    }

    @GetMapping("/listHuongDan/{accountView}")
    public String getListHuongDan(@PathVariable("accountView") String accountView, Model model) {
        User nguoihuongdan = userService.findByName(accountView);
        List<DeTai> deTaiList = deTaiService.findAll();
        List<DeTai> deTaiHuongDan = new ArrayList<DeTai>();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List<String> idDeTaiThucHienList = new ArrayList<String>();
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            idDeTaiThucHienList.add(nguoiThucHienList.get(i).getIDDeTai());
        }
        List< Pair<DeTai, User> > listHuongDan =  new ArrayList< Pair<DeTai, User> >();
        for (int  i = 0; i <deTaiList.size(); i++) {
            if (deTaiList.get(i).getIDNguoihuongdan() != null && deTaiList.get(i).getIDNguoihuongdan().equals(nguoihuongdan.getID())) {
                deTaiHuongDan.add(deTaiList.get(i));
            }
        }
        for (int i = 0; i < deTaiHuongDan.size(); i++) {
            if (idDeTaiThucHienList.contains(deTaiHuongDan.get(i).getIDDeTai())) {
                Pair pair = Pair.of(deTaiHuongDan.get(i), userService.findById(nguoiThucHienService.findIDNguoiThucHien(deTaiHuongDan.get(i).getIDDeTai())));
                listHuongDan.add(pair);
            } else {
                listHuongDan.add(Pair.of(deTaiHuongDan.get(i), new User()));
            }

        }
        String pageOld = "listHuongDan";
        model.addAttribute("pageold", pageOld);
        model.addAttribute("nguoihuongdan", nguoihuongdan);
        model.addAttribute("listHuongDan", listHuongDan);
        return "listHuongDan";
    }

    @GetMapping("/dangThucHien/{accountView}")
    public String getListDangThucHien(@PathVariable("accountView") String accountView, Model model) {
        User viewer = userService.findByName(accountView);
        List<DeTaiDangThucHien> list = deTaiDangThucHienService.findAll();
        List<DeTai> deTaiList = new ArrayList<DeTai>();
        List<Pair<DeTaiDangThucHien ,DeTai>> dangThucHienList = new ArrayList<Pair<DeTaiDangThucHien ,DeTai>>();
        for (int i = 0 ;i < list.size(); i++) {
            dangThucHienList.add(Pair.of(list.get(i), deTaiService.findById(list.get(i).getIDDeTai())));
        }
        String pageOld = "dangThucHien";
        model.addAttribute("pageold", pageOld);
        model.addAttribute("viewer", viewer);
        model.addAttribute("listDangThucHien", dangThucHienList);
        return "dangThucHien";
    }

    @GetMapping("/suaDeTai/{pageold}/{idDeTai}/{accountView}")
    public String getSuaDeTai(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer, @PathVariable("pageold") String pageold, Model model) {
        DeTai detai = deTaiService.findById(idDeTai);
        List<ChuyenMon> chuyenMonList = chuyenmonService.findAll();
        List<LinhVuc> linhvucList = linhvucService.findAll();
        List<LinhVuc> linhvucCuaDeTai = new ArrayList<LinhVuc>();
        String status = "";
        User viewer = userService.findByName(accViewer);
        for(int i = 0; i < chuyenMonList.size(); i++) {
            String idLinhvuc = "";
            if(chuyenMonList.get(i).getIDDeTai().equals(idDeTai)) {
                idLinhvuc = chuyenMonList.get(i).getIDLinhVuc();
            }
            for(int j = 0; j < linhvucList.size(); j++) {
                if (linhvucList.get(j).getIDLinhVuc().equals(idLinhvuc)) {
                    linhvucCuaDeTai.add(linhvucList.get(j));
                }
            }
        }
        if (linhvucCuaDeTai.size()==0) {
            linhvucCuaDeTai.add(new LinhVuc("Chưa có lĩnh vực"));
        }
        User nguoiHuongDan = new User();
        if (detai.getIDNguoihuongdan() !=null) {
            nguoiHuongDan = userService.findById(detai.getIDNguoihuongdan());
        }
        model.addAttribute("nguoiHuongDan", nguoiHuongDan);
        model.addAttribute("detai", detai);
        model.addAttribute("linhVucCuaDeTai", linhvucCuaDeTai);
        model.addAttribute("viewer", viewer);
        model.addAttribute("status", status);
        model.addAttribute("pageold", pageold);
        return "suaDeTai";
    }

    @PostMapping("/suaDeTai/{pageold}/{idDeTai}/{accountView}")
    public String postSuaDeTai(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer, @PathVariable("pageold") String pageold, Model model, @ModelAttribute DeTai newDeTai ) {
        DeTai deTai = deTaiService.findById(idDeTai);
        deTaiService.update(deTai, newDeTai);
        return getSuaDeTai(idDeTai, accViewer, pageold , model);
    }

    @GetMapping("/themDeTai/{accountView}")
    public String getThemDeTai(Model model, @PathVariable("accountView") String accViewer) {
        DeTai deTai = new DeTai();
        User user = userService.findByName(accViewer);
        String pageold = "";
        if (user.getRole().equals("3")) {
            pageold = "canbo";
        } else  if (user.getRole().equals("4")) {
            pageold = "nghiencuusinh";
        }
        model.addAttribute("pageold", pageold);
        model.addAttribute("detai", deTai);
        model.addAttribute("viewer", user);
        return "themDeTai";
    }

    @PostMapping("/themDeTai/{accountView}")
    public String postThemDeTai(Model model, @PathVariable("accountView") String accViewer, @ModelAttribute DeTai newDeTai, RedirectAttributes redirectAttrs) {
        User accountAdd = userService.findByName(accViewer);
        String id = deTaiService.addDeTai(newDeTai);
        redirectAttrs.addAttribute("accViewer", accViewer);
        redirectAttrs.addAttribute("idDeTai", id);
        return "redirect:/themLinhVucDeTai/{idDeTai}/{accViewer}";
    }

    @GetMapping("/themLinhVucDeTai/{idDeTai}/{accViewer}")
    public String getThemLinhVuc(Model model, @PathVariable("accViewer") String accViewer, @PathVariable("idDeTai") String idDeTai) {
        List<LinhVuc>  linhVucList = linhvucService.findAll();
        String checked = "";
        model.addAttribute("linhvuc", linhVucList);
        model.addAttribute("idDeTai", idDeTai);
        model.addAttribute("accViewer", accViewer);
        model.addAttribute("checked", checked);
        return "themLinhVucDeTai";
    }

    @PostMapping("/themLinhVucDeTai/{idDeTai}/{accViewer}")
    public String postThemLinhVuc(Model model, @ModelAttribute("checked") String checked, @PathVariable("accViewer") String accViewer, @PathVariable("idDeTai") String idDeTai, RedirectAttributes redirectAttrs) {
        String[] idlinhVucCanThem = checked.split("/");
        for (int i = 0; i < idlinhVucCanThem.length; i++) {
            if (!idlinhVucCanThem[i].equals("")) {
                chuyenmonService.addChuyenMon(idlinhVucCanThem[i], idDeTai);
            }
        }
        redirectAttrs.addAttribute("accViewer",accViewer);
        return "redirect:/dangKyLamNghienCuu/{accViewer}";
    }

    @GetMapping("/listChoHuongDan/{accViewer}")
    public String getChoHuongDan(Model model, @PathVariable("accViewer") String accViewer) {
        User viewer = userService.findByName(accViewer);
        List<DeTai> deTaiList = deTaiService.findAll();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List<Pair<DeTai, User>> nguoidexuat = new ArrayList<Pair<DeTai, User>>();
        for (int i = 0; i < deTaiList.size(); i++) {
            if(deTaiList.get(i).getIDNguoihuongdan() == null) {
                for (int j = 0; j < nguoiThucHienList.size(); j++) {
                    if (deTaiList.get(i).getIDDeTai().equals(nguoiThucHienList.get(j).getIDDeTai())) {
                        User nguoithuchien = userService.findById(nguoiThucHienList.get(j).getIDNguoiThucHien());
                        if (!nguoithuchien.equals(viewer)){
                            nguoidexuat.add(Pair.of(deTaiList.get(i), userService.findById(nguoiThucHienList.get(j).getIDNguoiThucHien())));
                        }
                    }
                }
            }
        }
        String checked = "";
        String pageOld = "listChoHuongDan";
        model.addAttribute("pageold", pageOld);
        model.addAttribute("viewer", viewer);
        model.addAttribute("nguoidexuat", nguoidexuat);
        model.addAttribute("checked", checked);
        return "listChoHuongDan";
    }

    @PostMapping("/listChoHuongDan/{accViewer}")
    public String postChoHuongDan(Model model, @PathVariable("accViewer") String accViewer, @ModelAttribute("checked") String checked) {
        String[] idDeTaiHuongDan = checked.split("/");
        User nguoihuongdan = userService.findByName(accViewer);
        for(int i = 0; i < idDeTaiHuongDan.length; i++) {
            if(!idDeTaiHuongDan[i].equals("")) {
                DeTai deTai =  deTaiService.findById(idDeTaiHuongDan[i]);
                deTaiService.updateNguoiHuongDan(deTai, nguoihuongdan.getID());
            }
        }
        return getChoHuongDan(model, accViewer);
    }

    @GetMapping("/listYeuCauPheDuyet/{accViewer}")
    public String getYeuCau(Model model, @PathVariable("accViewer") String accViewer) {
        User nguoihuongdan = userService.findByName(accViewer);
        List<DeTai> deTaiList = deTaiService.findAll();
        List<DeTai> listChuaXet = new ArrayList<DeTai>();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List< Pair<DeTai, User> > nguoithuchien = new ArrayList< Pair<DeTai,User> >();
        for (int i = 0; i < deTaiList.size(); i++) {
            if (deTaiList.get(i).getIDNguoihuongdan()!=null && deTaiList.get(i).getIDNguoihuongdan().equals(nguoihuongdan.getID()) && deTaiList.get(i).getTrangThai().equals("chuaxet")) {
                listChuaXet.add(deTaiList.get(i));
            }
        }
        for (int i = 0; i < listChuaXet.size(); i++) {
            int count = 0;
            for (int j = 0; j < nguoiThucHienList.size(); j++) {
                if (listChuaXet.get(i).getIDDeTai().equals(nguoiThucHienList.get(j).getIDDeTai())) {
                    count++;
                    Pair<DeTai, User> deTaiUserPair =  Pair.of(listChuaXet.get(i), userService.findById(nguoiThucHienList.get(j).getIDNguoiThucHien()));
                    nguoithuchien.add(deTaiUserPair);
                }
            }
            if (count==0) {
                Pair<DeTai, User> deTaiUserPair =  Pair.of(listChuaXet.get(i), new User());
                nguoithuchien.add(deTaiUserPair);
            }
        }
        String checked = "";
        String pageold = "listYeuCauPheDuyet";
        model.addAttribute("checked", checked);
        model.addAttribute("nguoithuchien", nguoithuchien);
        model.addAttribute("pageold", pageold);
        model.addAttribute("accViewer",  nguoihuongdan);
        return "listYeuCauPheDuyet";
    }

    @PostMapping("/listYeuCauPheDuyet/{accViewer}")
    public String postGuiYeuCau(Model model, @PathVariable("accViewer") String accViewer, @ModelAttribute("checked") String checked) {
        String[] idDeTai = checked.split("/");
        for (int i = 0; i < idDeTai.length; i++) {
            if (!idDeTai[i].equals("")) {
                DeTai deTai = deTaiService.findById(idDeTai[i]);
                deTaiService.updateStatus(deTai, "dangxet");
            }
        }
        return getYeuCau(model, accViewer);
    }

    @GetMapping("/listDeTaiCham/{accViewer}")
    public String getChamThi(Model model, @PathVariable("accViewer") String accViewer) {
        User nguoicham = userService.findByName(accViewer);
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List< Pair<DeTai, User> > deTaiCham  = new ArrayList< Pair<DeTai,User> >();
        for (int i = 0; i < hoiDongChamList.size(); i++) {
            if (nguoicham.getID().equals(hoiDongChamList.get(i).getIDCanBo())) {
                Pair<DeTai, User> detai = Pair.of(deTaiService.findById(hoiDongChamList.get(i).getIDDeTai()), userService.findById(deTaiService.findById(hoiDongChamList.get(i).getIDDeTai()).getIDNguoihuongdan()));
                deTaiCham.add(detai);
            }
        }
        String pageold = "listDeTaiCham";
        model.addAttribute("pageold", pageold);
        model.addAttribute("deTaiCham", deTaiCham);
        model.addAttribute("viewer", nguoicham);
        return "listDeTaiCham";
    }

    @GetMapping("/deTaiDangThucHien/{pageold}/{accViewer}")
    public String getThucHien(Model model,  @PathVariable("accViewer") String accViewer, @PathVariable("pageold") String pageold) {
        User nguoithuchien = userService.findByName(accViewer);
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List<DeTai> deTaiDangThucHien = new ArrayList<DeTai>();
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            if (nguoiThucHienList.get(i).getIDNguoiThucHien().equals(nguoithuchien.getID())) {
                deTaiDangThucHien.add(deTaiService.findById(nguoiThucHienList.get(i).getIDDeTai()));
            }
        }
        model.addAttribute("pageold", pageold);
        model.addAttribute("deTaiDangThucHien", deTaiDangThucHien);
        model.addAttribute("nguoithuchien", nguoithuchien);
        return "deTaiDangThucHien";
    }

    @GetMapping("/dangKyLamNghienCuu/{accViewer}")
    public String getLamNghienCuu(Model model, @PathVariable("accViewer") String accViewer) {
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List<String> idDeTaiThuchien = new ArrayList<String>();
        List<DeTai> deTaiList = deTaiService.findAll();
        List<DeTai> deTaiCoThe = new ArrayList<>();
        User user = userService.findByName(accViewer);
        String checked = "";
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            idDeTaiThuchien.add(nguoiThucHienList.get(i).getIDDeTai());
        }
        for (int i = 0; i < deTaiList.size(); i++) {
            if (!idDeTaiThuchien.contains(deTaiList.get(i).getIDDeTai())) {
                if ( deTaiList.get(i).getIDNguoihuongdan() == null) {
                    deTaiCoThe.add(deTaiList.get(i));
                }  else {
                    if (!deTaiList.get(i).getIDNguoihuongdan().equals(user.getID())) {
                        deTaiCoThe.add(deTaiList.get(i));
                    }
                }
            }
        }
        String pageold = "";
        if (user.getRole().equals("3")) {
            pageold = "canbo";
        } else  if (user.getRole().equals("4")) {
            pageold = "nghiencuusinh";
        }
        model.addAttribute("checked", checked);
        model.addAttribute("pageold", pageold);
        model.addAttribute("deTaiCoThe", deTaiCoThe);
        model.addAttribute("user", user);
        return "dangKyLamNghienCuu";

    }

    @PostMapping("/dangKyLamNghienCuu/{accViewer}")
    public String postLamNghienCuu(Model model, @PathVariable("accViewer") String accViewer, @ModelAttribute("checked") String checked) {
        String[] idDeTai = checked.split("/");
        User user = userService.findByName(accViewer);
        for (int i = 0; i < idDeTai.length; i++) {
            if (!idDeTai[i].equals("")) {
                NguoiThucHien nguoiThucHien = new NguoiThucHien(idDeTai[i], user.getID());
                nguoiThucHienService.add(nguoiThucHien);
            }
        }
        return getLamNghienCuu(model, accViewer);
    }

    @GetMapping("/danhSachBaiBao/{accViewer}")
    public String getBaiBao(Model model, @PathVariable("accViewer") String accViewer) {
        User viewer = userService.findByName(accViewer);
        List<BaiBao> baiBaoList = baiBaoService.findAll();
        List< Pair<DeTai, BaiBao> > list = new ArrayList< Pair<DeTai, BaiBao> >() ;
        for (int i = 0; i < baiBaoList.size(); i++) {
            list.add(Pair.of(deTaiService.findById(baiBaoList.get(i).getIDDeTai()), baiBaoList.get(i)));
        }
        String pageold = "";
        if (viewer.getRole().equals("4")) {
            pageold = "nghiencuusinh";
        } else if (viewer.getRole().equals("3")) {
            pageold = "canbo";
        }
        model.addAttribute("pageold", pageold);
        model.addAttribute("list",  list);
        model.addAttribute("viewer", viewer);
        return "danhSachBaiBao";
    }

    @GetMapping("/chiTietBaiBao/{idDeTai}/{accViewer}")
    public String getChiTietBaiBao(Model model, @PathVariable("idDeTai") String idDeTai, @PathVariable("accViewer") String accViewer) {
        String pageold = "chiTietBaiBao";
        BaiBao baiBao = new BaiBao();
        DeTai deTai = deTaiService.findById(idDeTai);
        User user = userService.findByName(accViewer);
        List<BaiBao> baiBaoList = baiBaoService.findAll();
        for (int  i = 0; i < baiBaoList.size(); i++) {
            if (baiBaoList.get(i).getIDDeTai().equals(idDeTai)) {
                baiBao = baiBaoList.get(i);
                break;
            }
        }
        model.addAttribute("detai", deTai);
        model.addAttribute("baibao", baiBao);
        model.addAttribute("pageold", pageold);
        model.addAttribute("viewer", user);
        return "chiTietBaiBao";
    }

    @GetMapping("/deTaiDaLamDuoc/{accViewer}")
    public String getDeTaiLamDuoc(Model model, @PathVariable("accViewer") String accViewer) {
        User user =  userService.findByName(accViewer);
        List<DeTaiHoanThanh> hoanThanhList = deTaiHoanThanhService.findAll();
        List<NguoiThucHien> thucHienList = nguoiThucHienService.findDeTaiByIDUser(user.getID());
        List<String> idDeTaiThucHien = new ArrayList<String>();
        List< Pair<DeTai, DeTaiHoanThanh> >  deTaiThucHien = new ArrayList< Pair<DeTai, DeTaiHoanThanh> >();
        for (int i = 0; i < thucHienList.size(); i++) {
            idDeTaiThucHien.add(thucHienList.get(i).getIDDeTai());
        }
        for (int i = 0; i < hoanThanhList.size(); i++) {
            if (idDeTaiThucHien.contains(hoanThanhList.get(i).getIDDeTai())) {
                Pair pair = Pair.of(deTaiService.findById(hoanThanhList.get(i).getIDDeTai()), hoanThanhList.get(i));
                deTaiThucHien.add(pair);
            }
        }
        String pageold = "deTaiDaLamDuoc";
        model.addAttribute("pageold", pageold);
        model.addAttribute("detai", deTaiThucHien);
        model.addAttribute("user", user);
        return "deTaiDaLamDuoc";
    }

    @GetMapping("/xemBaiBao/{idDeTai}")
    public String getXemBaiBao(Model model, @PathVariable("idDeTai") String idDeTai) {
        String pageold = "xemBaiBao";
        BaiBao baiBao = new BaiBao();
        DeTai deTai = deTaiService.findById(idDeTai);
        List<BaiBao> baiBaoList = baiBaoService.findAll();
        for (int  i = 0; i < baiBaoList.size(); i++) {
            if (baiBaoList.get(i).getIDDeTai().equals(idDeTai)) {
                baiBao = baiBaoList.get(i);
                break;
            }
        }
        model.addAttribute("detai", deTai);
        model.addAttribute("baibao", baiBao);
        model.addAttribute("pageold", pageold);
        return "xemBaiBao";
    }

    @GetMapping("/xemDeTai/{pageold}/{idDeTai}")
    public  String getXemDeTai(Model model, @PathVariable("idDeTai") String idDeTai, @PathVariable("pageold") String pageold) {
        DeTai detai = deTaiService.findById(idDeTai);
        List<ChuyenMon> chuyenMonList = chuyenmonService.findAll();
        List<LinhVuc> linhvucList = linhvucService.findAll();
        List<LinhVuc> linhvucCuaDeTai = new ArrayList<LinhVuc>();
        List<DeTaiDangThucHien> deTaiDangThucHiens = deTaiDangThucHienService.findAll();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        User nguoithuchien = new User();
        for (int i = 0; i < nguoiThucHienList.size(); i++) {
            if (nguoiThucHienList.get(i).getIDDeTai().equals(idDeTai)) {
                nguoithuchien = userService.findById(nguoiThucHienList.get(i).getIDNguoiThucHien());
            }
        }
        for(int i = 0; i < chuyenMonList.size(); i++) {
            String idLinhvuc = "";
            if(chuyenMonList.get(i).getIDDeTai().equals(idDeTai)) {
                idLinhvuc = chuyenMonList.get(i).getIDLinhVuc();
            }
            for(int j = 0; j < linhvucList.size(); j++) {
                if (linhvucList.get(j).getIDLinhVuc().equals(idLinhvuc)) {
                    linhvucCuaDeTai.add(linhvucList.get(j));
                }
            }
        }
        if (linhvucCuaDeTai.size()==0) {
            linhvucCuaDeTai.add(new LinhVuc("chua co linh vuc"));
        }
        User nguoiHuongDan = new User();
        if (detai.getIDNguoihuongdan() !=null) {
            nguoiHuongDan = userService.findById(detai.getIDNguoihuongdan());
        }
        model.addAttribute("nguoithuchien", nguoithuchien);
        model.addAttribute("pageold", pageold);
        model.addAttribute("nguoiHuongDan", nguoiHuongDan);
        model.addAttribute("detai", detai);
        model.addAttribute("linhVucCuaDeTai", linhvucCuaDeTai);
        return "xemDeTai";
    }

    @GetMapping("/xoaDeTai/{idDeTai}/{accountView}")
    public String xoaDeTai(RedirectAttributes redirectAttributes, @PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accountView) {
        deTaiHoanThanhService.deleteByIdDeTai(idDeTai);
        baiBaoService.deleteByIdDeTai(idDeTai);
        chuyenmonService.deleteByIdDeTai(idDeTai);
        nguoiThucHienService.deleteByIdDeTai(idDeTai);
        deTaiDangThucHienService.deleteByIdDeTai(idDeTai);
        hoiDongChamService.deleteByIdDeTai(idDeTai);
        deTaiService.deleteById(idDeTai);
        redirectAttributes.addAttribute("accountView", accountView);
        return "redirect:/listDeTai/{accountView}";
    }

    @GetMapping("/danhsachdacham/{accountView}")
    public String xemDeTaiDaCham(@PathVariable("accountView") String accountView, Model model) {
        User viewer = userService.findByName(accountView);
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List<String> deTaiDaCham = hoiDongChamList.stream()
                .map(element -> element.getDiem() != null ? element.getIDDeTai() : "")
                .filter(element -> !element.equals("")).collect(Collectors.toList());

        List<DeTai> deTaiList = new ArrayList<DeTai>();
        for (int i = 0; i < deTaiDaCham.size(); i++) {
            DeTai deTai = deTaiService.findById(deTaiDaCham.get(i));
            if (!deTaiList.contains(deTai)) {
                deTaiList.add(deTai);
            }
        }
        model.addAttribute("deTaiCham", deTaiList);
        model.addAttribute("viewer", viewer);
        return "danhsachdacham";
    }

    @GetMapping("/diemDeTai/{idDeTai}/{accountView}")
    public String diemDeTai(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accountView,
                            Model model) {
        User user = userService.findByName(accountView);
        DeTai deTai = deTaiService.findById(idDeTai);
        List<HoiDongCham> deTaiCham = hoiDongChamService.findByIdDeTai(idDeTai);
        List<Pair<User, String>> danhSachNguoiCham = new ArrayList<Pair<User, String>>();
        for (int i = 0; i < deTaiCham.size(); i++) {
            User user1 = userService.findById(deTaiCham.get(i).getIDCanBo());
            Pair<User, String> nguoiCham = null;
            if (deTaiCham.get(i).getDiem() == null) {
                nguoiCham = Pair.of(user1, "Chưa chấm điểm");
            } else {
                nguoiCham = Pair.of(user1, deTaiCham.get(i).getDiem());
            }
            danhSachNguoiCham.add(nguoiCham);
        }
        boolean hoanthanh = false;
        List<String> idHoanThanhList = deTaiHoanThanhService.findAll().stream()
                .map(element -> element.getIDDeTai())
                .collect(Collectors.toList());
        if (idHoanThanhList.contains(idDeTai)) {
            hoanthanh = true;
        }
        model.addAttribute("hoanthanh", hoanthanh);
        model.addAttribute("viewer", user);
        model.addAttribute("detai", deTai);
        model.addAttribute("danhSachNguoiCham", danhSachNguoiCham);
        return "diemDeTai";
    }

    @PostMapping("/diemDeTai/{idDeTai}/{accountView}")
    public String nghiemThuDeTai(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accountView,
                                 Model model) {
        User user = userService.findByName(accountView);
        DeTai deTai = deTaiService.findById(idDeTai);
        List<HoiDongCham> deTaiCham = hoiDongChamService.findByIdDeTai(idDeTai);
        float diem = 0;

        for (int i = 0; i < deTaiCham.size(); i++) {
            diem = diem + Float.parseFloat(deTaiCham.get(i).getDiem());
        }
        diem = diem / deTaiCham.size();
        deTaiHoanThanhService.insertDeTai(idDeTai ,diem);
        return diemDeTai(idDeTai, accountView, model);
    }

    @GetMapping("/vietBao/{pageold}/{idDeTai}/{accountViewer}")
    public String getVietBao(@PathVariable("pageold") String pageold, @PathVariable("idDeTai") String idDeTai,
                             @PathVariable("accountViewer") String accountViewer, Model model) {
        DeTai deTai = deTaiService.findById(idDeTai);
        User viewer = userService.findByName(accountViewer);
        BaiBao baiBao = baiBaoService.findByIdDeTai(idDeTai);
        String noidung = "";
        model.addAttribute("pageold", pageold);
        model.addAttribute("detai", deTai);
        model.addAttribute("viewer", viewer);
        model.addAttribute("baibao", baiBao);
        model.addAttribute("noidung", noidung);
        return "vietBao";
    }

    @PostMapping("/vietBao/{pageold}/{idDeTai}/{accountViewer}")
    public String postVietBao(@PathVariable("pageold") String pageold, @PathVariable("idDeTai") String idDeTai,
                             @PathVariable("accountViewer") String accountViewer, @ModelAttribute("noidung") String noidung
                            ,Model model) {
        BaiBao baiBao = baiBaoService.findByIdDeTai(idDeTai);
        String idBaiBao = baiBaoService.themBaiBao(baiBao, idDeTai,noidung);
        deTaiHoanThanhService.updateBaiBao(idBaiBao, idDeTai);
        return getVietBao(pageold, idDeTai, accountViewer, model);
    }

    @GetMapping("daHoanThanh/{accountViewer}")
    public String getHoanThanh(@PathVariable("accountViewer") String accountViewer, Model model) {
        User viewer = userService.findByName(accountViewer);
        List<DeTaiHoanThanh> hoanThanhList = deTaiHoanThanhService.findAll();
        List<NguoiThucHien> nguoiThucHienList = nguoiThucHienService.findAll();
        List< Pair< Pair<DeTai, User>, DeTaiHoanThanh > > list = new ArrayList<
                Pair< Pair<DeTai, User>, DeTaiHoanThanh > >();
        for (int i = 0; i < hoanThanhList.size(); i++) {
            Pair<DeTai, User> nguoithuchien = null;
            for (int j = 0; j < nguoiThucHienList.size(); j++) {
                DeTai deTai = deTaiService.findById(hoanThanhList.get(i).getIDDeTai());
                if (nguoiThucHienList.get(j).getIDDeTai().equals(deTai.getIDDeTai())) {
                    User user = userService.findById(nguoiThucHienList.get(j).getIDNguoiThucHien());
                    nguoithuchien = Pair.of(deTai, user);
                }
            }
            if (nguoithuchien != null) {
                list.add(Pair.of(nguoithuchien, hoanThanhList.get(i)));
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("viewer", viewer);
        return "daHoanThanh";
    }

    @GetMapping("themDanhSachDeTai/{accountView}")
    public String getThemDanhSachDeTai(@PathVariable("accountView") String accountView, Model model) {
        User viewer = userService.findByName(accountView);
        MyFile myFile = new MyFile();
        model.addAttribute("myfile", myFile);
        model.addAttribute("viewer", viewer);
        return "themDanhSachDeTai";
    }

    @PostMapping("themDanhSachDeTai/{accountView}")
    public String postThemDanhSachDeTai(@PathVariable("accountView") String accountView,
                                        @ModelAttribute("myfile") MyFile file, Model model) throws IOException {
        MultipartFile multipartFile = file.getMultipartFile();
        XSSFWorkbook workbook  = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int position = 0;
            String tenDeTai = "";
            String moTa = "";
            String[] linhVuc = new String[]{};
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String rowTitle = "tên đề tài";
                if(cell.getStringCellValue().toLowerCase().equals(rowTitle.toLowerCase())) {
                    break;
                }
                if ( position == 0 ) {
                    tenDeTai = cell.getStringCellValue();
                    position++;
                } else if ( position == 1) {
                    moTa = cell.getStringCellValue();
                    position++;
                } else {
                    DeTai deTai = new DeTai(tenDeTai, moTa, null);
                    String idDeTai = deTaiService.addDeTai(deTai);
                    linhVuc = cell.getStringCellValue().split(",");
                    for (int i = 0;  i < linhVuc.length; i++) {
                        linhVuc[i] = linhVuc[i].trim();
                        LinhVuc linhvucDeTai = linhvucService.findByName(linhVuc[i]);
                        if (linhvucDeTai == null) {
                            System.out.println("không tìm ra lĩnh vực có tên : " + linhVuc[i]);
                        }
                        chuyenmonService.addChuyenMon(linhvucDeTai.getIDLinhVuc(), idDeTai);
                    }
                }
            }
        }
        return getListDeTai(model, accountView);
    }
}
