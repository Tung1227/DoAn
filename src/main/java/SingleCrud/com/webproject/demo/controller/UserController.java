package SingleCrud.com.webproject.demo.controller;

import SingleCrud.com.webproject.demo.model.*;
import SingleCrud.com.webproject.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final HoiDongChamService hoiDongChamService;
    private final DeTaiService deTaiService;
    private final NghiepVuService nghiepVuService;
    private final LinhVucService linhVucService;

    @Autowired
    public UserController(UserService userService, HoiDongChamService hoiDongChamService, DeTaiService deTaiService, NghiepVuService nghiepVuService, LinhVucService linhVucService) {
        this.userService = userService;
        this.hoiDongChamService = hoiDongChamService;
        this.deTaiService = deTaiService;
        this.nghiepVuService = nghiepVuService;
        this.linhVucService = linhVucService;
    }

    @GetMapping("/admin/{account}")
    public String adminPage(Model mode, @PathVariable String account) {
        mode.addAttribute("user", userService.findByName(account));
        return "admin";
    }

    @GetMapping("/hoidong/{account}")
    public String hoidongPage(Model mode, @PathVariable String account) {
        mode.addAttribute("user", userService.findByName(account));
        return "hoidong";
    }

    @GetMapping("/canbo/{account}")
    public String canboPage(Model mode, @PathVariable String account) {
        mode.addAttribute("user", userService.findByName(account));
        return "canbo";
    }

    @GetMapping("/nghiencuusinh/{account}")
    public String ncsinhPage(Model mode, @PathVariable String account) {
        mode.addAttribute("user", userService.findByName(account));
        return "nghiencuusinh";
    }

    @GetMapping("/failed")
    public String failed() {
        return "failed";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginacc(@ModelAttribute User user, RedirectAttributes redirectAttrs, Model model) {
        User userLogin = userService.login(user);
        boolean wrongAcc = false;
        boolean locked = false;
        if (userLogin == null) {
            wrongAcc = true;
            model.addAttribute("wrongAcc", wrongAcc);
            return login(model);
        }
        if(userLogin.getTrangThai().equals("khoa")) {
            locked = true;
            model.addAttribute("locked", locked);
            return login(model);
        }
        String option = userLogin.getRole();
        redirectAttrs.addAttribute("account", user.getTaiKhoan());
        if (option.equals("1")) {
            return "redirect:/admin/{account}";
        } else if (option.equals("2")) {
            return "redirect:/hoidong/{account}";
        } else if (option.equals("3")) {
            return "redirect:/canbo/{account}";
        } else if (option.equals("4")) {
            return "redirect:/nghiencuusinh/{account}";
        }
        return "redirect:/index";
    }

    @GetMapping("/lockAccount")
    public String getLockAccount() {
        return "lockAccount";
    }

    @GetMapping("/saiTaiKhoan")
    public String getSaiTaiKhoan() {
        return "saiTaiKhoan";
    }

    @GetMapping("/doimatkhau/{account}")
    public String formdoiPass(Model model, @PathVariable("account") String account) {
        User changer = userService.findByName(account);
        String pageold = "";
        if (changer.getRole().equals("1")){
            pageold = "admin";
        } else if (changer.getRole().equals("2")) {
            pageold = "hoidong";
        } else if (changer.getRole().equals("3")) {
            pageold = "canbo";
        } else  {
            pageold = "nghiencuusinh";
        }
        model.addAttribute("pageold", pageold);
        model.addAttribute("user", changer);
        return "doimatkhau";
    }

    @PostMapping("/doimatkhau/{account}")
    public String doiPassPost(@PathVariable("account") String account, @ModelAttribute User newUser, RedirectAttributes redirectAttrs) {
//        System.out.println(newUser);
//        System.out.println(account);
        User user = userService.findByName(account);
        userService.updatePass(user, newUser.getMatKhau());
        String option = user.getRole();
        redirectAttrs.addAttribute("account", account);
        if (option.equals("1")) {
            return "redirect:/admin/{account}";
        } else if (option.equals("2")) {
            return "redirect:/hoidong/{account}";
        } else if (option.equals("3")) {
            return "redirect:/canbo/{account}";
        } else if (option.equals("4")) {
            return "redirect:/nghiencuusinh/{account}";
        }
        return "redirect:/index";
    }

    @GetMapping("/listhoidong/{account}")
    public String listhoiDong(@PathVariable("account") String account, Model model) {
        List<User> userList = userService.findAll();
        List<User> listHoiDong = new ArrayList<User>();
        for(int i = 0 ; i < userList.size(); i++) {
            if(userList.get(i).getRole().equals("2")) {
                listHoiDong.add(userList.get(i));
            }
        }
        User changer = userService.findByName(account);
        String truong = "";
        String giatri = "";
        String pageold = "listhoidong";
        model.addAttribute("pageold", pageold);
        model.addAttribute("truong", truong);
        model.addAttribute("giatri", giatri);
        model.addAttribute("changer", changer);
        model.addAttribute("listhoidong", listHoiDong);
        return "listhoidong";
    }

    @PostMapping("/listhoidong/{account}")
    public String searchHoiDong(@PathVariable("account") String account, Model model, @ModelAttribute("giatri") String giatri, @ModelAttribute("truong") String truong ) {
        List<User> searchList = userService.search(truong, giatri);
        User changer = userService.findByName(account);
        List<User> listHoiDong = new ArrayList<User>();
        for(int i = 0 ; i < searchList.size(); i++) {
            if(searchList.get(i).getRole().equals("2")) {
                listHoiDong.add(searchList.get(i));
            }
        }
        String pageold = "listhoidong";
        model.addAttribute("pageold", pageold);
        model.addAttribute("changer", changer);
        model.addAttribute("listhoidong", listHoiDong);
        return "listhoidong";
    }

    @GetMapping("/listcanbo/{account}")
    public String listcanBo(@PathVariable("account") String account, Model model) {
        List<User> userList = userService.findAll();
        List<User> listCanbo = new ArrayList<User>();
        for(int i = 0 ; i < userList.size(); i++) {
            if(userList.get(i).getRole().equals("3")) {
                listCanbo.add(userList.get(i));
            }
        }
        User changer = userService.findByName(account);
        String truong = "";
        String giatri = "";
        String pageold = "listcanbo";
        model.addAttribute("pageold", pageold);
        model.addAttribute("truong", truong);
        model.addAttribute("giatri", giatri);
        model.addAttribute("changer", changer);
        model.addAttribute("listcanbo", listCanbo);

        return "listcanbo";
    }

    @PostMapping("/listcanbo/{account}")
    public String searchCanBo(@PathVariable("account") String account, Model model, @ModelAttribute("giatri") String giatri, @ModelAttribute("truong") String truong ) {
        List<User> searchList = userService.search(truong, giatri);
        User changer = userService.findByName(account);
        List<User> listCanbo = new ArrayList<User>();
        for(int i = 0 ; i < searchList.size(); i++) {
            if(searchList.get(i).getRole().equals("3")) {
                listCanbo.add(searchList.get(i));
            }
        }
        String pageold = "listcanbo";
        model.addAttribute("pageold", pageold);
        model.addAttribute("changer", changer);
        model.addAttribute("listcanbo", listCanbo);
        return "listcanbo";
    }


    @GetMapping("/listncsinh/{account}")
    public String listncSinh(@PathVariable("account") String account, Model model) {
        List<User> userList = userService.findAll();
        List<User> listNcsinh = new ArrayList<User>();
        for(int i = 0 ; i < userList.size(); i++) {
            if(userList.get(i).getRole().equals("4")) {
                listNcsinh.add(userList.get(i));
            }
        }
        User changer = userService.findByName(account);
        String truong = "";
        String giatri = "";
        String pageold = "listncsinh";
        model.addAttribute("pageold", pageold);
        model.addAttribute("truong", truong);
        model.addAttribute("giatri", giatri);
        model.addAttribute("changer", changer);
        model.addAttribute("listncsinh", listNcsinh);
        return "listncSinh";
    }

    @PostMapping("/listncsinh/{account}")
    public String searchNcsinh(@PathVariable("account") String account, Model model, @ModelAttribute("giatri") String giatri, @ModelAttribute("truong") String truong ) {
        List<User> searchList = userService.search(truong, giatri);
        User changer = userService.findByName(account);
        List<User> listNcsinh = new ArrayList<User>();
        for(int i = 0 ; i < searchList.size(); i++) {
            if(searchList.get(i).getRole().equals("4")) {
                listNcsinh.add(searchList.get(i));
            }
        }
        String pageold = "listncsinh";
        model.addAttribute("pageold", pageold);
        model.addAttribute("changer", changer);
        model.addAttribute("listncsinh", listNcsinh);
        return "listncSinh";
    }


    @GetMapping("/information/{pageold}/{account}/{status}")
    public String information(@PathVariable("pageold") String pageold ,@PathVariable("account") String account,@PathVariable("status") String status, Model model) {
        User user = userService.findByName(account);
        User whoFollow = userService.findByName(status);
        List<NghiepVu> nghiepVuList = nghiepVuService.findByIdUser(user.getID());
        List<LinhVuc> linhVucList = linhVucService.findAll();
        List<LinhVuc> linhVucCuaUser = new ArrayList<LinhVuc>();
        List<String> idLinhVuc = linhVucList.stream().map(linhVuc -> linhVuc.getIDLinhVuc()).collect(Collectors.toList());
        for (int i = 0; i < nghiepVuList.size(); i++) {
            if (idLinhVuc.contains(nghiepVuList.get(i).getIDLinhVuc())) {
                linhVucCuaUser.add(linhVucService.findByID(nghiepVuList.get(i).getIDLinhVuc()));
            }
        }
        model.addAttribute("linhvuc", linhVucCuaUser);
        model.addAttribute("user", user);
        model.addAttribute("whoFollow",whoFollow);
        model.addAttribute("pageold", pageold);
        return "information";
    }

    @PostMapping("/information/{pageold}/{account}/{status}")
    public String lockAccount(@PathVariable("pageold") String pageold, @PathVariable("account") String account, @PathVariable("status") String status , @ModelAttribute User newUser, Model model) {
        User user = userService.findByName(account);
        userService.updateStatus(user, newUser.getTrangThai());

        return information(pageold, account, status, model);
    }

    @GetMapping("/suathongtin/{pageold}/{accountUser}/{accountChanger}")
    public String changeInformation(@PathVariable("pageold") String pageold, @PathVariable("accountUser") String accountUser, @PathVariable("accountChanger") String accountChanger, Model model ) {
        User user = userService.findByName(accountUser);
        User changer = userService.findByName(accountChanger);
        model.addAttribute("user",user);
        model.addAttribute("changer", changer);
        model.addAttribute("pageold", pageold);
        return "suathongtin";
    }

    @PostMapping("/suathongtin/{pageold}/{accountUser}/{accountChanger}")
    public String handlerChangeInfo(@PathVariable("pageold") String pageold, @PathVariable("accountUser") String accountUser, @PathVariable("accountChanger") String accountChanger, @ModelAttribute User newUser, Model model) {
        User user = userService.findByName(accountUser);
//        System.out.println(user);
//        System.out.println(newUser);
        userService.update(user, newUser);
        return changeInformation(pageold, accountUser, accountChanger, model);
    }

    @GetMapping("/addTaiKhoan/{accountChanger}")
    public String getAddTaiKhoan(Model model,  @PathVariable("accountChanger") String accountChanger) {
        User changer = userService.findByName(accountChanger);
        List<String> danhSachTaiKhoanDangCo = userService.findAll().stream().map(element -> element.getTaiKhoan()).collect(Collectors.toList());
        model.addAttribute("danhSachTaiKhoan", danhSachTaiKhoanDangCo);
        model.addAttribute("user", new User());
        model.addAttribute("changer", changer);
        return "addTaiKhoan";
    }

    @PostMapping("/addTaiKhoan/{accountChanger}")
    public String postAddTaiKhoan(@PathVariable("accountChanger") String accountChanger,  RedirectAttributes redirectAttrs, @ModelAttribute User newUser) {
        userService.addUser(newUser);
//        System.out.println("ok");
        redirectAttrs.addAttribute("accountChanger", accountChanger);
        return "redirect:/admin/{accountChanger}";
    }

    @GetMapping("/changePersonInfo/{accountUser}")
    public String getChangePersonInfo(@PathVariable("accountUser") String accountUser, Model model) {
        User user = userService.findByName(accountUser);
        model.addAttribute("user", user);
        String option = user.getRole();
        if (option.equals("2")) {
            model.addAttribute("role", "hoidong");
        } else if (option.equals("3")) {
            model.addAttribute("role", "canbo");
        } else if (option.equals("4")) {
            model.addAttribute("role", "nghiencuusinh");
        }
        return "changePersonInfo";
    }

    @PostMapping("/changePersonInfo/{accountUser}")
    public String postChangePersonInfo(@PathVariable("accountUser") String accountUser,  @ModelAttribute User newUser,  RedirectAttributes redirectAttrs) {
        User user = userService.findByName(accountUser);
        userService.update(user, newUser);
        redirectAttrs.addAttribute("accountUser", accountUser);
        String option = user.getRole();
        if (option.equals("2")) {
            return "redirect:/changePersonInfo/{accountUser}";
        } else if (option.equals("3")) {
            return "redirect:/changePersonInfo/{accountUser}";
        } else if (option.equals("4")) {
            return "redirect:/changePersonInfo/{accountUser}";
        }
        return "redirect:error";
    }

    @GetMapping("/hoidongchamthi/{accountUser}")
    public String getHoiDongChamThi(@PathVariable("accountUser") String accountUser, Model model) {
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List<DeTai> deTaiCham = new ArrayList<DeTai>();
        List<DeTai> deTaiFilter = new ArrayList<DeTai>();
        for (int i = 0; i < hoiDongChamList.size(); i++) {
            deTaiCham.add(deTaiService.findById(hoiDongChamList.get(i).getIDDeTai()));
        }
        for (int i = 0; i < deTaiCham.size(); i++) {
            if (!deTaiFilter.contains(deTaiCham.get(i))) {
                deTaiFilter.add(deTaiCham.get(i));
            }
        }
        model.addAttribute("deTaiCham", deTaiFilter);
        model.addAttribute("viewer", userService.findByName(accountUser));
        return "hoidongchamthi";
    }

    @GetMapping("/danhsachcham/{idDeTai}/{accViewer}")
    public String getDanhSach(@PathVariable("idDeTai") String idDeTai, @PathVariable("accViewer") String accViewer, Model model) {
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List<User> nguoiCham = new ArrayList<User>();
        for (int i = 0; i < hoiDongChamList.size(); i++) {
            if (hoiDongChamList.get(i).getIDDeTai().equals(idDeTai)){
                nguoiCham.add(userService.findById(hoiDongChamList.get(i).getIDCanBo()));
            }
        }
        String pageold = "danhsachcham";
        model.addAttribute("pageold", pageold);
        model.addAttribute("nguoicham", nguoiCham);
        model.addAttribute("accViewer", userService.findByName(accViewer));

        return "danhsachcham";
    }

    @GetMapping("/phanCongCham/{idDeTai}/{accountView}")
    public String getPhanCong(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer, Model model)  {
        List<User> userList = userService.findAll();
        List<User> listCanbo = new ArrayList<User>();
        for(int i = 0 ; i < userList.size(); i++) {
            if(userList.get(i).getRole().equals("3")) {
                listCanbo.add(userList.get(i));
            }
        }
        List<NghiepVu> nghiepVuList = nghiepVuService.findAll();
        List<LinhVuc> linhVucList =  linhVucService.findAll();

        Map<User,List<LinhVuc>>  linhVucCuaCanBo = new HashMap<User, List<LinhVuc>>();
        for (int  i = 0; i < listCanbo.size(); i++) {
            List<LinhVuc> list = new ArrayList<LinhVuc>();
            for (int j = 0; j < nghiepVuList.size(); j++) {
                if (listCanbo.get(i).getID().equals(nghiepVuList.get(j).getIDCanBo())) {
                    for (int k = 0; k < linhVucList.size(); k++) {
                        if (nghiepVuList.get(j).getIDLinhVuc().equals(linhVucList.get(k).getIDLinhVuc())) {
                            list.add(linhVucList.get(k));
                        }
                    }
                }
            }
           if(list.size()!=0) {
               linhVucCuaCanBo.put(listCanbo.get(i), list);
           }
        }
//        List<String> checked = new ArrayList<String>();
        String checked = "";
        String notChecked = "";
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List<String> idHoiDongChamCuaDeTai = new ArrayList<String>();
        for(int i = 0; i < hoiDongChamList.size(); i++) {
            if (hoiDongChamList.get(i).getIDDeTai().equals(idDeTai)) {
                idHoiDongChamCuaDeTai.add(hoiDongChamList.get(i).getIDCanBo());
            }
        }
        model.addAttribute("detai", deTaiService.findById(idDeTai));
        model.addAttribute("viewer", userService.findByName(accViewer));
        model.addAttribute("hoidongcham",  idHoiDongChamCuaDeTai);
        model.addAttribute( "linhVucCuaCanBo", linhVucCuaCanBo);
        model.addAttribute("checked", checked);
        model.addAttribute("notChecked", notChecked);
        return "phanCongCham";
    }

    @PostMapping("/phanCongCham/{idDeTai}/{accountView}")
    public String postPhanCong(@PathVariable("idDeTai") String idDeTai, @PathVariable("accountView") String accViewer,  @ModelAttribute("notChecked") String notChecked, @ModelAttribute("checked") String checked ,Model model) {
//        System.out.println(checked);
        String[] idNguoiCham = checked.split("/");
        String[] idNguoiKhongCham = notChecked.split("/");
        List<HoiDongCham> hoiDongChamList = hoiDongChamService.findAll();
        List<String> idHoiDongChamCuaDeTai =  new ArrayList<String>();
        for (int i = 0; i < hoiDongChamList.size(); i++) {
            if (hoiDongChamList.get(i).getIDDeTai().equals(idDeTai)) {
                idHoiDongChamCuaDeTai.add(hoiDongChamList.get(i).getIDCanBo());
            }
        }
        for (int i = 0 ; i < idNguoiCham.length; i++) {
            if (!idHoiDongChamCuaDeTai.contains(idNguoiCham[i])) {
                idHoiDongChamCuaDeTai.add(idNguoiCham[i]);
            }
        }
        for (int i = 0; i < idNguoiKhongCham.length; i++) {
            if (idHoiDongChamCuaDeTai.contains(idNguoiKhongCham[i])) {
                idHoiDongChamCuaDeTai.remove(idNguoiKhongCham[i]);
            }
        }
//        System.out.println(idHoiDongChamCuaDeTai);
        hoiDongChamService.updateListByUser(idDeTai, idHoiDongChamCuaDeTai);
        return getPhanCong(idDeTai, accViewer, model);
    }

    @GetMapping("/thongtinNCS/{pageold}/{idNCS}/{idNguoiHuongDan}")
    public String getThongTinNCS(@PathVariable("pageold") String pageold, @PathVariable("idNCS") String idNCS, @PathVariable("idNguoiHuongDan") String idNguoiHuongDan, Model model) {
        User user = userService.findById(idNCS);
        User nguoiHuongDan = userService.findById(idNguoiHuongDan);
        model.addAttribute("pageold",pageold);
        model.addAttribute("user", user);
        model.addAttribute("nguoihuongdan", nguoiHuongDan);
        return "thongtinNCS";
    }

    @GetMapping("/dangky")
    public String getDangKy(Model model) {
        List<String> danhSachTaiKhoanDangCo = userService.findAll().stream().map(element -> element.getTaiKhoan()).collect(Collectors.toList());
        model.addAttribute("danhSachTaiKhoan", danhSachTaiKhoanDangCo);
        model.addAttribute("user", new User());
        return "dangky";
    }

    @PostMapping("/dangky")
    public String postDangKy(@ModelAttribute("user") User newUser) {
        newUser.setRole("4");
        userService.addUser(newUser);
        return "redirect:/login";
    }
}
